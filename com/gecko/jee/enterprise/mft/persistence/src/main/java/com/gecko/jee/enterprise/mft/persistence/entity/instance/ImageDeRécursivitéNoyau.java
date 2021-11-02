package com.gecko.jee.enterprise.mft.persistence.entity.instance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author olivier
 * @version 1.0
 * @created 21-oct.-2021 14:25:15
 */
@Entity
@Table(name = "imageDeRecursiviteNoyau")
public class ImageDeRécursivitéNoyau {

	@ManyToOne
	@JoinColumn(name = "ImageDeGdS_Createur_id")
	private ImageDeGdS créateur;

	@OneToOne
	@JoinColumn(name = "ImageDeGdS_Cree_id", referencedColumnName = "id")
	private ImageDeGdS créé;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "ligneDeJournalApres_notification_id", referencedColumnName = "id")
	private LigneDeJournalAprès notification;

	/**
	 * @return the créateur
	 */
	public ImageDeGdS getCréateur() {
		return this.créateur;
	}

	/**
	 * @return the créé
	 */
	public ImageDeGdS getCréé() {
		return this.créé;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the notification
	 */
	public LigneDeJournalAprès getNotification() {
		return this.notification;
	}

	/**
	 * @param créateur the créateur to set
	 */
	public void setCréateur(final ImageDeGdS créateur) {
		this.créateur = créateur;
	}

	/**
	 * @param créé the créé to set
	 */
	public void setCréé(final ImageDeGdS créé) {
		this.créé = créé;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param notification the notification to set
	 */
	public void setNotification(final LigneDeJournalAprès notification) {
		this.notification = notification;
	}

}// end ImageDeRécursivitéNoyau
