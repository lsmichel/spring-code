package com.gecko.jee.enterprise.mft.business.component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.gecko.jee.enterprise.mft.business.model.StructCommandeNoyau;
import com.gecko.jee.enterprise.mft.exception.BusinessException;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.Automate;
import com.gecko.jee.enterprise.mft.persistence.entity.connecteur.Connecteur;
import com.gecko.jee.enterprise.mft.persistence.entity.connecteur.ConnecteurÉlémentaire;
import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ValeurDeParamètre;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionEntrante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelant;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelé;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionEntrante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveau;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveauArgument;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Fonctionnalité;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Signal;
import com.gecko.jee.enterprise.mft.utils.IntegerTypeVerification;

/**
 * <b>Description: Implémentation basée sur les services qui accèdent à la base
 * de données et au noyau.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class CommandeHautNiveauComponentImpl extends AbstractComponent implements CommandeHautNiveauComponent {

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(CommandeHautNiveauComponentImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void controleParametresObligatoires(final List<Fonctionnalité> fonctionnalités,
			final UtilisationDeContexteDExécutionAppliqué partenaireAppele,
			final UtilisationDeContexteDExécutionAppliqué sessionSortante,
			final UtilisationDeContexteDExécutionAppliqué objectifDeSessionSortante,
			final Map<String, String> parametresComplementaires) throws BusinessException {
		// liste des paramètres des fonctionnalités
		final List<Paramètre> paramètresFonctionnalites = this.getParametresFonctionnalités(fonctionnalités);

		// Récuperation de la liste des paramètres des contextes
		// obtenir la liste des paramètre de partenaireAppelant
		final List<Paramètre> paramètresPartenaire = partenaireAppele.getUtilisé().getValeursFixées().stream()
				.map(ValeurDeParamètre::getIdentEtType).collect(Collectors.toList());

		// obtenir la liste des paramètre de sessionEntrante
		final List<Paramètre> paramètresSession = sessionSortante.getUtilisé().getValeursFixées().stream()
				.map(ValeurDeParamètre::getIdentEtType).collect(Collectors.toList());

		// obtenir la liste des paramètre de objectifDeSessionEntrante
		final List<Paramètre> paramètresObjectifDeSessionEntrante = objectifDeSessionSortante.getUtilisé()
				.getValeursFixées().stream().map(ValeurDeParamètre::getIdentEtType).collect(Collectors.toList());
		// merger tous les parametes de contexte
		final List<Paramètre> paramètresContextes = new ArrayList<Paramètre>() {
			{
				this.addAll(paramètresPartenaire);
				this.addAll(paramètresObjectifDeSessionEntrante);
				this.addAll(paramètresSession);

			}
		};

		// si un paramètre est obligatoire et qu il n'est pas das le contexte ou en
		// suplement alors on créer un business exception
		final StringBuilder identifiantsParametresObligatoiresManquants = new StringBuilder();
		for (final Paramètre paramètre : paramètresFonctionnalites) {
			if (paramètre.getEstObligatoire()) {
				final Boolean isParamaterInContexte = paramètresContextes.stream()
						.map(param -> param.getIdent().equals(paramètre.getIdent()))
						.reduce(false, (isParm1, isParm2) -> isParm1 || isParm2);
				final Boolean isParamaterInComplementaire = parametresComplementaires.get(paramètre.getIdent()) != null;
				if (!isParamaterInContexte && !isParamaterInComplementaire) {
					identifiantsParametresObligatoiresManquants.append(paramètre.getIdent()).append(" ");
				}
			}
		}
		if (!identifiantsParametresObligatoiresManquants.toString().isEmpty()) {
			final BusinessException businessException = new BusinessException(
					this.businessExceptionProperties
							.getProperty("business.exception.controleParametresObligatoires.code"),
					this.businessExceptionProperties
							.getProperty("business.exception.controleParametresObligatoires.message"),
					identifiantsParametresObligatoiresManquants.toString());
			throw businessException;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BusinessException> controlerCommandeHautNiveau(final PartenaireAppelant partenaireAppelant,
			final SessionEntrante sessionEntrante, final ObjectifDeSessionEntrante objectifDeSessionEntrante,
			final Map<String, String> parametres) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BusinessException> controlerCommandeHautNiveau(final String commandeEtArguments) {
		final List<BusinessException> businessExceptions = new ArrayList<>();
		if (commandeEtArguments != null && !commandeEtArguments.isEmpty()) {
			List<CommandeHautNiveau> listCommandeHautNiveau = null;

			CommandeHautNiveau commandeHautNiveau = null;
			String[] commandeEtArgumentsSplitedAsArray = commandeEtArguments.split(" ");
			String commandeHauNiveauIdent = null;
			commandeHauNiveauIdent = commandeEtArgumentsSplitedAsArray[0].trim();

			List<String> commandeEtArgumentsSplitedAsList = Arrays.asList(commandeEtArgumentsSplitedAsArray);

			commandeEtArgumentsSplitedAsList = commandeEtArgumentsSplitedAsList.stream().map(String::trim)
					.collect(Collectors.toCollection(LinkedList::new));
			final String[] commandesItems = new String[commandeEtArgumentsSplitedAsList.size()];
			commandeEtArgumentsSplitedAsArray = commandeEtArgumentsSplitedAsList.toArray(commandesItems);
			listCommandeHautNiveau = this.commandeHautNiveauRepository.findByIdent(commandeHauNiveauIdent);
			if (listCommandeHautNiveau == null || listCommandeHautNiveau.isEmpty()) {
				BusinessException businessException;

				businessException = new BusinessException(
						this.businessExceptionProperties
								.getProperty("business.exception.validation.commande.non-trouve.code"),
						this.businessExceptionProperties
								.getProperty("business.exception.validation.commande.non-trouve.message"));

				businessExceptions.add(businessException);
			} else {
				commandeHautNiveau = listCommandeHautNiveau.get(0);
				this.logger.info(String.valueOf(commandeHautNiveau.getCommandeHautNiveauArguments() == null
						|| commandeHautNiveau.getCommandeHautNiveauArguments().size() == 0));
				final Set<CommandeHautNiveauArgument> commandeHautNiveauArguments = commandeHautNiveau
						.getCommandeHautNiveauArguments();
				for (final CommandeHautNiveauArgument commandeHautNiveauArgument : commandeHautNiveauArguments) {
					if (commandeHautNiveauArgument.isObligatoire()) {
						String argumentIdent = null;
						for (int i = 1; i <= commandeEtArgumentsSplitedAsArray.length - 1; i = i + 2) {
							if (commandeEtArgumentsSplitedAsArray[i] != null && commandeEtArgumentsSplitedAsArray[i]
									.equals(commandeHautNiveauArgument.getIdent())) {
								argumentIdent = commandeEtArgumentsSplitedAsArray[i];
								final String argumentValue = commandeEtArgumentsSplitedAsArray[i + 1];
								if (commandeHautNiveauArgument.getType().equals("java.lang.Integer")
										&& !IntegerTypeVerification.isInteger(argumentValue, "java.lang.Integer")) {

									final BusinessException businessException = new BusinessException(
											this.businessExceptionProperties.getProperty(
													"business.exception.validation.commande.argument-type-invalide.code"),
											this.businessExceptionProperties.getProperty(
													"business.exception.validation.commande.argument-type-invalide.message"));
									businessExceptions.add(businessException);

								}
							}
						}
						if (argumentIdent == null) {
							final BusinessException businessException = new BusinessException(
									this.businessExceptionProperties.getProperty(
											"business.exception.validation.commande.argument-obligatoire-manquant.code"),
									this.businessExceptionProperties.getProperty(
											"business.exception.validation.commande.argument-obligatoire-manquant.message")
											+ " >> " + commandeHautNiveauArgument.getIdent());
							businessExceptions.add(businessException);
						}
					}
				}
				for (int i = 1; i <= commandeEtArgumentsSplitedAsArray.length - 1; i = i + 2) {
					final String argumentIdent = commandeEtArgumentsSplitedAsArray[i];
					final CommandeHautNiveauArgument commandeHautNiveauArgument = commandeHautNiveauArguments.stream()
							.filter(cmhArgument -> argumentIdent.equals(cmhArgument.getIdent())).findAny().orElse(null);
					if (commandeHautNiveauArgument == null) {
						final BusinessException businessException = new BusinessException(
								this.businessExceptionProperties
										.getProperty("business.exception.validation.commande.argument-inconnu.code"),
								this.businessExceptionProperties.getProperty(
										"business.exception.validation.commande.argument-inconnu.message"));
						businessExceptions.add(businessException);
					}
				}

			}

		} else {
			// la commande est vide on renvoie une exception
			final BusinessException businessException = new BusinessException(
					this.businessExceptionProperties.getProperty("business.exception.validation.commande.vide.code"),
					this.businessExceptionProperties
							.getProperty("business.exception.validation.commande.vide.message"));
			businessExceptions.add(businessException);
		}
		return businessExceptions;
	}

	/**
	 * Après la création/déclaration des PCA's, il faut créer les flux/paramètres.
	 *
	 *
	 * @param identifiantSession l'identifiant de la session de commande
	 * @param fonctionnalité     la fonctionnalité nécessaire pour la commande de
	 *                           haut niveau
	 * @param identifiantsPCA    Les identifiants PCA's créés précédemment dans
	 *                           creerPCA.
	 */
	private void creerFlux(final String identifiantSession, final Fonctionnalité fonctionnalité,
			final Map<String, Connecteur> identifiantsPCA) {
		// Pour tous les paramètres des fonctionnalités nécessaires,
		// on retrouve ceux qui sont des objets de flux.
		for (final Signal signalFonctionnalite : fonctionnalité.getSignaux()) {
			for (final Paramètre parametreFlux : signalFonctionnalite.getParamètre_s()) {
				if (parametreFlux.getEstImplicitementObjetDeFlux()) {
					// Pour chacun de ces paramètres, on doit envoyer une commande _n.sync:cr_flux
					final StructCommandeNoyau structCommandeNoyau = new StructCommandeNoyau();
					structCommandeNoyau.gi_Cmd.add(NoyauComponent.VERBE_N_SYNC_CR_FLUX);

					final String identifiantFlux = String.valueOf(
							fonctionnalité.getIdent() + "_" + parametreFlux.getIdent()) + Instant.now().toString();
					structCommandeNoyau.gi_Cmd.add(identifiantFlux);
					structCommandeNoyau.gi_Cmd.add(parametreFlux.getIdent());

					// On doit retrouver les paramètres de fonctionnalité (lien direct
					// fonctionnalité, estImplicitementObjetDeFlux = false)
					// pour retrouver les métadonnées de flux: taille_de_poste, taille_de_fenêtre...
					String tailleDePoste = "";
					String tailleDeFenetre = "";
					for (final Paramètre parametreFonction : fonctionnalité.getParamètres()) {
						// Dans l'ident du méta-param on a l'ident du paramètre objet de flux.
						// ex: "_n.sync:param_flux &ident_Paramètre& taille_de_poste"
						if (parametreFonction.getEstImplicitementObjetDeFlux() == false
								&& parametreFonction.getIdent().contains(parametreFlux.getIdent())) {
							// On identifie l'option de configuration de la fonctionnalité
							if (parametreFonction.getIdent().contains(" taille_de_poste")) {
								tailleDePoste = "";
							} else if (parametreFonction.getIdent().contains(" taille_de_fenêtre")) {
								tailleDeFenetre = "";
							}
						}
					}
					structCommandeNoyau.gi_Cmd.add(tailleDePoste);
					structCommandeNoyau.gi_Cmd.add(tailleDeFenetre);

					// Pour ce paramètre d'objet de flux, il faut passer par le signal pour trouver
					// le connecteur.
					final List<String> identifiantsPCASources = new ArrayList<>();
					final List<String> identifiantsPCACibles = new ArrayList<>();
					for (final Signal signalParametre : parametreFlux.getSignal_s()) {
						// signal sortant = PCA source
						// signal entrant = PCA cible
						// On peut donc trouver plusieurs connecteurs en lien avec le paramètre.
						// Cela permet de constituer les listes PCA sources et PCA cibles.
						for (final Connecteur connecteurEntrant : signalParametre.getConnecteurElementaireEntrant_s()) {
							// Il faut retrouver l'identPCA du connecteur/automate pour l'envoyer dans la
							// commande.
							for (final String identifiantPCA : identifiantsPCA.keySet()) {
								if (identifiantPCA.contains(connecteurEntrant.getIdent())) {
									identifiantsPCACibles.add(identifiantPCA);
								}
							}
						}
						for (final Connecteur connecteurSortant : signalParametre.getConnecteurElementaireSortant_s()) {
							// Il faut retrouver l'identPCA du connecteur/automate pour l'envoyer dans la
							// commande.
							for (final String identifiantPCA : identifiantsPCA.keySet()) {
								if (identifiantPCA.contains(connecteurSortant.getIdent())) {
									identifiantsPCASources.add(identifiantPCA);
								}
							}
						}
					}
					structCommandeNoyau.gi_Cmd.add(identifiantsPCASources.toArray());
					structCommandeNoyau.gi_Cmd.add(identifiantsPCACibles.toArray());

					// Une commande cr_flux par paramètre
					// Soumission de la commande de création de flux au noyau
					this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession, structCommandeNoyau);
				}
			}
		}
	}

	/**
	 * Cette méthode envoie le groupe de synchro au noyau.<br>
	 * _n.sync:cr_gds //ident_GDS// //ident_Automate// //liste_de_PCAs//
	 *
	 * @param identifiantSession l'identifiant de la session de commande
	 * @param identifiantsPCA    La liste des identifiants PCA créés dans les
	 *                           commandes noyau précédentes
	 * @param automateGDS        l'automate GDS
	 * @return l'identifiant du groupe de synchro créé.
	 */
	private String creerGDS(final String identifiantSession, final List<String> identifiantsPCA,
			final Automate automateGDS) {
		final StructCommandeNoyau structCommandeNoyau = new StructCommandeNoyau();
		structCommandeNoyau.gi_Cmd.add(NoyauComponent.VERBE_N_SYNC_CR_GDS);

		final String identifiantGDS = String.valueOf(identifiantSession + "_" + automateGDS.getIdent())
				+ Instant.now().toString();
		structCommandeNoyau.gi_Cmd.add(identifiantGDS);
		structCommandeNoyau.gi_Cmd.add(automateGDS.getIdent());
		structCommandeNoyau.gi_Cmd.add(identifiantsPCA.toArray());
		// Envoi au noyau
		this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession, structCommandeNoyau);
		return identifiantGDS;
	}

	/**
	 * Il s'agit de la première commande à envoyer au noyau pour préparer une
	 * exécution de commande de haut niveau. Pour une liste de fonctionnalités
	 * donnée, nous allons identifier toutes les paires connecteur/automate. Ces
	 * paires seront ensuite envoyées au noyau dans le cadre de l'exécution d'une
	 * commande de haut niveau.
	 *
	 * @param identifiantSession l'identifiant de la session de commande
	 * @param fonctionnalité     la fonctionnalité nécessaire pour la commande de
	 *                           haut niveau
	 *
	 * @return Une map des identifiants PCA créés
	 */
	private Map<String, Connecteur> creerPCA(final String identifiantSession, final Fonctionnalité fonctionnalité) {
		// Pour tous les signaux des fonctionnalités nécessaires, on récupère tous les
		// connecteurs
		final Map<Connecteur, List<Signal>> connecteurSignauxMap = new HashMap<>();
		// si pas performant utiliser une boucle while
		for (final Signal signal : fonctionnalité.getSignaux()) {
			// On doit récupérer les connecteurs associés aux signaux entrants et sortants
			for (final ConnecteurÉlémentaire connecteur : signal.getConnecteurElementaireEntrant_s()) {
				if (!connecteurSignauxMap.containsKey(connecteur)) {
					// Le connecteur n'est pas dans la map
					final List<Signal> connecteurSignaux = new ArrayList<>();
					connecteurSignaux.add(signal);
					connecteurSignauxMap.put(connecteur, fonctionnalité.getSignaux());
				} else {
					connecteurSignauxMap.get(connecteur).add(signal);
				}
			}
			for (final ConnecteurÉlémentaire connecteur : signal.getConnecteurElementaireSortant_s()) {
				if (!connecteurSignauxMap.containsKey(connecteur)) {
					// Le connecteur n'est pas dans la map
					final List<Signal> connecteurSignaux = new ArrayList<>();
					connecteurSignaux.add(signal);
					connecteurSignauxMap.put(connecteur, fonctionnalité.getSignaux());
				} else {
					connecteurSignauxMap.get(connecteur).add(signal);
				}
			}
		}

		final Map<String, Connecteur> identifiantsPCA = new HashMap<>();
		for (final Connecteur connecteur : connecteurSignauxMap.keySet()) {
			// Pour chaque connecteur,
			// Génération d'un identifiant de paire Connecteur/Automate
			// on envoie la commande "_n.sync:cr_pca" au noyau
			final String identifiantPCA = String.valueOf(connecteur.getIdent()) + "_"
					+ String.valueOf(fonctionnalité.getProtocole().getIdent()) + Instant.now().toString();
			identifiantsPCA.put(identifiantPCA, connecteur);

			final StructCommandeNoyau structCommandeNoyau = new StructCommandeNoyau();
			structCommandeNoyau.gi_Cmd.add(NoyauComponent.VERBE_N_SYNC_CR_PCA);
			structCommandeNoyau.gi_Cmd.add(identifiantPCA);
			structCommandeNoyau.gi_Cmd.add(connecteur.getIdent());
			structCommandeNoyau.gi_Cmd.add(fonctionnalité.getProtocole().getAutomate().getIdent());
			// Envoi au noyau
			this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession, structCommandeNoyau);

			this.declarerSignauxPCA(identifiantSession, identifiantPCA, connecteurSignauxMap.get(connecteur));
		}
		return identifiantsPCA;
	}

	/**
	 * Pour une liste de signaux associés à un connecteur, on va envoyer les ident
	 * de ces signaux au noyau.
	 *
	 * @param identifiantSession identifiant de session de commande
	 * @param identifiantPCA     identifiant Connecteur/Automate
	 * @param signaux            la liste des signaux du connecteur
	 */
	private void declarerSignauxPCA(final String identifiantSession, final String identifiantPCA,
			final List<Signal> signaux) {

		// _n.sync:decl_signaux_pca //ident_PCA// //ident_Signal//,//ident_Signal//,
		final StructCommandeNoyau structCommandeNoyau = new StructCommandeNoyau();
		structCommandeNoyau.gi_Cmd.add(NoyauComponent.VERBE_N_SYNC_DECL_SIGNAUX_PCA);
		structCommandeNoyau.gi_Cmd.add(identifiantPCA);
		for (final Signal signal : signaux) {
			structCommandeNoyau.gi_Cmd.add(signal.getIdent());
		}
		this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession, structCommandeNoyau);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executerCommandeHautNiveau(final PartenaireAppelé partenaireAppele,
			final SessionSortante sessionSortante, final ObjectifDeSessionSortante objectifDeSessionSortante,
			final Map<String, String> parametresComplementaires) throws BusinessException {

		// les "partenaire, session, objectif de session" sont des contextes d'exécution
		// appliqués qui sont en lien avec des fonctionnalités de protocoles.
		// On extrait les fonctionnalités nécessaires à partir de ces contextes
		// d'exécution pour identifier les signaux, connecteurs et paramètres.
		final List<Fonctionnalité> fonctionnalités = new ArrayList<>();

		// Récupération des contextes d'exécution en base de données pour avoir les
		// fonctionnalités associées.
		final UtilisationDeContexteDExécutionAppliqué partenaireContexteApplique = this.utilisationDeContexteDExecutionAppliqueRepository
				.findByIdent(partenaireAppele.getIdent());
		fonctionnalités.addAll(partenaireContexteApplique.getFonctionnalites());
		final UtilisationDeContexteDExécutionAppliqué sessionContexteApplique = this.utilisationDeContexteDExecutionAppliqueRepository
				.findByIdent(sessionSortante.getIdent());
		fonctionnalités.addAll(sessionContexteApplique.getFonctionnalites());
		final UtilisationDeContexteDExécutionAppliqué objectifSessionContexteApplique = this.utilisationDeContexteDExecutionAppliqueRepository
				.findByIdent(objectifDeSessionSortante.getIdent());
		fonctionnalités.addAll(objectifSessionContexteApplique.getFonctionnalites());

		// Contrôler les paramètres de la commande
		this.controleParametresObligatoires(fonctionnalités, partenaireContexteApplique, sessionContexteApplique,
				objectifSessionContexteApplique, parametresComplementaires);

		// Création d'une session de commande noyau
		final String identifiantSession = this.noyauService.creerSessionDeCommandes(null);

		// Pour chaque fonctionnalité nécessaire
		final List<String> identifiantsPCA = new ArrayList<>();
		for (final Fonctionnalité fonctionnalité : fonctionnalités) {
			// Chargement des paires connecteurs/automates
			final Map<String, Connecteur> identifiantsPCAFonctionnalite = this.creerPCA(identifiantSession,
					fonctionnalité);
			// Récupération des identifiants PCA pour la suite des commandes
			identifiantsPCA.addAll(identifiantsPCAFonctionnalite.keySet());
			// Création des flux pour chaque paramètre
			this.creerFlux(identifiantSession, fonctionnalité, identifiantsPCAFonctionnalite);
		}

		// Il n'y aura tjs qu'un seul automate groupe de synchro.
		final Automate automateGDS = this.rechercherAutomateGDS(fonctionnalités);
		// On doit envoyer cette commande en envoyant tous les identPCA qu'on aura créé
		final String identifiantGDS = this.creerGDS(identifiantSession, identifiantsPCA, automateGDS);

		// Lancement de la commande de haut niveau dans le noyau
		// On doit passer les valeurs des paramètres (affectations)
		this.lancerGDS(identifiantSession, identifiantGDS, partenaireAppele, sessionSortante, objectifDeSessionSortante,
				parametresComplementaires);
		final List<ValeurDeParamètre> valeurDeParamètres = this
				.getValeurDeParamètres(new UtilisationDeContexteDExécutionAppliqué[] { partenaireAppele,
						sessionSortante, objectifDeSessionSortante });
		// Transformation des paramètres complémentaires en valeurs de paramètre
		// Pour tout valeur complémentaire
		for (final String key : parametresComplementaires.keySet()) {
			// On recherche le paramètre associé (la clé du du paramètre complementaire est
			// l'identifiant du *paramètre en base)
			final List<Paramètre> paramètres = this.parametreRepository.findByIdent(key);
			// Si on retouve le paramètre on construit une valeur de paramètre qu'on persite
			// en base . Et on l'ajoute à la liste des valeurs de paramètre pour créer
			// l'image de groupe de synchro
			if (paramètres != null && !paramètres.isEmpty()) {
				ValeurDeParamètre valeurDeParamètre = new ValeurDeParamètre();
				valeurDeParamètre.setIdentEtType(paramètres.get(0));
				valeurDeParamètre.setValeurSérialiséeChnCar(parametresComplementaires.get(key));
				valeurDeParamètre = this.valeurDeParametreRepository.save(valeurDeParamètre);
				valeurDeParamètres.add(valeurDeParamètre);
			}
		}
		// Création d'image de groupe de synchro
		this.monitoringService.creerImageDeGds(valeurDeParamètres,
				Arrays.asList(partenaireAppele, sessionSortante, objectifDeSessionSortante), identifiantSession,
				identifiantGDS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public List<CommandeHautNiveau> getCommandesHautNiveau() {
		final List<CommandeHautNiveau> commandeHautNiveau = this.commandeHautNiveauRepository.findAll();
		return commandeHautNiveau;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Paramètre> getParametresFonctionnalités(final List<Fonctionnalité> fonctionnalites) {
		// Récupération de la liste des signaux à partir de fonctionnalité pour obtenir
		// les paramètres obligatoires.
		// Cette recuperations passe par la recuperation d'une collection de signaux
		// qu'il faut merger en une seule liste de signaux
		final List<List<Signal>> fonctionnalitésSignauxCollection = fonctionnalites.stream()
				.map(Fonctionnalité::getSignaux).collect(Collectors.toList());
		final List<Signal> fonctionnalitésSignaux = fonctionnalitésSignauxCollection.stream()
				.reduce(new ArrayList<Signal>(), (sinal1, signal2) -> new ArrayList<Signal>() {
					{
						this.addAll(sinal1);
						this.addAll(signal2);

					}
				});
		// on recupère a partir des signau une collection de liste de paramettre qu'on
		// merge pour avoir les paramètre obligatoires
		final List<List<Paramètre>> signauxParametresCollection = fonctionnalitésSignaux.stream()
				.map(Signal::getParamètre_s).collect(Collectors.toList());
		// On va supprimer les doublons de paramètres
		final Map<String, Paramètre> paramètresFonctionnalites = new HashMap<>();
		for (final List<Paramètre> paramètres : signauxParametresCollection) {
			for (final Paramètre paramètre : paramètres) {
				paramètresFonctionnalites.put(paramètre.getIdent(), paramètre);
			}
		}
		return new ArrayList(paramètresFonctionnalites.values());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Paramètre> getParametresObligatoires(
			final UtilisationDeContexteDExécutionAppliqué... utilisationDeContexteDExecutionAppliques) {
		// les "partenaire, session, objectif de session" sont des contextes d'exécution
		// appliqués qui sont en lien avec des fonctionnalités de protocoles.
		// On extrait les fonctionnalités nécessaires à partir de ces contextes
		// d'exécution pour identifier les paramètres.
		final List<Fonctionnalité> fonctionnalités = new ArrayList<>();
		final List<Paramètre> paramètresContexte = new ArrayList<>();
		for (final UtilisationDeContexteDExécutionAppliqué utilisationDeContexteDExecutionApplique : utilisationDeContexteDExecutionAppliques) {
			// Récupération des contextes d'exécution en base de données pour avoir les
			// fonctionnalités associées.
			final UtilisationDeContexteDExécutionAppliqué contexteDExecutionApplique = this.utilisationDeContexteDExecutionAppliqueRepository
					.findByIdent(utilisationDeContexteDExecutionApplique.getIdent());
			fonctionnalités.addAll(contexteDExecutionApplique.getFonctionnalites());
			// récuperation de la liste des paramètres de contexte
			final List<Paramètre> parameter = contexteDExecutionApplique.getUtilisé().getValeursFixées().stream()
					.map(ValeurDeParamètre::getIdentEtType).collect(Collectors.toList());
			paramètresContexte.addAll(parameter);
		}

		// liste des paramètres des fonctionnalités
		final List<Paramètre> paramètresFonctionnalites = this.getParametresFonctionnalités(fonctionnalités);
		final List<Paramètre> parametresObligatoiresManquantes = new ArrayList<>();
		// si un paramètre est obligatoire et qu il n'est pas das le contexte
		for (final Paramètre paramètre : paramètresFonctionnalites) {
			if (paramètre.getEstObligatoire()) {
				final Boolean isParamaterInContexte = paramètresContexte.stream()
						.map(param -> param.getIdent().equals(paramètre.getIdent()))
						.reduce(false, (isParm1, isParm2) -> isParm1 || isParm2);
				if (!isParamaterInContexte) {
					parametresObligatoiresManquantes.add(paramètre);

				}
			}
		}
		return parametresObligatoiresManquantes;

	}

	/**
	 * Méthode permettant d'avoir la liste des valeurs de paramètre d'un tableau
	 * d'utilisation de contexte d'execution appliqué
	 *
	 * @param utilisationDeContexteDExecutionAppliques
	 * @return List<ValeurDeParamètre>:la liste des valeurs de paramètre d'un
	 *         tableau d'utilisation de contexte d'execution appliqué
	 */
	private List<ValeurDeParamètre> getValeurDeParamètres(
			final UtilisationDeContexteDExécutionAppliqué... utilisationDeContexteDExecutionAppliques) {
		final List<ValeurDeParamètre> valeurDeParamètres = new ArrayList<>();
		for (final UtilisationDeContexteDExécutionAppliqué utilisationDeContexteDExecutionApplique : utilisationDeContexteDExecutionAppliques) {
			valeurDeParamètres.addAll(utilisationDeContexteDExecutionApplique.getUtilisé().getValeursFixées());
		}
		return valeurDeParamètres;

	}

	/**
	 * Lancement final de la commande de haut niveau dans le noyau.<br>
	 * _n.sync:lancer_gds //ident_GDS// //affectations_initiales//
	 *
	 * @param identifiantSession l'identifiant de la session de commande
	 * @param identifiantGDS     l'identifiant du groupe de synchro
	 */
	private void lancerGDS(final String identifiantSession, final String identifiantGDS,
			final PartenaireAppelé partenaireAppele, final SessionSortante sessionSortante,
			final ObjectifDeSessionSortante objectifDeSessionSortante,
			final Map<String, String> parametresComplementaires) {
		final StructCommandeNoyau structCommandeNoyau = new StructCommandeNoyau();
		structCommandeNoyau.gi_Cmd.add(NoyauComponent.VERBE_N_SYNC_LANCER_GDS);
		structCommandeNoyau.gi_Cmd.add(identifiantGDS);

		// Ajout des affectations_initiales = on envoie ces paramètres sous la forme de
		// pojo ValeurDeParamètre
		// Valeurs de paramètres issus des contextes d'exécution
		structCommandeNoyau.gi_Cmd.addAll(partenaireAppele.getUtilisé().getValeursFixées());
		structCommandeNoyau.gi_Cmd.addAll(sessionSortante.getUtilisé().getValeursFixées());
		structCommandeNoyau.gi_Cmd.addAll(objectifDeSessionSortante.getUtilisé().getValeursFixées());

		// Valeurs de paramètres saisis par l'utilisateur
		for (final String identParametre : parametresComplementaires.keySet()) {
			final ValeurDeParamètre valeurDeParamètreSaisi = new ValeurDeParamètre();
			final Paramètre paramètreSaisi = new Paramètre();
			paramètreSaisi.setIdent(identParametre);
			valeurDeParamètreSaisi.setIdentEtType(paramètreSaisi);
			valeurDeParamètreSaisi.setValeurSérialiséeChnCar(parametresComplementaires.get(identParametre));
			structCommandeNoyau.gi_Cmd.add(valeurDeParamètreSaisi);
		}

		// Envoi au noyau
		this.noyauService.traiterCommandeSurSessionDeCommande(identifiantSession, structCommandeNoyau);
	}

	/**
	 * Cette methode permet de retrouver l'automate GDS (groupe de synchro) en
	 * partant d'une liste de fonctionnalités identifiées.
	 *
	 * @param fonctionnalités Liste des fonctionnalités nécessaires pour une
	 *                        commande de haut niveau
	 *
	 * @return L'automate GDS
	 * @throws BusinessException si aucun automate GDS n'a été trouvé, ou bien si on
	 *                           en a trouvé plusieurs.
	 */
	private Automate rechercherAutomateGDS(final List<Fonctionnalité> fonctionnalités) throws BusinessException {
		// Recherche de toutes les automates liés aux fonctionalités
		final List<Automate> fonctionaliteAutomates = fonctionnalités.stream()
				.map(fonctionalite -> fonctionalite.getProtocole().getAutomate()).collect(Collectors.toList());
		// Parcours de la liste des automates trouvés pour ne retenir que ceux qui sont
		// GDS
		final List<Automate> automatesGDS = new ArrayList<>();
		for (final Automate automate : fonctionaliteAutomates) {
			if (automate.getEstDeGdS()) {
				automatesGDS.add(automate);
			}
		}
		if (automatesGDS.size() == 0) {
			// On ne trouve aucun automate GDS --> exception
			final BusinessException businessException = new BusinessException(
					this.businessExceptionProperties
							.getProperty("business.exception.rechercherAutomateGDS.automateGDSNonTrouve.code"),
					this.businessExceptionProperties
							.getProperty("business.exception.rechercherAutomateGDS.automateGDSNonTrouve.message"));
			throw businessException;
		}
		if (automatesGDS.size() > 1) {
			// On trouve plusieurs automates GDS -> exception
			final BusinessException businessException = new BusinessException(
					this.businessExceptionProperties
							.getProperty("business.exception.rechercherAutomateGDS.plusieursAutomatesGDSTrouves.code"),
					this.businessExceptionProperties.getProperty(
							"business.exception.rechercherAutomateGDS.plusieursAutomatesGDSTrouves.message"));
			throw businessException;
		}
		// Si non on ramène le seul automate GDS
		return automatesGDS.get(0);

	}

}
