package com.gecko.jee.enterprise.mft.cli;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponent;
import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponentImpl;
import com.gecko.jee.enterprise.mft.business.component.MonitoringNotifierComponent;
import com.gecko.jee.enterprise.mft.business.component.NoyauComponent;
import com.gecko.jee.enterprise.mft.business.component.NoyauComponentImpl;
import com.gecko.jee.enterprise.mft.business.service.NoyauService;
import com.gecko.jee.enterprise.mft.business.service.NoyauServiceRest;
import com.gecko.jee.enterprise.mft.cli.shell.CliInputReader;
import com.gecko.jee.enterprise.mft.cli.shell.CliShellHelper;
import com.gecko.jee.enterprise.mft.cli.shell.CliShellNotifier;

/**
 * <b>Description: Classe de configuration Spring du module CLI (Spring
 * Shell).</b>
 * <p>
 * La methode main est lancée aprés l'initialisation et instantiation des Beans
 * de notre application.
 * </p>
 * <p/>
 * Pour lancer l'application shell dans une fenêtre de commande: java -jar
 * mft-cli.<version>.jar
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@SpringBootApplication
@PropertySources({ @PropertySource("classpath:message.properties") })
@ComponentScan({ "com.gecko.jee.enterprise.mft.cli", "com.gecko.jee.enterprise.mft.business.component",
		"com.gecko.jee.enterprise.mft.business.config", "com.gecko.jee.enterprise.mft.business.service" })
public class CliApplication {

	/**
	 * Chargement de la configuration Spring.
	 *
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(CliApplication.class, args);
	}

	@Bean
	public CliInputReader cliInputReader(@Lazy final LineReader lineReader) {
		return new CliInputReader(lineReader);
	}

	@Bean
	public CliShellHelper cliShellHelper(@Lazy final Terminal terminal) {
		return new CliShellHelper(terminal);
	}

	/**
	 * Instanciation du bean vommandeHauNiveauComponent de la couche Business. Ce
	 * composant dialogue avec :
	 * <ul>
	 * <li>l'EJB du noyau (via des services de la couche Business).</li>
	 * <li>les DAO de la couche Persistence.</li>
	 * </ul>
	 *
	 * @return
	 */
	@Bean
	public CommandeHautNiveauComponent commandeHautNiveauComponent() {
		return new CommandeHautNiveauComponentImpl();
	}

	@Bean
	public MonitoringNotifierComponent monitoringNotifierComponent() {
		return new CliShellNotifier();
	}

	/**
	 * Instanciation du bean noyauComponent de la couche Business. Ce composant
	 * dialogue avec :
	 * <ul>
	 * <li>l'EJB du noyau (via des services de la couche Business).</li>
	 * <li>les DAO de la couche Persistence.</li>
	 * </ul>
	 *
	 * @return
	 */
	@Bean
	public NoyauComponent noyauComponent() {
		return new NoyauComponentImpl();
	}

	/**
	 * Instanciation du bean noyauService de la couche Business. Ce composant
	 * dialogue avec :
	 * <ul>
	 * <li>l'EJB du noyau (via des services de la couche Business).</li>
	 * </ul>
	 *
	 * @return
	 */
	@Bean
	public NoyauService noyauService() {
		return new NoyauServiceRest();
	}
}
