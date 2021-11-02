package com.gecko.jee.enterprise.mft.business.service;

public class NotificationInfo {

	/**
	 * identifiant de session pour un utilisateur qui execute une commande
	 */
	private String identifiantSessionCommande;

	/**
	 * information idiquant si l excecution d'une commande c'est bien deroulévaleur
	 * possible (succes/error)
	 */
	private String level;

	/**
	 * message renvoyé par le noyau pour secces ou error
	 */
	private String message;

	/**
	 * identifiant de session d'un utilisateur
	 */
	private String utilisateurSessesionId;

	/**
	 * @return the identifiantSessionCommande
	 */
	public String getIdentifiantSessionCommande() {
		return this.identifiantSessionCommande;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return this.level;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @return the utilisateurSessesionId
	 */
	public String getUtilisateurSessesionId() {
		return this.utilisateurSessesionId;
	}

	/**
	 * @param identifiantSessionCommande the identifiantSessionCommande to set
	 */
	public void setIdentifiantSessionCommande(final String identifiantSessionCommande) {
		this.identifiantSessionCommande = identifiantSessionCommande;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(final String level) {
		this.level = level;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * @param utilisateurSessesionId the utilisateurSessesionId to set
	 */
	public void setUtilisateurSessesionId(final String utilisateurSessesionId) {
		this.utilisateurSessesionId = utilisateurSessesionId;
	}

}
