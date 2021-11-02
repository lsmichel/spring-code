package com.gecko.jee.enterprise.mft.cli.shell;

import org.springframework.beans.factory.annotation.Autowired;

import com.gecko.jee.enterprise.mft.business.component.MonitoringNotifierComponent;
import com.gecko.jee.enterprise.mft.business.service.NotificationInfo;

/**
 * <b>Description: Composant en charge des notifications dans la console du
 * CLI.</b>
 * <p>
 * Permet d'afficher du texte dans la console dans différentes couleurs pour
 * informer l'utilisateur.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class CliShellNotifier implements MonitoringNotifierComponent {
	@Autowired
	private CliShellHelper cliShellHelper;

	/**
	 * {@inheritDoc}.
	 */

	@Override
	public void notify(final NotificationInfo notificationInfo) {
		// Si le propriete level est sucess on fait un printSucces si non printError
		// dans la terminal de commande MFT
		if (notificationInfo.getLevel().equals("sucess")) {
			this.cliShellHelper.printSuccess(notificationInfo.getMessage());
		} else {
			this.cliShellHelper.printError(notificationInfo.getMessage());
		}
	}

}
