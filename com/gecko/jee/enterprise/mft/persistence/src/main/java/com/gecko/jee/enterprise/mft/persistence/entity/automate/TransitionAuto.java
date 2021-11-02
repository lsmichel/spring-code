package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:48:54
 */
@Entity
@Table(name = "transitionAuto")
public class TransitionAuto extends Transition {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "etatElementaireOrigine_id", referencedColumnName = "id")
	private ÉtatÉlémentaire origine;

	/**
	 * @return the origine
	 */
	public ÉtatÉlémentaire getOrigine() {
		return this.origine;
	}

	/**
	 * @param origine the origine to set
	 */
	public void setOrigine(final ÉtatÉlémentaire origine) {
		this.origine = origine;
	}

}// end TransitionAuto
