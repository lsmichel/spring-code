package com.gecko.jee.enterprise.mft.business.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gecko.jee.enterprise.mft.business.component.MonitoringNotifierComponent;
import com.gecko.jee.enterprise.mft.business.service.NotificationInfo;

/**
 * <b>Description: Web service de supervision MFT X-Protocols.</b>
 * <p>
 * Ce WS reçoit des notifications en provenance du noyau. Il a en charge de
 * notifier l'application émétrice de la commande (CLi, Webapp) et également de
 * persister les informations pour avoir une vision générale/partagée des
 * commandes exécutées.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Controller
@RequestMapping("notify")
public class MonitoringAgentController {

	/**
	 * Le composant de notification: instancié dans la couche CLI, Webapp car
	 * l'implémentation de la notification est différente.
	 */
	@Autowired
	MonitoringNotifierComponent monitoringNotifierComponent;

	/**
	 * Exposition en mode REST pour que le noyau notifie la couche appelante.
	 *
	 * @param notificationInfo Pojo contenant les détails de la notification
	 *                         provenant du noyau.
	 * @return "ok" 200 si la notification a bien été intégrée.
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> notify(final RequestEntity<NotificationInfo> notificationInfo) {
		// appel du business pour le reste à faire
		this.monitoringNotifierComponent.notify(notificationInfo.getBody());

		final HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>("ok", httpHeaders, HttpStatus.OK);
	}
}
