package com.gecko.jee.enterprise.mft.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <b>Description: Classe de configuration Spring du module persistance </b>
 * <p>
 * Quand La methode main est lancé au démarage de l'application. Il effectue des
 * actions sur la base de donnée en fonction des configurations qui on été mise
 * dans le fichier properties
 * </p>
 * <p/>
 * Pour lancer l'application avec commande: java -jar
 * mft-persistence.<version>.jar s'il y a de nouvelles modiffications sur
 * entités
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@SpringBootApplication
@ComponentScan("com.gecko.jee.enterprise.mft.persistence")
public class PersistenceApplication {
	public static void main(final String[] args) {
		SpringApplication.run(PersistenceApplication.class, args);
	}
}
