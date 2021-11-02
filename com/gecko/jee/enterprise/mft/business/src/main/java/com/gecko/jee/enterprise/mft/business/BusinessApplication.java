package com.gecko.jee.enterprise.mft.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>Description: Classe de configuration Spring du module Business.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@SpringBootApplication
public class BusinessApplication {

	/**
	 * Chargement de la configuration Spring.
	 *
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(BusinessApplication.class, args);
	}
}
