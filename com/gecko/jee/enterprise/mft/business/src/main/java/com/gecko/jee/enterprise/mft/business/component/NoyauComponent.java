package com.gecko.jee.enterprise.mft.business.component;

import org.springframework.stereotype.Component;

import com.gecko.jee.enterprise.mft.exception.BusinessException;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Protocole;

/**
 * <b>Description: Composant Business pour les interactions avec le noyau
 * MFT.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Component
public interface NoyauComponent {

	/**
	 * Verbe pour l'ajout des transitions dans un automate du noyau
	 */
	public static final String VERBE_N_REPATMT_AJT_TRAN_ATMT = "_n.repatmt:ajt_tran_atmt";

	/**
	 * Verbe pour la création d'un automate dans le noyau
	 */
	public static final String VERBE_N_REPATMT_CR_ATMT = "_n.repatmt:cr_atmt";

	/**
	 * Verbe pour la création d'un connecteur dans le noyau
	 */
	public static final String VERBE_N_REPCNCTR_DECL_CNCTR = "_n.repcnctr:decl_cnctr";
	/**
	 * Verbe pour la création d'un flux de paramètre / PCA's
	 */
	public static final String VERBE_N_SYNC_CR_FLUX = "_n.sync:cr_flux";

	/**
	 * Verbe pour la création d'un groupe de synchro (automate du milieu)
	 */
	public static final String VERBE_N_SYNC_CR_GDS = "_n.sync:cr_gds";

	/**
	 * Verbe pour la création de commande paire connecteur/automate
	 */
	public static final String VERBE_N_SYNC_CR_PCA = "_n.sync:cr_pca";

	/**
	 * Verbe pour la déclaration des signaux paire connecteur/automate
	 */
	public static final String VERBE_N_SYNC_DECL_SIGNAUX_PCA = "_n.sync:decl_signaux_pca ";

	/**
	 * Verbe pour l'exécution d'une commande de haut niveau.
	 */
	public static final String VERBE_N_SYNC_LANCER_GDS = "_n.sync:lancer_gds";

	/**
	 * Verbe pour la création d'un paramètre dans un automate du noyau
	 */
	public static final String VERBE_N_ZTRAV_CR_PARAM_FCLT1 = "_n.ztrav:cr_param_fclt1";

	/**
	 * Cette méthode exécute plusieurs actions en séquence pour l'initialisation du
	 * noyau et le démarrage des services sous jacents.
	 * <ul>
	 * <li>Lecture des configurations des protocoles et connecteurs en base de
	 * données</li>
	 * <li>Charger les X protocoles dans le noyau (envois de commande "noyau")</li>
	 * <li>Démarrage des connecteurs serveurs (FTP, PESIT…)</li>
	 * </ul>
	 */
	void chargementNoyau();

	/**
	 * Cette méthode exécute plusieurs actions en séquence pour l'initialisation du
	 * noyau et le démarrage des services sous jacents.
	 * <ul>
	 * <li>Lecture des configurations du protocole indiqué et connecteurs en base de
	 * données</li>
	 * <li>Charger le protocole dans le noyau (envois de commande "noyau")</li>
	 * <li>Démarrage des connecteurs serveurs associés (FTP, PESIT…)</li>
	 * </ul>
	 *
	 * @param protocole le protocole contenant l'identifiant
	 * @throws BusinessException si aucun protocole trouvé avec l'identifiant fourni
	 */
	void chargementNoyau(Protocole protocole) throws BusinessException;

}
