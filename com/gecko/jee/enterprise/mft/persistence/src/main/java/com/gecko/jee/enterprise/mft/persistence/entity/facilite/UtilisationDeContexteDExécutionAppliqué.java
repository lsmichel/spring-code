package com.gecko.jee.enterprise.mft.persistence.entity.facilite;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ContexteDExécutionAppliqué;
import com.gecko.jee.enterprise.mft.persistence.entity.instance.CommandeCoucheHaute;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Fonctionnalité;

/**
 * @author olivier
 * @version 1.0
 * @created 21-sept.-2021 14:59:18
 */
@Entity
@Table(name = "utilisationDeContexteDExecutionApplique")
public class UtilisationDeContexteDExécutionAppliqué {

	@ManyToMany(mappedBy = "prédéfinition_s")
	private List<CommandeCoucheHaute> commandeCoucheHaute_s;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	@ManyToOne
	@JoinColumn(name = "contexteDExecutionApplique_utilise", referencedColumnName = "id")
	private ContexteDExécutionAppliqué utilisé;

	/**
	 * @return the commandeCoucheHaute_s
	 */
	public List<CommandeCoucheHaute> getCommandeCoucheHaute_s() {
		return this.commandeCoucheHaute_s;
	}

	public List<Fonctionnalité> getFonctionnalites() {
		if (this.utilisé != null) {
			return this.utilisé.getFonctionnalités();
		}
		return null;

	}

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
	public ContexteDExécutionAppliqué getUtilisé() {
		return this.utilisé;
	}

	/**
	 * @param commandeCoucheHaute_s the commandeCoucheHaute_s to set
	 */
	public void setCommandeCoucheHaute_s(final List<CommandeCoucheHaute> commandeCoucheHaute_s) {
		this.commandeCoucheHaute_s = commandeCoucheHaute_s;
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
	public void setUtilisé(final ContexteDExécutionAppliqué utilisé) {
		this.utilisé = utilisé;
	}

}// end UtilisationDeContexteDExécutionAppliqué
