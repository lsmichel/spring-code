package com.gecko.jee.enterprise.mft.persistence.entity.connecteur;

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
 * @created 29-sept.-2021 10:31:41
 */
@Entity
@Table(name = "connecteur")
public class Connecteur {

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "connecteurCompositeParent_id", referencedColumnName = "id")
	private ConnecteurComposite parent;

	public Connecteur() {

	}

	@Override
	public void finalize() throws Throwable {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	public String getIdent() {
		return this.ident;
	}

	public ConnecteurComposite getParent() {
		return this.parent;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	public void setIdent(final String ident) {
		this.ident = ident;
	}

	public void setParent(final ConnecteurComposite parent) {
		this.parent = parent;
	}

}// end Connecteur
