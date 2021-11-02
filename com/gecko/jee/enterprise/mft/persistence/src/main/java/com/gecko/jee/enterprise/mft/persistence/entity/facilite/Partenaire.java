package com.gecko.jee.enterprise.mft.persistence.entity.facilite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author olivier
 * @version 1.0
 * @created 23-sept.-2021 14:39:04
 */
@Entity
@Table(name = "partenaire")
public class Partenaire {

	@OneToOne
	@JoinColumn(name = "commeAppelant", referencedColumnName = "id")
	private PartenaireAppelant commeAppelant;

	@OneToOne
	@JoinColumn(name = "commeAppele", referencedColumnName = "id")
	private PartenaireAppelé commeAppelé;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * @return the commeAppelant
	 */
	public PartenaireAppelant getCommeAppelant() {
		return this.commeAppelant;
	}

	/**
	 * @return the commeAppelé
	 */
	public PartenaireAppelé getCommeAppelé() {
		return this.commeAppelé;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param commeAppelant the commeAppelant to set
	 */
	public void setCommeAppelant(final PartenaireAppelant commeAppelant) {
		this.commeAppelant = commeAppelant;
	}

	/**
	 * @param commeAppelé the commeAppelé to set
	 */
	public void setCommeAppelé(final PartenaireAppelé commeAppelé) {
		this.commeAppelé = commeAppelé;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

}// end Partenaire
