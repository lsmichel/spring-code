package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Signal;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:48:54
 */
@Entity
@Table(name = "transitionSimple")
public class TransitionSimple extends Transition {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "signalEntrant_id", referencedColumnName = "id")
	private Signal entrant;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "etatElementaireOrigine_id", referencedColumnName = "id")
	private ÉtatÉlémentaire origine;

	/**
	 * @return the entrant
	 */
	public Signal getEntrant() {
		return this.entrant;
	}

	/**
	 * @return the origine
	 */
	public ÉtatÉlémentaire getOrigine() {
		return this.origine;
	}

	/**
	 * @param entrant the entrant to set
	 */
	public void setEntrant(final Signal entrant) {
		this.entrant = entrant;
	}

	/**
	 * @param origine the origine to set
	 */
	public void setOrigine(final ÉtatÉlémentaire origine) {
		this.origine = origine;
	}

}// end TransitionSimple
