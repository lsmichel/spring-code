package com.gecko.jee.enterprise.mft.persistence.entity.instance;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.automate.ÉtatÉlémentaire;
import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ContexteDExécutionConstaté;
import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ValeurDeParamètre;

/**
 * @author olivier
 * @version 1.0
 * @created 21-oct.-2021 14:25:15
 */
@Entity
@Table(name = "ligneDeJournalApres")
public class LigneDeJournalAprès {

	@ManyToOne
	@JoinColumn(name = "contexteConstate_id")
	private ContexteDExécutionConstaté contexteConstaté;

	private int dateHeureMs;

	@ManyToOne
	@JoinColumn(name = "etatAtteint_id")
	private ÉtatÉlémentaire étatAtteint;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany
	@JoinColumn(name = "valeurDeParametre_id", referencedColumnName = "id")
	private List<ValeurDeParamètre> valeur_s;

	private String valeursComplémentairesJSON;

	/**
	 * @return the contexteConstaté
	 */
	public ContexteDExécutionConstaté getContexteConstaté() {
		return this.contexteConstaté;
	}

	/**
	 * @return the dateHeureMs
	 */
	public int getDateHeureMs() {
		return this.dateHeureMs;
	}

	/**
	 * @return the étatAtteint
	 */
	public ÉtatÉlémentaire getÉtatAtteint() {
		return this.étatAtteint;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the valeur_s
	 */
	public List<ValeurDeParamètre> getValeur_s() {
		return this.valeur_s;
	}

	/**
	 * @return the valeursComplémentairesJSON
	 */
	public String getValeursComplémentairesJSON() {
		return this.valeursComplémentairesJSON;
	}

	/**
	 * @param contexteConstaté the contexteConstaté to set
	 */
	public void setContexteConstaté(final ContexteDExécutionConstaté contexteConstaté) {
		this.contexteConstaté = contexteConstaté;
	}

	/**
	 * @param dateHeureMs the dateHeureMs to set
	 */
	public void setDateHeureMs(final int dateHeureMs) {
		this.dateHeureMs = dateHeureMs;
	}

	/**
	 * @param étatAtteint the étatAtteint to set
	 */
	public void setÉtatAtteint(final ÉtatÉlémentaire étatAtteint) {
		this.étatAtteint = étatAtteint;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param valeur_s the valeur_s to set
	 */
	public void setValeur_s(final List<ValeurDeParamètre> valeur_s) {
		this.valeur_s = valeur_s;
	}

	/**
	 * @param valeursComplémentairesJSON the valeursComplémentairesJSON to set
	 */
	public void setValeursComplémentairesJSON(final String valeursComplémentairesJSON) {
		this.valeursComplémentairesJSON = valeursComplémentairesJSON;
	}

}// end LigneDeJournalAprès
