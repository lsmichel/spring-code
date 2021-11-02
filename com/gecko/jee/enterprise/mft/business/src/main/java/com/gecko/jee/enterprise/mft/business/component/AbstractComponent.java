package com.gecko.jee.enterprise.mft.business.component;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.gecko.jee.enterprise.mft.business.service.MonitoringService;
import com.gecko.jee.enterprise.mft.business.service.NoyauService;
import com.gecko.jee.enterprise.mft.persistence.repository.CommandeHautNiveauRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.automate.TransitionRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.connecteur.ConnecteurRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.contexte.ValeurDeParametreRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.facilite.ObjectifDeSessionSortanteRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.facilite.PartenaireAppeleRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.facilite.SessionSortanteRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.facilite.UtilisationDeContexteDExecutionAppliqueRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.instance.CommandeCoucheHauteRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.instance.ImageDeGdSRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.instance.LigneDeJournalAprèsRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.protocole.ParametreRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.protocole.ProtocoleRepository;

/**
 * <b>Description: Classe abstraite pour centraliser certains attributs des
 * components Business.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Component
public class AbstractComponent {
	/**
	 * Fichier de configuration du module business
	 */
	protected Properties businessConfigProperties;
	/**
	 * Fichier des messages pour les erreurs métiers et techniques de la couche
	 * Business.
	 */

	protected Properties businessExceptionProperties;

	/**
	 * Repository (JPA) pour commandeCoucheHaute
	 */
	@Autowired
	protected CommandeCoucheHauteRepository commandeCoucheHauteRepository;

	/**
	 * Repository (JPA) pour les commandes "haut niveau"
	 */
	@Autowired
	protected CommandeHautNiveauRepository commandeHautNiveauRepository;

	/**
	 * Repository (JPA) pour les connecteurs
	 */
	@Autowired
	protected ConnecteurRepository connecteurRepository;

	/**
	 * Repository (JPA) pour image group de synchro
	 */
	@Autowired
	protected ImageDeGdSRepository imageDeGdSRepository;

	@Autowired
	protected LigneDeJournalAprèsRepository ligneDeJournalAprèsRepository;

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(NoyauComponentImpl.class);

	@Autowired
	protected MonitoringService monitoringService;

	/**
	 * Client (REST) pour les appels au noyau
	 */
	@Autowired
	protected NoyauService noyauService;

	/**
	 * Repository (JPA) pour les objectifDeSessionSortantes
	 */
	@Autowired
	protected ObjectifDeSessionSortanteRepository objectifDeSessionSortanteRepository;

	/**
	 * Repository (JPA) pour paramètre
	 */
	@Autowired
	protected ParametreRepository parametreRepository;

	/**
	 * Repository (JPA) pour partenaireAppele
	 */
	@Autowired
	protected PartenaireAppeleRepository partenaireAppeleRepository;

	/**
	 * Repository (JPA) pour les protocoles
	 */
	@Autowired
	protected ProtocoleRepository protocoleRepository;

	/**
	 * Repository (JPA) pour les sessionSortantes
	 */
	@Autowired
	protected SessionSortanteRepository sessionSortanteRepository;

	/**
	 * Repository (JPA) pour les transitions
	 */
	@Autowired
	protected TransitionRepository transitionRepository;

	/**
	 * Repository (JPA) pour utilisationDeContexteDExecutionApplique
	 */
	@Autowired
	protected UtilisationDeContexteDExecutionAppliqueRepository utilisationDeContexteDExecutionAppliqueRepository;

	/**
	 * Repository (JPA) pour les valeurs de paramètre
	 */
	@Autowired
	protected ValeurDeParametreRepository valeurDeParametreRepository;

	/**
	 * Méthode d'intialisation de certains attributs du bean.
	 */
	@PostConstruct
	public void initBean() {
		try {
			final Resource resource = new ClassPathResource("/businessException.properties");
			this.businessExceptionProperties = PropertiesLoaderUtils.loadProperties(resource);
			final Resource configResource = new ClassPathResource("/config.properties");
			this.businessConfigProperties = PropertiesLoaderUtils.loadProperties(configResource);

		} catch (final IOException e) {
			this.logger.error(e.getMessage());
		}
	}

}
