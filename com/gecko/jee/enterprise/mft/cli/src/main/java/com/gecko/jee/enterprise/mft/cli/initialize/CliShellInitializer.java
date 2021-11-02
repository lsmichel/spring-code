package com.gecko.jee.enterprise.mft.cli.initialize;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gecko.jee.enterprise.mft.business.component.NoyauComponent;

/**
 * <b>Description: Composant d'initialisation du CLI.</b>
 * <p>
 * Permet de réaliser différentes tâches de chargement du Noyau.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Component
public class CliShellInitializer {

	/**
	 * Composant métier pour les appels au Noyau.
	 */
	@Autowired
	private NoyauComponent noyauComponent;

	/**
	 * Opérations d'initialisation lors du démarrage d'une instance du CLI:
	 * <ul>
	 * <li>Chargement du noyau à partir de la configuration présente en base de
	 * données.</li>
	 * </ul>
	 */
	@PostConstruct
	private void start() {
		this.noyauComponent.chargementNoyau();
	}

	/**
	 * Opérations de clôture du contexte du CLI.
	 */
	@PreDestroy
	private void stop() {

	}
}
