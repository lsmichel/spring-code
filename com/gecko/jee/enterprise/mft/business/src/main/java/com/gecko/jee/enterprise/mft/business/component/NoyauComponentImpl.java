package com.gecko.jee.enterprise.mft.business.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gecko.jee.enterprise.mft.business.model.StructCommandeNoyau;
import com.gecko.jee.enterprise.mft.exception.BusinessException;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.Affectation;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.Automate;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.Transition;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.TransitionAuto;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.TransitionRdv;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.TransitionSimple;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.État;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.ÉtatÉlémentaire;
import com.gecko.jee.enterprise.mft.persistence.entity.connecteur.Connecteur;
import com.gecko.jee.enterprise.mft.persistence.entity.connecteur.ConnecteurÉlémentaire;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Protocole;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Signal;

/**
 * <b>Description: Composant Business pour les interactions avec le noyau
 * MFT.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Component
public class NoyauComponentImpl extends AbstractComponent implements NoyauComponent {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(NoyauComponentImpl.class);

	/**
	 * Réalise le chargement des données de l'automate pour un protocole.
	 *
	 * @param identifiantSession
	 * @param protocole
	 */
	private void chargementAutomate(final String identifiantSession, final Protocole protocole) {
		// On récupère l'automate du protocole
		final Automate automateCourant = protocole.getAutomate();
		final String verbe = VERBE_N_REPATMT_CR_ATMT;
		StructCommandeNoyau structCommandeNoyau = new StructCommandeNoyau();
		structCommandeNoyau.gi_Cmd.add(verbe);
		structCommandeNoyau.gi_Cmd.add(automateCourant.getIdent());
		// Chargement de l'automate dans le noyau
		this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession, structCommandeNoyau);

		// Chargement des états/transitions de l'automate dans le noyau
		final Set<État> etatCourants = automateCourant.getEtats();
		// On stocke les paramètres déjà envoyés au noyau pour éviter les doublons
		final Map<String, Paramètre> parametresTransitionsEnvoyés = new HashMap<>();
		for (final État etatCourant : etatCourants) {
			// On ne charge que les états élémentaires
			if (etatCourant instanceof ÉtatÉlémentaire) {
				// on recherche toutes les transitions vers l'etat courant
				final List<Transition> transitions = this.transitionRepository
						.findByDestination((ÉtatÉlémentaire) etatCourant);
				for (final Transition transitionCourante : transitions) {
					structCommandeNoyau = new StructCommandeNoyau();
					// Les paramètres entrants/sortants liés à la transition
					final List<Paramètre> parametresTransition = new ArrayList<>();

					structCommandeNoyau.gi_Cmd.add(VERBE_N_REPATMT_AJT_TRAN_ATMT);

					// argument 1
					final String argument_1 = automateCourant.getIdent();
					structCommandeNoyau.gi_Cmd.add(argument_1);

					// argument 2
					final String argument_2 = null;
					structCommandeNoyau.gi_Cmd.add(argument_2);

					// argument 3
					final String argument_3 = transitionCourante.getClass().getSimpleName();
					structCommandeNoyau.gi_Cmd.add(argument_3);

					// argument 4 (et autres)
					String[] argument_4 = null;
					String argument_6 = null;
					String[] argument_7 = null;
					String[] argument_10 = null;
					Object[] argument_11 = null;
					List<Paramètre> parametresEntrants = null;

					if (argument_3.equals(TransitionSimple.class.getSimpleName())
							|| argument_3.equals(TransitionAuto.class.getSimpleName())) {
						argument_4 = new String[1];
						// Après retypage, transitionCourante.origine.ident

						if (argument_3.equals(TransitionSimple.class.getSimpleName())) {
							if (((TransitionSimple) transitionCourante).getOrigine() != null) {
								argument_4[0] = ((TransitionSimple) transitionCourante).getOrigine().getIdent();
							} else {
								argument_4[0] = "";// à confirmer
							}
							argument_6 = ((TransitionSimple) transitionCourante).getEntrant().getIdent();
							if (argument_6 != null && !argument_6.isEmpty()) {
								parametresEntrants = ((TransitionSimple) transitionCourante).getEntrant()
										.getParamètre_s();
							}
						}
						if (argument_3.equals(TransitionAuto.class.getSimpleName())) {
							if (((TransitionAuto) transitionCourante).getOrigine() != null) {
								argument_4[0] = ((TransitionAuto) transitionCourante).getOrigine().getIdent();
							} else {
								argument_4[0] = "";// à confirmer
							}
						}
					} else if (argument_3.equals(TransitionRdv.class.getSimpleName())) {
						final TransitionRdv transitionCouranteRdv = (TransitionRdv) transitionCourante;
						argument_4 = new String[transitionCouranteRdv.getOrigine_s().size()];
						final int i = 0;
						for (final ÉtatÉlémentaire origine : transitionCouranteRdv.getOrigine_s()) {
							argument_4[i] = origine.getIdent();
						}

					}
					structCommandeNoyau.gi_Cmd.add(argument_4);

					// argument 5
					structCommandeNoyau.gi_Cmd.add(etatCourant.getIdent());

					// argument 6
					structCommandeNoyau.gi_Cmd.add(argument_6);

					// argument 7
					if (parametresEntrants != null) {
						int i = 0;
						argument_7 = new String[parametresEntrants.size()];
						for (final Paramètre parametreEntrant : parametresEntrants) {
							argument_7[i] = parametreEntrant.getIdent();
							i++;
						}
						parametresTransition.addAll(parametresEntrants);
					}
					structCommandeNoyau.gi_Cmd.add(argument_7);

					// argument 8
					if (transitionCourante.getGarde() != null) {
						structCommandeNoyau.gi_Cmd.add(transitionCourante.getGarde().getExpression());
					} else {
						structCommandeNoyau.gi_Cmd.add(null);
					}

					// argument 9
					if (transitionCourante.getSortant() != null) {
						structCommandeNoyau.gi_Cmd.add(transitionCourante.getSortant().getIdent());
					} else {
						structCommandeNoyau.gi_Cmd.add(null);
					}

					// argument 10
					if (transitionCourante.getSortant() != null) {
						argument_10 = new String[transitionCourante.getSortant().getParamètre_s().size()];
						for (int i = 0; i < transitionCourante.getSortant().getParamètre_s().size(); i++) {
							final Paramètre paramètreSortant = transitionCourante.getSortant().getParamètre_s().get(i);
							argument_10[i] = paramètreSortant.getIdent();
						}
						parametresTransition.addAll(transitionCourante.getSortant().getParamètre_s());
					}
					structCommandeNoyau.gi_Cmd.add(argument_10);

					// argument 11
					if (transitionCourante.getAffectation_s() != null
							&& transitionCourante.getAffectation_s().size() > 0) {
						argument_11 = transitionCourante.getAffectation_s().stream().map(Affectation::getExpression)
								.collect(Collectors.toCollection(LinkedList::new)).toArray();
						structCommandeNoyau.gi_Cmd.add(argument_11);

					} else {
						structCommandeNoyau.gi_Cmd.add(null);
					}

					// Envoi des paramètres liés à la transition
					for (final Paramètre paramètre : parametresTransition) {
						// On check si le paramètre a déjà été envoyé ou non via une précédente
						// transition
						if (parametresTransitionsEnvoyés.get(paramètre.getIdent()) == null) {
							// _n.ztrav:cr_param_fclt1 //ident// //ident_Automate// //type//
							// //estObligatoire// //exprImpliciteSiFacult// //estImplicitementObjetDeFlux//
							final StructCommandeNoyau structCommandeNoyauParam = new StructCommandeNoyau();
							structCommandeNoyauParam.gi_Cmd.add(VERBE_N_ZTRAV_CR_PARAM_FCLT1);
							structCommandeNoyauParam.gi_Cmd.add(paramètre.getIdent());
							structCommandeNoyauParam.gi_Cmd.add(automateCourant.getIdent());
							structCommandeNoyauParam.gi_Cmd.add(paramètre.getType_());
							structCommandeNoyauParam.gi_Cmd.add(paramètre.getEstObligatoire());
							structCommandeNoyauParam.gi_Cmd.add(paramètre.getExprImpliciteSiFacult());
							structCommandeNoyauParam.gi_Cmd.add(paramètre.getEstImplicitementObjetDeFlux());
							this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession,
									structCommandeNoyauParam);

							// On mémorise qu'on a transmis le paramètre
							parametresTransitionsEnvoyés.put(paramètre.getIdent(), paramètre);
						}
					}

					// Soumission de la commande complète au noyau pour charger les
					// états/transitions de l'automate
					this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession, structCommandeNoyau);
				}
			}
		}
	}

	/**
	 * Réalise le chargement des données des connecteurs.
	 *
	 * @param identifiantSession
	 */
	private void chargementConnecteurs(final String identifiantSession) {
		// Lecture de tous les connecteurs configurés en base
		final List<Connecteur> connecteurs = this.connecteurRepository.findAll();

		for (final Connecteur connecteur : connecteurs) {
			// Constitution du message pour le chargement du connecteur
			final StructCommandeNoyau structCommandeNoyau = new StructCommandeNoyau();
			structCommandeNoyau.gi_Cmd.add(VERBE_N_REPCNCTR_DECL_CNCTR);
			structCommandeNoyau.gi_Cmd.add(connecteur.getIdent());
			structCommandeNoyau.gi_Cmd.add(connecteur.getClass().getSimpleName());

			// Connecteur parent si configuré
			if (connecteur.getParent() != null) {
				structCommandeNoyau.gi_Cmd.add(connecteur.getParent().getIdent());
			}

			// Si connecteur élémentaire: package, tab idents des signaux entrants, tab
			// idents des signaux sortants
			if (connecteur instanceof ConnecteurÉlémentaire) {
				final ConnecteurÉlémentaire connecteurÉlémentaire = (ConnecteurÉlémentaire) connecteur;
				structCommandeNoyau.gi_Cmd.add(connecteurÉlémentaire.getNomCanonique());
				// On envoie un tableau des signaux entrants
				final List<String> signauxEntrantsIdents = connecteurÉlémentaire.getSignauxEntrant_s().stream()
						.collect(Collectors.mapping(Signal::getIdent, Collectors.toList()));
				structCommandeNoyau.gi_Cmd.add(signauxEntrantsIdents.toArray());

				// On envoie un tableau des signaux sortants
				final List<String> signauxSortantsIdents = connecteurÉlémentaire.getSignauxSortant_s().stream()
						.collect(Collectors.mapping(Signal::getIdent, Collectors.toList()));
				structCommandeNoyau.gi_Cmd.add(signauxSortantsIdents.toArray());
			}

			// Appel au noyau pour charger le connecteur
			this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession, structCommandeNoyau);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void chargementNoyau() {
		// Création d'une nouvelle session
		final String identifiantSession = this.noyauService.creerSessionDeCommandes(null);
		// Récupération de la liste de tous les protocols en base
		final List<Protocole> protocoles = this.protocoleRepository.findAll();
		for (final Protocole protocole : protocoles) {
			// Chargement de l'automate lié au protocole
			this.chargementAutomate(identifiantSession, protocole);
		}

		// Chargement de tous les connecteurs configurés en base
		this.chargementConnecteurs(identifiantSession);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void chargementNoyau(final Protocole protocole) throws BusinessException {
		// Lecture de la configuration du protocole en base de données
		final Protocole protocoleCherche = this.protocoleRepository.findByIdent(protocole.getIdent());
		if (protocoleCherche == null) {
			// Aucun protocole identifié avec cet identifiant
			throw new BusinessException(
					this.businessExceptionProperties
							.getProperty("business.exception.validation.protocole.invalide.code"),
					this.businessExceptionProperties.getProperty(
							"business.exception.validation.protocole.invalide.message"),
					protocole.getIdent());
		}

		// Création d'une nouvelle session
		final String identifiantSession = this.noyauService.creerSessionDeCommandes(null);
		// Chargement de l'automate du protocole
		this.chargementAutomate(identifiantSession, protocoleCherche);

		// Chargement de tous les connecteurs configurés en base
		this.chargementConnecteurs(identifiantSession);
	}

}
