package com.gecko.jee.enterprise.mft.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveau;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveauArgument;

/**
 * <b>Description: DAO pour l'accès aux données des arguments d'une commande
 * macro.</b>
 * <p>
 * Les méthodes CRUD sont déjà implémentées. On peut ajouter ici de nouveaux
 * finders si besoin.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Repository
public interface CommandeHautNiveauArgumentRepository extends JpaRepository<CommandeHautNiveauArgument, Integer> {
	List<CommandeHautNiveauArgument> findByCommandeHautNiveau(CommandeHautNiveau commandeHautNiveau);
}
