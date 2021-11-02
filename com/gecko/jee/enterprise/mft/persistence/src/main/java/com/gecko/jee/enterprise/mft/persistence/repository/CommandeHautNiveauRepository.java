package com.gecko.jee.enterprise.mft.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveau;

/**
 * <b>Description: DAO pour l'accès aux données des commandes macro.</b>
 * <p>
 * Les méthodes CRUD sont déjà implémentées. On peut ajouter ici de nouveaux
 * finders si besoin.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Repository
public interface CommandeHautNiveauRepository extends JpaRepository<CommandeHautNiveau, Integer> {

	/**
	 *
	 * @param ident
	 * @return
	 */
	List<CommandeHautNiveau> findByIdent(String ident);

}
