package com.gecko.jee.enterprise.mft.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gecko.jee.enterprise.mft.persistence.initialize.PersistenceApplicationInitializer;
import com.gecko.jee.enterprise.mft.persistence.repository.CommandeHautNiveauArgumentRepository;
import com.gecko.jee.enterprise.mft.persistence.repository.CommandeHautNiveauRepository;

/**
 * <b>Description: Test unitaire JPA général. Peut être utilisé pour créer les
 * tables dans PostgreSQL.</b>
 * <p>
 * <ul>
 * <li>Replace.NONE = utilise la config de src/main et pointe sur
 * PostgreSQL</li>
 * <li>Replace.ANY = utilise la config de src/test et pointe sur H2</li>
 * </ul>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class PersistenceComponentTest {

	@Autowired
	private CommandeHautNiveauArgumentRepository commandeHautNiveauArgumentRepository;

	@Autowired
	private CommandeHautNiveauRepository commandeHautNiveauRepository;

	private final Logger logger = LoggerFactory.getLogger(PersistenceComponentTest.class);

	@Autowired
	private PersistenceApplicationInitializer persistenceApplicationInitializer;

	@Test
	public void insertAndSelectInCommandeMacro() {
//		this.persistenceApplicationInitializer.chargerFtpPut();
//		final List<CommandeHautNiveau> commandeHautNiveau = this.commandeHautNiveauRepository.findAll();
//		final List<CommandeHautNiveauArgument> commandeHautNiveauArguments = this.commandeHautNiveauArgumentRepository
//				.findByCommandeHautNiveau(commandeHautNiveau.get(0));
//		this.logger.info("ok");
	}
}
