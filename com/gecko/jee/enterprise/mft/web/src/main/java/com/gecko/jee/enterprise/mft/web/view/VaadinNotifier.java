package com.gecko.jee.enterprise.mft.web.view;

import com.gecko.jee.enterprise.mft.business.component.MonitoringNotifierComponent;
import com.gecko.jee.enterprise.mft.business.service.NotificationInfo;
import com.vaadin.flow.component.notification.Notification;

/**
 * <b>Description: Composant en charge des notifications dans l'application web
 * Vaadin.</b>
 * <p>
 * Permet d'afficher du texte dans la console dans différentes couleurs pour
 * informer l'utilisateur. S'appuie sur le système de notification de Vaadin.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class VaadinNotifier implements MonitoringNotifierComponent {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notify(final NotificationInfo notificationInfo) {
		// final Notification notification = new
		// Notification(notificationInfo.getMessage());
		// notification.setDuration(3000);
		// notification.open();
		// UI.getCurrent().
		Notification.show(notificationInfo.getMessage());
	}
}
