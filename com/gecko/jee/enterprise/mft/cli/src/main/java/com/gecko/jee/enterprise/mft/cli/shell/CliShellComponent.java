package com.gecko.jee.enterprise.mft.cli.shell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponent;
import com.gecko.jee.enterprise.mft.business.component.NoyauComponent;
import com.gecko.jee.enterprise.mft.exception.BusinessException;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelé;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveau;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Protocole;
import com.gecko.jee.enterprise.mft.utils.OperatingSystems;

/**
 * <b>Description: Classe d'implémentation des commandes du shell.</b>
 * <p>
 * Ce composant shell est générique: les commandes macro ne seront pas déclarées
 * ici. Il s'appuie sur le module Business pour récupérer la liste des commandes
 * macro disponibles.
 * </p>
 * <p>
 * On a donc une commande d'aide pour lister les commandes macro et une commande
 * pour exécuter une commande macro.
 * </p>
 *
 * @author GECKO SOFTWARE
 */

@ShellComponent
public class CliShellComponent {

	/**
	 * Composant pour la lecture des saisies de l'utilisateur
	 */
	@Lazy
	@Autowired
	private CliInputReader cliInputReader;

	/**
	 * Composant pour la coloration des messages
	 */
	@Autowired
	private CliShellHelper cliShellHelper;

	/**
	 * Composant Business pour accéder aux données et au noyau.
	 */
	@Autowired
	private CommandeHautNiveauComponent commandeHautNiveauComponent;

	/**
	 * Composant Business pour exécuter certaines commandes noyau.
	 */
	@Autowired
	private NoyauComponent noyauComponent;

	/**
	 * Cette méthode permet de lister les commandes macro chargées en base de
	 * données.
	 *
	 * @return la liste des commandes macro disponibles et de leurs arguments.
	 */
	@ShellMethod(value = "Liste des commandes MFT X-Protocols", key = "list-commands")
	public String listCommands() {
		final List<CommandeHautNiveau> commandeHautNiveaus = this.commandeHautNiveauComponent.getCommandesHautNiveau();
		String newLine = "";
		//
		if (OperatingSystems.isSolaris() || OperatingSystems.isMac() || OperatingSystems.isUnix()) {
			// passage à la ligne plaforme unix
			newLine = "\n";
		} else if (OperatingSystems.isWindows()) {
			// passage à la ligne plaforme windows
			newLine = "\r\n";
		}
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Liste des commandes et leurs argumets :");
		stringBuilder.append(newLine);
		stringBuilder.append(newLine);
		for (final CommandeHautNiveau commandeHautNiveau : commandeHautNiveaus) {
			stringBuilder.append(commandeHautNiveau.toString().replace("\n", newLine));
		}
		stringBuilder.append(newLine);
		stringBuilder.append(newLine);
		return stringBuilder.toString();
	}

	/**
	 * Commande de chargement des configurations d'un protocole dans le noyau
	 *
	 * @param identifiantProtocole Identifiant du protocole
	 * @return
	 */
	@ShellMethod(value = "Chargement dans le noyau de l'automate du protocole", key = "load-protocol")
	public String loadProtocol(@ShellOption(help = "Identifiant du protocole") final String identifiantProtocole) {
		final Protocole protocole = new Protocole();
		protocole.setIdent(identifiantProtocole);
		try {
			this.noyauComponent.chargementNoyau(protocole);
		} catch (final BusinessException businessException) {
			// Problème d'identification du protocole
			this.cliShellHelper.printError(businessException);
		}
		return null;
	}

	/**
	 * Authentification de l'utilisateur pour la gestion des permissions.
	 *
	 * @return
	 */
	@ShellMethod(value = "Authentification", key = "login")
	public String login() {
		String username;
		boolean usernameInvalid = true;
		do {
			username = this.cliInputReader.prompt("Please enter your username");
			if (StringUtils.hasText(username)) {
				usernameInvalid = false;
			} else {
				this.cliShellHelper.getErrorMessage("Username can not be empty string!");
			}
		} while (usernameInvalid);
		this.cliShellHelper
				.printSuccess("Credentials successfully authenticated! " + username + " -> welcome to CliDemo.");
		return this.cliShellHelper.getInfoMessage("Authentication passed");
//		final String password = this.inputReader.prompt("Please enter your password", null, false);
//		final Authentication request = new UsernamePasswordAuthenticationToken(username, password);
//
//		try {
//			final Authentication result = authenticationManager.authenticate(request);
//			SecurityContextHolder.getContext().setAuthentication(result);
//			shellHelper.printSuccess("Credentials successfully authenticated! " + username + " -> welcome to CliDemo.");
//		} catch (final AuthenticationException e) {
//			shellHelper.printWarning("Authentication failed: " + e.getMessage());
//		}
	}

	/**
	 * Commande d'exécution d'une commande de haut niveau.
	 *
	 * @param partenaire      Identifiant du partenaire
	 * @param session         Mode de session (FTP, SFTP, PESIT...)
	 * @param objectifSession Objectif de la session (transfert, commande...)
	 * @param parametres      Liste des paramètres complémentaires (clé=valeur) et
	 *                        séparés par des !.
	 * @return
	 */
	@ShellMethod(value = "Execution d'une commande de haut niveau", key = "mft")
	public String mft(@ShellOption(help = "Identifiant du partenaire", value = "-P") final String partenaire,
			@ShellOption(help = "Mode de session (FTP, SFTP, PESIT...)", value = "-S") final String session,
			@ShellOption(help = "Objectif de la session (transfert, commande...)", value = "-O") final String objectifSession,
			@ShellOption(help = "Liste des paramètres complémentaires (clé=valeur) et séparés par des !", value = "-p") final String parametres) {

		// Constitution des objets de la commande
		final PartenaireAppelé partenaireAppelé = new PartenaireAppelé();
		partenaireAppelé.setIdent(partenaire);
		final SessionSortante sessionSortante = new SessionSortante();
		sessionSortante.setIdent(session);
		final ObjectifDeSessionSortante objectifSessionSortante = new ObjectifDeSessionSortante();
		objectifSessionSortante.setIdent(objectifSession);

		// Découpage des paramètres complémentaires
		final Map<String, String> parametresMap = new HashMap<>();
		final String[] paramClesValeurs = parametres.split("!");
		for (final String cleValeur : paramClesValeurs) {
			final String[] cleValeurSplit = cleValeur.split("=");
			this.cliShellHelper.printSuccess("Parametre clé:" + cleValeurSplit[0] + " valeur: " + cleValeurSplit[1]);
			parametresMap.put(cleValeurSplit[0], cleValeurSplit[1]);
		}

		try {
			// Exécution de la commande
			this.commandeHautNiveauComponent.executerCommandeHautNiveau(partenaireAppelé, sessionSortante,
					objectifSessionSortante, parametresMap);
		} catch (final BusinessException e) {
			// Une erreur métier est survenue
			this.cliShellHelper.printError(e);
		}
		return "";
	}
}
