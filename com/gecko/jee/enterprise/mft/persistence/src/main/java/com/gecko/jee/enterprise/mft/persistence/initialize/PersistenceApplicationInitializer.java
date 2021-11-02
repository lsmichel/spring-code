package com.gecko.jee.enterprise.mft.persistence.initialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveau;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveauArgument;
import com.gecko.jee.enterprise.mft.persistence.repository.CommandeHautNiveauArgumentRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.CommandeHautNiveauRepository;

/**
 * <b>Description: Classe d'initialisation du modèle de données MFT
 * X-Protocols.</b>
 * <p>
 * Cette classe doit être appelée manuellement pour insérer les données de
 * paramétrage. Cela a été fait en JPA et pas en SQL pour ne pas avoir à gérer
 * d'id en dur.
 * </p>
 * <p>
 * Pour lancer le chargement:
 * <ul>
 * <li>Pour créer les tables: mvn spring-boot:run</li>
 * <li>Pour charger un protocole: mvn spring-boot:run
 * -Dspring-boot.run.arguments="ftp"</li>
 * </ul>
 * </p>
 * <p>
 * Protocoles/Fonctionnalités configurés
 * <ul>
 * <li>FTP-PUT</li>
 * </ul>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Component
public class PersistenceApplicationInitializer implements CommandLineRunner {

	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(PersistenceApplicationInitializer.class);

	/**
	 * Repository JPA des arguments des commandes de haut niveau
	 */
	@Autowired
	private CommandeHautNiveauArgumentRepository commandeHautNiveauArgumentRepository;

	/**
	 * Repository JPA des commandes de haut niveau
	 */
	@Autowired
	private CommandeHautNiveauRepository commandeHautNiveauRepository;

	/**
	 *
	 * Méthode de chargement des paramètres de la commande FTP "put".
	 */
	public void chargerFtpPut() {
		logger.info("================= debut chargement ftp-put");
		CommandeHautNiveau commandeHautNiveau = new CommandeHautNiveau();
		commandeHautNiveau.setIdent("ftp-put");
		commandeHautNiveau.setDescription("Transfert d'un fichier avec le protocole FTP");

		final CommandeHautNiveauArgument commandeHautNiveauArgumentUser = new CommandeHautNiveauArgument();
		commandeHautNiveauArgumentUser.setCommandeHautNiveau(commandeHautNiveau);
		commandeHautNiveauArgumentUser.setIdent("-u");
		commandeHautNiveauArgumentUser.setDescription("Nom utilisateur serveur FTP");
		commandeHautNiveauArgumentUser.setObligatoire(true);
		commandeHautNiveauArgumentUser.setType(String.class.getName());
		commandeHautNiveau.getCommandeHautNiveauArguments().add(commandeHautNiveauArgumentUser);

		final CommandeHautNiveauArgument commandeHautNiveauArgumentPassword = new CommandeHautNiveauArgument();
		commandeHautNiveauArgumentPassword.setCommandeHautNiveau(commandeHautNiveau);
		commandeHautNiveauArgumentPassword.setIdent("-p");
		commandeHautNiveauArgumentPassword.setDescription("Mot de passe utilisateur serveur FTP");
		commandeHautNiveauArgumentPassword.setObligatoire(true);
		commandeHautNiveauArgumentPassword.setType(String.class.getName());
		commandeHautNiveau.getCommandeHautNiveauArguments().add(commandeHautNiveauArgumentPassword);

		final CommandeHautNiveauArgument commandeHautNiveauArgumentHost = new CommandeHautNiveauArgument();
		commandeHautNiveauArgumentHost.setCommandeHautNiveau(commandeHautNiveau);
		commandeHautNiveauArgumentHost.setIdent("-h");
		commandeHautNiveauArgumentHost.setDescription("Adresse IP serveur FTP");
		commandeHautNiveauArgumentHost.setObligatoire(true);
		commandeHautNiveauArgumentHost.setType(String.class.getName());
		// this.commandeHautNiveauArgumentRepository.save(commandeHautNiveauArgumentHost);
		commandeHautNiveau.getCommandeHautNiveauArguments().add(commandeHautNiveauArgumentHost);

		final CommandeHautNiveauArgument commandeHautNiveauArgumentPort = new CommandeHautNiveauArgument();
		commandeHautNiveauArgumentPort.setCommandeHautNiveau(commandeHautNiveau);
		commandeHautNiveauArgumentPort.setIdent("-P");
		commandeHautNiveauArgumentPort.setDescription("Port serveur FTP");
		commandeHautNiveauArgumentPort.setObligatoire(true);
		commandeHautNiveauArgumentPort.setType(Integer.class.getName());
		// this.commandeHautNiveauArgumentRepository.save(commandeHautNiveauArgumentPort);
		commandeHautNiveau.getCommandeHautNiveauArguments().add(commandeHautNiveauArgumentPort);

		final CommandeHautNiveauArgument commandeHautNiveauArgumentRemoteDirectoryPath = new CommandeHautNiveauArgument();
		commandeHautNiveauArgumentRemoteDirectoryPath.setCommandeHautNiveau(commandeHautNiveau);
		commandeHautNiveauArgumentRemoteDirectoryPath.setIdent("-d");
		commandeHautNiveauArgumentRemoteDirectoryPath.setDescription("Répertoire de destination sur serveur FTP");
		commandeHautNiveauArgumentRemoteDirectoryPath.setObligatoire(true);
		commandeHautNiveauArgumentRemoteDirectoryPath.setType(String.class.getName());
		// this.commandeHautNiveauArgumentRepository.save(commandeHautNiveauArgumentRemoteDirectoryPath);
		commandeHautNiveau.getCommandeHautNiveauArguments().add(commandeHautNiveauArgumentRemoteDirectoryPath);

		final CommandeHautNiveauArgument commandeHautNiveauArgumentLocalFilePath = new CommandeHautNiveauArgument();
		commandeHautNiveauArgumentLocalFilePath.setCommandeHautNiveau(commandeHautNiveau);
		commandeHautNiveauArgumentLocalFilePath.setIdent("-f");
		commandeHautNiveauArgumentLocalFilePath.setDescription("Chemin local absolu du fichier à transférer");
		commandeHautNiveauArgumentLocalFilePath.setObligatoire(true);
		commandeHautNiveauArgumentLocalFilePath.setType(String.class.getName());
		commandeHautNiveau.getCommandeHautNiveauArguments().add(commandeHautNiveauArgumentLocalFilePath);
		// this.commandeHautNiveauArgumentRepository.save(commandeHautNiveauArgumentLocalFilePath);

		commandeHautNiveau = this.commandeHautNiveauRepository.save(commandeHautNiveau);
		logger.info("================= fin chargement ftp-put");
	}

	/**
	 * Méthode principale du chargement de tous les paramétrages.
	 *
	 * @FIXME à voir pour personnaliser cette méthode en fonction de ce que le
	 *        client a acheter comme protocoles.
	 */
	@Override
	public void run(final String... args) throws Exception {
		logger.info("================= debut chargement ......");
		for (final String arg : args) {
			if (arg.equals("ftp")) {
				this.chargerFtpPut();
			}
		}
		logger.info("================= fin chargement");
	}

}
