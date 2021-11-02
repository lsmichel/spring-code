package com.gecko.jee.enterprise.mft.persistence.entity.contexte;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;

/**
 * @author olivier
 * @version 1.0
 * @created 21-sept.-2021 14:27:47
 */
@Entity(name = "valeurDeParametre")
@Table(name = "valeurDeParametre")
public class ValeurDeParamètre {

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "identEtType", referencedColumnName = "id")
	private Paramètre identEtType;

	private String valeurSérialiséeChnCar;

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the identEtType
	 */
	public Paramètre getIdentEtType() {
		return this.identEtType;
	}

	/**
	 * @return the valeurSérialiséeChnCar
	 */
	public String getValeurSérialiséeChnCar() {
		return this.valeurSérialiséeChnCar;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param identEtType the identEtType to set
	 */
	public void setIdentEtType(final Paramètre identEtType) {
		this.identEtType = identEtType;
	}

	/**
	 * @param valeurSérialiséeChnCar the valeurSérialiséeChnCar to set
	 */
	public void setValeurSérialiséeChnCar(final String valeurSérialiséeChnCar) {
		this.valeurSérialiséeChnCar = valeurSérialiséeChnCar;
	}

}// end ValeurDeParamètre
