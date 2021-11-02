package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:48:54
 */
@Entity
@Table(name = "etatElementaire")
public class ÉtatÉlémentaire extends État {

	@ManyToMany
	@JoinTable(name = "etat_transition_rdv", joinColumns = @JoinColumn(name = "etat_elementaire_id"), inverseJoinColumns = @JoinColumn(name = "transition_rdv_id"))
	private Set<TransitionRdv> transitionRdv;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "destination")
	private List<Transition> transitions;

	/**
	 * @return the transitionRdv
	 */
	public Set<TransitionRdv> getTransitionRdv() {
		return this.transitionRdv;
	}

	/**
	 * @return the transitions
	 */
	public List<Transition> getTransitions() {
		return this.transitions;
	}

	/**
	 * @param transitionRdv the transitionRdv to set
	 */
	public void setTransitionRdv(final Set<TransitionRdv> transitionRdv) {
		this.transitionRdv = transitionRdv;
	}

	/**
	 * @param transitions the transitions to set
	 */
	public void setTransitions(final List<Transition> transitions) {
		this.transitions = transitions;
	}

}// end ÉtatÉlémentaire
