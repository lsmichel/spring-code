package com.gecko.jee.enterprise.mft.business.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gecko.jee.enterprise.mft.business.BusinessApplication;
import com.gecko.jee.enterprise.mft.business.BusinessApplicationTest;
import com.gecko.jee.enterprise.mft.exception.BusinessException;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Fonctionnalité;
import com.gecko.jee.enterprise.mft.persistence.initialize.PersistenceApplicationInitializer;
import com.gecko.jee.enterprise.mft.persistence.repository.facilite.UtilisationDeContexteDExecutionAppliqueRepository;

@SpringBootTest(classes = { BusinessApplication.class, BusinessApplicationTest.class })
@RunWith(SpringRunner.class)
public class CommandeHautNiveauComponentTest {

	/**
	 * Fichier de messages des exceptions business
	 */
	private Properties businessExceptionProperties;

	/**
	 * Composant business testé
	 */
	@Autowired
	CommandeHautNiveauComponent commandeHautNiveauComponent;

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(CommandeHautNiveauComponentTest.class);

	/**
	 * Composant pour charger les commandes de haut niveau dans la base de données
	 */
	@Autowired
	PersistenceApplicationInitializer persistenceApplicationInitializer;

	/**
	 * Repository (JPA) pour utilisationDeContexteDExecutionApplique
	 */
	@Autowired
	protected UtilisationDeContexteDExecutionAppliqueRepository utilisationDeContexteDExecutionAppliqueRepository;

	@Transactional
	@Test
	public void controleParametresObligatoires() {
		final List<Fonctionnalité> fonctionnalités = new ArrayList<>();
		final UtilisationDeContexteDExécutionAppliqué partenaireContexteApplique = this.utilisationDeContexteDExecutionAppliqueRepository
				.findByIdent("partenaire_appelé_fictif");
		fonctionnalités.addAll(partenaireContexteApplique.getFonctionnalites());
		final UtilisationDeContexteDExécutionAppliqué sessionContexteApplique = this.utilisationDeContexteDExecutionAppliqueRepository
				.findByIdent("fichier01_lecture");
		fonctionnalités.addAll(sessionContexteApplique.getFonctionnalites());
		final UtilisationDeContexteDExécutionAppliqué objectifSessionContexteApplique = this.utilisationDeContexteDExecutionAppliqueRepository
				.findByIdent("objectif_session_sortante_fictif");
		fonctionnalités.addAll(objectifSessionContexteApplique.getFonctionnalites());

		final Map<String, String> parametresComplementaires = new HashMap<>();
		parametresComplementaires.put("i_chemin_fic", "C:/toto.txt");

		try {
			this.commandeHautNiveauComponent.controleParametresObligatoires(fonctionnalités, partenaireContexteApplique,
					sessionContexteApplique, objectifSessionContexteApplique, parametresComplementaires);
		} catch (final BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Commande avec des paramètres manquantes
	 */
	@Transactional
	@Test
	public void controlerCommandeHautNiveauTest() {
		final String commandeEtArguments = "ftp-put -u lsmichel -p d";
		final List<BusinessException> businessExceptions = this.commandeHautNiveauComponent
				.controlerCommandeHautNiveau(commandeEtArguments);
		assertNotNull(businessExceptions);
		assertEquals(businessExceptions.size(), 4);
		assertEquals(businessExceptions.get(0).getCode(), this.businessExceptionProperties
				.getProperty("business.exception.validation.commande.argument-obligatoire-manquant.code"));
	}

	/**
	 * Commande inexistante
	 */
	@Transactional
	@Test
	public void controlerCommandeHautNiveauTestArgumentInvalide() {
		final String commandeEtArgumentsTes4 = "ls";
		final List<BusinessException> businessExceptions = this.commandeHautNiveauComponent
				.controlerCommandeHautNiveau(commandeEtArgumentsTes4);
		assertNotNull(businessExceptions);
		assertEquals(businessExceptions.size(), 1);
		assertEquals(businessExceptions.get(0).getCode(),
				this.businessExceptionProperties.getProperty("business.exception.validation.commande.non-trouve.code"));

	}

	/**
	 * Argument invalide
	 */
	@Transactional
	@Test
	public void controlerCommandeHautNiveauTestComandeInexistant() {
		final String commandeEtArgumentsTes2 = "ftp-put -u lsmichel -p franchise -k manupulation";
		final List<BusinessException> businessExceptions = this.commandeHautNiveauComponent
				.controlerCommandeHautNiveau(commandeEtArgumentsTes2);
		assertNotNull(businessExceptions);
		final BusinessException businessException = businessExceptions.get(businessExceptions.size() - 1);
		assertEquals(businessException.getCode(), this.businessExceptionProperties
				.getProperty("business.exception.validation.commande.argument-inconnu.code"));
	}

	/**
	 * Commande vide
	 */
	@Transactional
	@Test
	public void controlerCommandeHautNiveauTestCommandeVide() {
		final String commandeEtArgumentsTes3 = "";
		final List<BusinessException> businessExceptions = this.commandeHautNiveauComponent
				.controlerCommandeHautNiveau(commandeEtArgumentsTes3);
		assertNotNull(businessExceptions);
		final BusinessException businessException = businessExceptions.get(businessExceptions.size() - 1);
		assertEquals(businessException.getCode(),
				this.businessExceptionProperties.getProperty("business.exception.validation.commande.vide.code"));
	}

	/**
	 * Initialisation avant de démarrer les tests: chargement des commandes de haut
	 * niveau, fichier properties des exceptions
	 */
	@PostConstruct
	private void initializeCommandeHautNiveauComponentTest() {
		final Resource resource = new ClassPathResource("/businessException.properties");
		try {
			this.businessExceptionProperties = PropertiesLoaderUtils.loadProperties(resource);
		} catch (final IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		this.persistenceApplicationInitializer.chargerFtpPut();
	}

}
