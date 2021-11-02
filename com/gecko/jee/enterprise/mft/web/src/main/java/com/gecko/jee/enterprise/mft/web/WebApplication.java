package com.gecko.jee.enterprise.mft.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponent;
import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponentImpl;
import com.gecko.jee.enterprise.mft.business.component.ContexteDExecutionComponent;
import com.gecko.jee.enterprise.mft.business.component.ContexteDExecutionComponentImpl;
import com.gecko.jee.enterprise.mft.business.component.MonitoringNotifierComponent;
import com.gecko.jee.enterprise.mft.business.component.NoyauComponent;
import com.gecko.jee.enterprise.mft.business.component.NoyauComponentImpl;
import com.gecko.jee.enterprise.mft.business.service.MonitoringService;
import com.gecko.jee.enterprise.mft.business.service.MonitoringServiceRepository;
import com.gecko.jee.enterprise.mft.business.service.NoyauService;
import com.gecko.jee.enterprise.mft.business.service.NoyauServiceRest;
import com.gecko.jee.enterprise.mft.web.view.VaadinNotifier;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * <b>C'est la classe principale de notre application spring boot</b>
 * <p>
 * La methode main qu'elle contient est lancée aprés l'initialisation et
 * instantiation des Beans de notre application
 * </p>
 *
 * @author GECKO SOFTWARE
 */
//permettre le fonctionnement de spring avec le MCV de vaadin
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@ComponentScan({ "com.gecko.jee.enterprise.mft.web", "com.gecko.jee.enterprise.mft.business.component",
		"com.gecko.jee.enterprise.mft.business.config", "com.gecko.jee.enterprise.mft.business.service" })
public class WebApplication {

	/**
	 * Chargement de la configuration Spring + initialisation du noyau MFT.
	 *
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(WebApplication.class, args);
		// final ConfigurableApplicationContext context =
		// SpringApplication.run(WebApplication.class, args);

		// TODO: lire les configurations des protocoles, connecteurs, connecteurs
		// serveurs... pour les envoyer au noyau afin qu'il les charge en mémoire.
		// context.getBean(NoyauComponent.class).chargementNoyau();
	}

	@Bean
	public CommandeHautNiveauComponent commandeHautNiveauComponent() {
		return new CommandeHautNiveauComponentImpl();
	}

	@Bean
	public ContexteDExecutionComponent contexteDExecutionComponent() {
		return new ContexteDExecutionComponentImpl();
	}

	@Bean
	public MonitoringNotifierComponent monitoringNotifierComponent() {
		return new VaadinNotifier();
	}

	@Bean
	public MonitoringService monitoringService() {
		return new MonitoringServiceRepository();
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
