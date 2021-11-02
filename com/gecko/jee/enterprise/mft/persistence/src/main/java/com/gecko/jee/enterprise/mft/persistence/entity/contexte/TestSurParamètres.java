package com.gecko.jee.enterprise.mft.persistence.entity.contexte;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;

/**
 * @author olivier
 * @version 1.0
 * @created 21-sept.-2021 14:27:47
 */
@Entity(name = "testSurParametres")
@Table(name = "testSurParametres")
public class TestSurParamètres {

	private String exprLogiqueDeComparaison;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(mappedBy = "testSurParametres")
	private List<Paramètre> paramètres;

	/**
	 * @return the exprLogiqueDeComparaison
	 */
	public String getExprLogiqueDeComparaison() {
		return this.exprLogiqueDeComparaison;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the paramètres
	 */
	public List<Paramètre> getParamètres() {
		return this.paramètres;
	}

	/**
	 * @param exprLogiqueDeComparaison the exprLogiqueDeComparaison to set
	 */
	public void setExprLogiqueDeComparaison(final String exprLogiqueDeComparaison) {
		this.exprLogiqueDeComparaison = exprLogiqueDeComparaison;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param paramètres the paramètres to set
	 */
	public void setParamètres(final List<Paramètre> paramètres) {
		this.paramètres = paramètres;
	}

}// end TestSurParamètres
