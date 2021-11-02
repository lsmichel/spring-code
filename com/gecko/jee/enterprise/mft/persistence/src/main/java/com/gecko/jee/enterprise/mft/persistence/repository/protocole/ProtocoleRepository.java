package com.gecko.jee.enterprise.mft.persistence.repository.protocole;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Protocole;

/**
 * <b>Description: Repository pour la manipulation des données des
 * protocoles.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public interface ProtocoleRepository extends JpaRepository<Protocole, Integer> {

	/**
	 * Récupération du protocole par son identifiant MFT.
	 *
	 * @param ident l'identifiant de protocole MFT recherché
	 * @return toutes les données du protocole
	 */
	Protocole findByIdent(String ident);
}
