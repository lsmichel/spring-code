package com.gecko.jee.enterprise.mft.persistence.entity.contexte;

import javax.persistence.CascadeType;
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
 * @created 21-sept.-2021 14:27:47
 */
@Entity(name = "contexteDExecution")
@Table(name = "contexteDExecution")
public class ContexteDExécution {

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "precise", referencedColumnName = "id")
	private ContexteDExécution précisé;

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the ident
	 */
	public String getIdent() {
		return this.ident;
	}

	/**
	 * @return the précisé
	 */
	public ContexteDExécution getPrécisé() {
		return this.précisé;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param ident the ident to set
	 */
	public void setIdent(final String ident) {
		this.ident = ident;
	}

	/**
	 * @param précisé the précisé to set
	 */
	public void setPrécisé(final ContexteDExécution précisé) {
		this.précisé = précisé;
	}

}// end ContexteDExécution
