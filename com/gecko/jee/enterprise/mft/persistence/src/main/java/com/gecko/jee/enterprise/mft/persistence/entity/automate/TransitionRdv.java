package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:48:54
 */
@Entity
@Table(name = "transitionRdv")
public class TransitionRdv extends Transition {

	@ManyToMany(mappedBy = "transitionRdv")
	private Set<ÉtatÉlémentaire> origine_s;

	/**
	 * @return the origine_s
	 */
	public Set<ÉtatÉlémentaire> getOrigine_s() {
		return this.origine_s;
	}

	/**
	 * @param origine_s the origine_s to set
	 */
	public void setOrigine_s(final Set<ÉtatÉlémentaire> origine_s) {
		this.origine_s = origine_s;
	}

}// end TransitionRdv
