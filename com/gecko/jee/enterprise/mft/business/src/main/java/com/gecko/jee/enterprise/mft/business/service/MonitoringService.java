package com.gecko.jee.enterprise.mft.business.service;

import java.util.List;

import com.gecko.jee.enterprise.mft.persistence.entity.automate.ÉtatÉlémentaire;
import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ValeurDeParamètre;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;
import com.gecko.jee.enterprise.mft.persistence.entity.instance.LigneDeJournalAprès;

/**
 * <b>Description: Service de persistence des notifications de supervision.</b>
 * <p>
 * La persistence des notifications permettra de construire le tableau de bord
 * de suivi des commandes exécutées.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public interface MonitoringService {

	/**
	 * Cette méthode permet de créer une ImageDeGds qu'on associe à une
	 * commandeCoucheHaute qu'on créé à partir d'une liste d'utilisation de contexte
	 * d'excecution appliqué , une lise de valeurs de paramètre et un identifiant de
	 * session
	 *
	 * @param valeurDeParamètres
	 * @param prédéfinition_s
	 * @param identSessionNoyau
	 * @param identGdS
	 */
	void creerImageDeGds(List<ValeurDeParamètre> valeurDeParamètres,
			List<UtilisationDeContexteDExécutionAppliqué> prédéfinition_s, String identSessionNoyau, String identGdS);

	/**
	 * Cette methode permet de créér une ligne de journal avec les élément qui lui
	 * sont passé en paramètre et de la persister en base
	 *
	 * @param étatAtteint
	 * @param valeur_s
	 * @param valeursComplémentairesJSON
	 * @return ligne de journal persistée en base
	 */
	LigneDeJournalAprès creerLigneDeJournal(ÉtatÉlémentaire étatAtteint, List<ValeurDeParamètre> valeur_s,
			String valeursComplémentairesJSON);
}
