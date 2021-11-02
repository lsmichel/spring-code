package com.gecko.jee.enterprise.mft.persistence.entity.protocole;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.automate.Affectation;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.Garde;
import com.gecko.jee.enterprise.mft.persistence.entity.contexte.TestSurParamètres;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:49:39
 */
@Entity(name = "parametre")
@Table(name = "parametre")
public class Paramètre {

	@ManyToMany
	@JoinTable(name = "parametre_affectation", joinColumns = @JoinColumn(name = "parametre_id"), inverseJoinColumns = @JoinColumn(name = "affectations_id"))
	private List<Affectation> affectations;

	/**
	 * Indique que ce paramètre fait l'objet d'un contrôle de flux.
	 */
	private Boolean estImplicitementObjetDeFlux;

	private Boolean estObligatoire;

	/**
	 * Valeur par défaut.
	 */
	private String exprImpliciteSiFacult;

	@ManyToMany(mappedBy = "paramètres")
	private List<Fonctionnalité> fonctionnalités;

	@ManyToMany
	@JoinTable(name = "parametre_garde", joinColumns = @JoinColumn(name = "parametre_id"), inverseJoinColumns = @JoinColumn(name = "gardes_id"))
	private List<Garde> gardes;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	@ManyToMany
	@JoinTable(name = "parametre_signal", joinColumns = @JoinColumn(name = "parametre_id"), inverseJoinColumns = @JoinColumn(name = "signal_id"))
	private List<Signal> signal_s;

	@ManyToMany
	@JoinTable(name = "parametre_testSurParametre", joinColumns = @JoinColumn(name = "parametre_id"), inverseJoinColumns = @JoinColumn(name = "testSurParametre_id"))
	private List<TestSurParamètres> testSurParametres;

	// TODO type_
	private String type_;

	/**
	 * @return the affectations
	 */
	public List<Affectation> getAffectations() {
		return this.affectations;
	}

	/**
	 * @return the estImplicitementObjetDeFlux
	 */
	public Boolean getEstImplicitementObjetDeFlux() {
		return this.estImplicitementObjetDeFlux;
	}

	/**
	 * @return the estObligatoire
	 */
	public Boolean getEstObligatoire() {
		return this.estObligatoire;
	}

	/**
	 * @return the exprImpliciteSiFacult
	 */
	public String getExprImpliciteSiFacult() {
		return this.exprImpliciteSiFacult;
	}

	/**
	 * @return the fonctionnalités
	 */
	public List<Fonctionnalité> getFonctionnalités() {
		return this.fonctionnalités;
	}

	/**
	 * @return the gardes
	 */
	public List<Garde> getGardes() {
		return this.gardes;
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
	 * @return the signal_s
	 */
	public List<Signal> getSignal_s() {
		return this.signal_s;
	}

	/**
	 * @return the type
	 */

	/**
	 * @return the testSurParametres
	 */
	public List<TestSurParamètres> getTestSurParametres() {
		return this.testSurParametres;
	}

	/**
	 * @return the type_
	 */
	public String getType_() {
		return this.type_;
	}

	/**
	 * @param affectations the affectations to set
	 */
	public void setAffectations(final List<Affectation> affectations) {
		this.affectations = affectations;
	}

	/**
	 * @param estImplicitementObjetDeFlux the estImplicitementObjetDeFlux to set
	 */
	public void setEstImplicitementObjetDeFlux(final Boolean estImplicitementObjetDeFlux) {
		this.estImplicitementObjetDeFlux = estImplicitementObjetDeFlux;
	}

	/**
	 * @param estObligatoire the estObligatoire to set
	 */
	public void setEstObligatoire(final Boolean estObligatoire) {
		this.estObligatoire = estObligatoire;
	}

	/**
	 * @param exprImpliciteSiFacult the exprImpliciteSiFacult to set
	 */
	public void setExprImpliciteSiFacult(final String exprImpliciteSiFacult) {
		this.exprImpliciteSiFacult = exprImpliciteSiFacult;
	}

	/**
	 * @param fonctionnalités the fonctionnalités to set
	 */
	public void setFonctionnalités(final List<Fonctionnalité> fonctionnalités) {
		this.fonctionnalités = fonctionnalités;
	}

	/**
	 * @param gardes the gardes to set
	 */
	public void setGardes(final List<Garde> gardes) {
		this.gardes = gardes;
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
	 * @param signal_s the signal_s to set
	 */
	public void setSignal_s(final List<Signal> signal_s) {
		this.signal_s = signal_s;
	}

	/**
	 * @param testSurParametres the testSurParametres to set
	 */
	public void setTestSurParametres(final List<TestSurParamètres> testSurParametres) {
		this.testSurParametres = testSurParametres;
	}

	/**
	 * @param type_ the type_ to set
	 */
	public void setType_(final String type_) {
		this.type_ = type_;
	}

	/**
	 * @param type the type to set
	 */

}// end Paramètre
