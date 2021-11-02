package com.gecko.jee.enterprise.mft.business.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gecko.jee.enterprise.mft.business.BusinessApplication;
import com.gecko.jee.enterprise.mft.business.BusinessApplicationTest;

@SpringBootTest(classes = { BusinessApplication.class, BusinessApplicationTest.class })
@RunWith(SpringRunner.class)
public class NoyauServiceTest {

	private final Logger logger = LoggerFactory.getLogger(NoyauServiceTest.class);

	@Autowired
	NoyauService noyauService;

	/**
	 * Scénarios de tests de création de session de commandes noyau.
	 */
	@Test
	public void creerSessionDeCommandes() {
		// Sécnario 1 : création d'une nouvelle session
		try {
			final String identifiantSession = this.noyauService.creerSessionDeCommandes(null);
			assertNotNull(identifiantSession);
			this.logger.info("************ Identifiant créé: " + identifiantSession);
		} catch (final Exception ex) {
			ex.printStackTrace();
			fail(ex.getMessage());
		}
	}
}
