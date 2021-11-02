package com.gecko.jee.enterprise.mft.business.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.gecko.jee.enterprise.mft.persistence.entity.automate.ÉtatÉlémentaire;
import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ValeurDeParamètre;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;
import com.gecko.jee.enterprise.mft.persistence.entity.instance.CommandeCoucheHaute;
import com.gecko.jee.enterprise.mft.persistence.entity.instance.ImageDeGdS;
import com.gecko.jee.enterprise.mft.persistence.entity.instance.LigneDeJournalAprès;

/**
 * <b>Description: Implémentation du service d'accès aux données de supervision
 * de MFT X-Protocols. Il s'appuie sur des composants {@link Repository} pour
 * accéder aux données en base.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class MonitoringServiceRepository extends AbstractService implements MonitoringService {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void creerImageDeGds(final List<ValeurDeParamètre> valeurDeParamètres,
			final List<UtilisationDeContexteDExécutionAppliqué> prédéfinition_s, final String identSessionNoyau,
			final String identGdS) {
		// Création de imageDeGdS
		ImageDeGdS imageDeGdS = new ImageDeGdS();
		imageDeGdS.setIdentGdS(identGdS);
		final LigneDeJournalAprès ligneDeJournalAprès = this.creerLigneDeJournal(null, valeurDeParamètres, "");
		imageDeGdS.setConfirmation(ligneDeJournalAprès);
		imageDeGdS = this.imageDeGdSRepository.save(imageDeGdS);
		// Fin création de imageDeGdS
		// Création de commandeCoucheHaute associé à l'imageDeGdS
		final CommandeCoucheHaute commandeCoucheHaute = new CommandeCoucheHaute();
		commandeCoucheHaute.setGdsAssocié(imageDeGdS);
		commandeCoucheHaute.setIdentSessionNoyau(identSessionNoyau);
		commandeCoucheHaute.setPrédéfinition_s(prédéfinition_s);
		commandeCoucheHaute.setValuationLocale_s(valeurDeParamètres);
		this.commandeCoucheHauteRepository.save(commandeCoucheHaute);
		// Fin création de commandeCoucheHaute
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LigneDeJournalAprès creerLigneDeJournal(final ÉtatÉlémentaire étatAtteint,
			final List<ValeurDeParamètre> valeurDeParamètres, final String valeursComplémentairesJSON) {
		LigneDeJournalAprès ligneDeJournalAprès = new LigneDeJournalAprès();
		ligneDeJournalAprès.setValeur_s(valeurDeParamètres);
		ligneDeJournalAprès.setÉtatAtteint(étatAtteint);
		ligneDeJournalAprès.setValeursComplémentairesJSON(valeursComplémentairesJSON);
		final int dateHeureMs = (int) (new Date().getTime() / 1000);
		ligneDeJournalAprès.setDateHeureMs(dateHeureMs);
		ligneDeJournalAprès = this.ligneDeJournalAprèsRepository.save(ligneDeJournalAprès);
		return ligneDeJournalAprès;
	}

}
