package com.gecko.jee.enterprise.mft.cli;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.gecko.jee.enterprise.mft.business.CommandeHautNiveauComponentMock;
import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponent;

/**
 * <b>Description: Classe de configuration Spring pour les tests unitaires.</b>
 * <p>
 * Les beans déclarés ici surchargent ceux de la configuration primaire
 * {@link CliApplication}.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@TestConfiguration
public class CliApplicationTest {

	/**
	 * Surcharge du bean CommandeHautNiveauComponent pour injecter un mock.
	 *
	 * @return
	 */
	@Bean
	public CommandeHautNiveauComponent commandeHautNiveauComponent() {
		return new CommandeHautNiveauComponentMock();
	}

}
