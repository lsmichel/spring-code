package com.gecko.jee.enterprise.mft.persistence.entity.contexte;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Fonctionnalité;

/**
 * @author olivier
 * @version 1.0
 * @created 21-sept.-2021 14:27:47
 */
@Entity
@Table(name = "contexteDExecutionApplique")
public class ContexteDExécutionAppliqué extends ContexteDExécution {

	@ManyToMany(mappedBy = "contexteExecutionAppliques", fetch = FetchType.EAGER)
	private List<Fonctionnalité> fonctionnalités;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany
	@JoinColumn(name = "contexteDExecutionApplique_id", referencedColumnName = "id")
	private List<ValeurDeParamètre> valeursFixées;

	/**
	 * @return the fonctionnalités
	 */
	public List<Fonctionnalité> getFonctionnalités() {
		return this.fonctionnalités;
	}

	/**
	 * @return the valeursFixées
	 */
	public List<ValeurDeParamètre> getValeursFixées() {
		return this.valeursFixées;
	}

	/**
	 * @param fonctionnalités the fonctionnalités to set
	 */
	public void setFonctionnalités(final List<Fonctionnalité> fonctionnalités) {
		this.fonctionnalités = fonctionnalités;
	}

	/**
	 * @param valeursFixées the valeursFixées to set
	 */
	public void setValeursFixées(final List<ValeurDeParamètre> valeursFixées) {
		this.valeursFixées = valeursFixées;
	}

}// end ContexteDExécutionAppliqué
