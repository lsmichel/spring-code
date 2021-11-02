package com.gecko.jee.enterprise.mft.persistence.repository.facilite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;

/**
 * <b>Description: Repository pour la manipulation des données des contextes
 * d'exécutions appliqués.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@NoRepositoryBean
public interface UtilisationDeContexteDExecutionAppliqueBaseRepository<T extends UtilisationDeContexteDExécutionAppliqué>
		extends JpaRepository<T, Integer> {

	/**
	 * Récupération du contexte par son identifiant MFT.
	 *
	 * @param ident l'identifiant de protocole MFT recherché
	 * @return toutes les données du contexte
	 */
	UtilisationDeContexteDExécutionAppliqué findByIdent(String ident);
}
