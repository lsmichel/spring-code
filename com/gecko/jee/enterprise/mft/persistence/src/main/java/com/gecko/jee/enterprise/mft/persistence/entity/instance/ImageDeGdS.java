package com.gecko.jee.enterprise.mft.persistence.entity.instance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author olivier
 * @version 1.0
 * @created 21-oct.-2021 14:25:15
 */
@Entity
public class ImageDeGdS {

	@ManyToOne
	@JoinColumn(name = "ligneDeJournalApres_Confirmation_id")
	private LigneDeJournalAprès confirmation;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String identGdS;

	/**
	 * @return the confirmation
	 */
	public LigneDeJournalAprès getConfirmation() {
		return this.confirmation;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the identGdS
	 */
	public String getIdentGdS() {
		return this.identGdS;
	}

	/**
	 * @param confirmation the confirmation to set
	 */
	public void setConfirmation(final LigneDeJournalAprès confirmation) {
		this.confirmation = confirmation;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param identGdS the identGdS to set
	 */
	public void setIdentGdS(final String identGdS) {
		this.identGdS = identGdS;
	}

}// end ImageDeGdS
