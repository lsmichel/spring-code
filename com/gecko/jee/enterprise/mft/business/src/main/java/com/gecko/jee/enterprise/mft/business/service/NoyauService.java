package com.gecko.jee.enterprise.mft.business.service;

import org.springframework.stereotype.Service;

import com.gecko.jee.enterprise.mft.business.model.StructCommandeNoyau;
import com.gecko.jee.enterprise.mft.business.model.StructRetourCommandeNoyau;

/**
 * <b>Description: Interface du service d'accès au noyau MFT X-Protocols.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Service
public interface NoyauService {

	/**
	 * Permet de créer une session de commandes noyau.
	 *
	 * @param identifiant
	 * @return L'identifiant de session créé.
	 */
	String creerSessionDeCommandes(Object identifiant);

	/**
	 * Démarrage des connecteurs serveurs (FTP, PESIT…).
	 * <ul>
	 * <li>Soit on embarque le serveur FTP, PESIT</li>
	 * <li>Soit le connecteur devra s'accrocher au serveur FTP...</li>
	 * </ul>
	 */
	void demarrerConnecteurs();

	/**
	 * Permet de clôturer/supprimer une session de commandes noyau.
	 *
	 * @param identifiant
	 */
	void supprimerSessionDeCommandes(String identifiant);

	/**
	 * Permet de soumettre une commande noyau au noyau.
	 *
	 * @param identifiantDeSessionDeCommandes l'identifiant de session
	 * @param structureCommandeNoyau          la liste des objets de la commande +
	 *                                        leurs types
	 * @return Réponse du noyau sur la prise en compte de la commande noyau.
	 */
	StructRetourCommandeNoyau traiterCommandeSurSessionDeCommande(String identifiantDeSessionDeCommandes,
			StructCommandeNoyau structureCommandeNoyau);

	/**
	 *
	 * @param identifiantDeSessionDeCommandes
	 * @param ligne
	 * @return
	 */
	StructRetourCommandeNoyau traiterLigneSurSessionDeCommande(String identifiantDeSessionDeCommandes, String ligne);

}
