package com.gecko.jee.enterprise.mft.business.component;

import org.springframework.stereotype.Service;

import com.gecko.jee.enterprise.mft.business.rest.MonitoringAgentController;
import com.gecko.jee.enterprise.mft.business.service.NotificationInfo;

/**
 * <b>Description: Interface de notification liée à la supervision.</b>
 * <p>
 * Cette interface sera appelé par le {@link MonitoringAgentController} pour
 * notifier la couche appelante (CLI, Webapp).
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Service
public interface MonitoringNotifierComponent {

	/**
	 * Notifie l'utilisateur via un message: soit via la console pour le CLI, soit
	 * via Vaadin pour l'application Web.
	 *
	 * @param notificationInfo Pojo contenant les détails de la notification
	 *                         provenant du noyau.
	 */
	void notify(NotificationInfo notificationInfo);
}
