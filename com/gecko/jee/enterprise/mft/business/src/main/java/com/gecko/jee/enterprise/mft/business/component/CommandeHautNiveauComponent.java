package com.gecko.jee.enterprise.mft.business.component;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gecko.jee.enterprise.mft.exception.BusinessException;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionEntrante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelant;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelé;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionEntrante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveau;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Fonctionnalité;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;

/**
 * <b>Description: Composant Business pour les commandes macro.</b>
 * <p>
 * Il s'appuie sur un ensemble de services qui vont accéder au noyau ou à la
 * base de données.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Service
public interface CommandeHautNiveauComponent {

	/**
	 * On recupère la liste de tous les parametres de contexte. On parcour la liste
	 * des paramètres obligatoires et on fait le controle pour savoir si le
	 * parametre obligatoire est dans le contexte ou en complement. Si non on ajoute
	 * une businessException pour signifier qu'un paramètre obligatoire est manquat
	 *
	 * @param fonctionnalités           liste des fonctinnalité qui mènenet aux
	 *                                  paramètres obligatoire
	 * @param partenaireAppele          contexte d'exécution avec ces paramètres
	 *                                  valorisés
	 * @param sessionSortante           contexte d'exécution avec ces paramètres
	 *                                  valorisés
	 * @param objectifDeSessionSortante contexte d'exécution avec ces paramètres
	 *                                  valorisés
	 * @param parametresComplementaires Liste des paramètres en complement
	 * @throws BusinessException
	 */
	void controleParametresObligatoires(final List<Fonctionnalité> fonctionnalités,
			final UtilisationDeContexteDExécutionAppliqué partenaireAppele,
			final UtilisationDeContexteDExécutionAppliqué sessionSortante,
			final UtilisationDeContexteDExécutionAppliqué objectifDeSessionSortante,
			final Map<String, String> parametresComplementaires) throws BusinessException;

	/**
	 * Cette méthode a en charge le contrôle d'intégrité de la commande "haut
	 * niveau" transmise par la webapp / cli. Elle vérifie:
	 * <ul>
	 * <li>l'existence de la session et de l'objectif de session</li>
	 * <li>les arguments obligatoires</li>
	 * <li>les valeurs des arguments par rapport au type attendu</li>
	 * </ul>
	 *
	 * @param partenaireAppelant        Identifiant du partenaire (permet de
	 *                                  récupérer des informations complémentaires
	 *                                  dans le contexte)
	 * @param sessionEntrante           Identifiant de la session (permet de
	 *                                  récupérer des informations complémentaires
	 *                                  dans le contexte)
	 * @param objectifDeSessionEntrante Identifiant de l'objectif de session (permet
	 *                                  de récupérer des informations
	 *                                  complémentaires dans le contexte)
	 * @param parametres                Paramètres complémentaires de la commande
	 * @return
	 */
	List<BusinessException> controlerCommandeHautNiveau(PartenaireAppelant partenaireAppelant,
			SessionEntrante sessionEntrante, ObjectifDeSessionEntrante objectifDeSessionEntrante,
			Map<String, String> parametres);

	/**
	 * Cette méthode a en charge le contrôle d'intégrité de la commande transmise
	 * par la webapp / cli. Elle vérifie:
	 * <ul>
	 * <li>l'existence de l'identifiant de la commande de haut niveau</li>
	 * <li>les arguments obligatoires</li>
	 * <li>les valeurs des arguments par rapport au type attendu</li>
	 * </ul>
	 *
	 * @param commandeEtArguments la ligne complète de la commande et ses arguments
	 *                            envoyée par la webapp/cli. Exemple: ftp-put -u xxx
	 *                            -d yyyy
	 * @return Une liste de BusinessException contenant les détails des erreurs
	 *         identifiées.
	 */
	List<BusinessException> controlerCommandeHautNiveau(String commandeEtArguments);

	/**
	 * Cette méthode va interpréter la commande de haut niveau en commandes Noyau.
	 * Le contrôle de la commande sera réalisé avant l'appel au noyau.
	 *
	 * @param partenaireAppelant        Identifiant du partenaire (permet de
	 *                                  récupérer des informations complémentaires
	 *                                  dans le contexte)
	 * @param sessionEntrante           Identifiant de la session (permet de
	 *                                  récupérer des informations complémentaires
	 *                                  dans le contexte)
	 * @param objectifDeSessionEntrante Identifiant de l'objectif de session (permet
	 *                                  de récupérer des informations
	 *                                  complémentaires dans le contexte)
	 * @param parametres                Paramètres complémentaires de la commande
	 * @throws BusinessException
	 */
	void executerCommandeHautNiveau(final PartenaireAppelé partenaireAppele, final SessionSortante sessionSortant,
			final ObjectifDeSessionSortante objectifDeSessionSortante,
			final Map<String, String> parametresComplementaires) throws BusinessException;

	/**
	 * Cette methode récupère la liste des commandes macros disopnibles (nom,
	 * description, arguments). Cela pourra être utilisé dans l'application web pour
	 * construire dynamiquement les formulaires, ou bien dans le module cli pour
	 * l'aide sur les commandes.
	 *
	 * @return la liste des commandes macros disponibles
	 */
	List<CommandeHautNiveau> getCommandesHautNiveau();

	/**
	 * Cette methode retourne la liste des paramètres obligatoires pour une liste de
	 * fonctionnalités
	 *
	 * @return List<Paramètre>: la liste des paramètres obligatoires
	 */
	List<Paramètre> getParametresFonctionnalités(List<Fonctionnalité> fonctionnalites);

	/**
	 * Renvoie la liste des paramètres obligatoires pour une liste de contextes
	 * d'execution
	 *
	 * @param utilisationDeContexteDExecutionAppliques
	 * @return List<Paramètre> : la liste des paramètres obligatoires
	 */
	List<Paramètre> getParametresObligatoires(
			UtilisationDeContexteDExécutionAppliqué... utilisationDeContexteDExecutionAppliques);
}
