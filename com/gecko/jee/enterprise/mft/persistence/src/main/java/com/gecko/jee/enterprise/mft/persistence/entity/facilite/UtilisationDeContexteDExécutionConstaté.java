package com.gecko.jee.enterprise.mft.persistence.entity.facilite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ContexteDExécutionConstaté;

/**
 * @author olivier
 * @version 1.0
 * @created 21-sept.-2021 14:59:18
 */
@Entity
@Table(name = "utilisationDeContexteDExecutionConstate")
public class UtilisationDeContexteDExécutionConstaté {

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	@ManyToOne
	@JoinColumn(name = "contexteDExecutionConstate_utilise", referencedColumnName = "id")
	private ContexteDExécutionConstaté utilisé;

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
	 * @return the utilisé
	 */
	public ContexteDExécutionConstaté getUtilisé() {
		return this.utilisé;
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
	 * @param utilisé the utilisé to set
	 */
	public void setUtilisé(final ContexteDExécutionConstaté utilisé) {
		this.utilisé = utilisé;
	}

}// end UtilisationDeContexteDExécutionConstaté
