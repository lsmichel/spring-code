package com.gecko.jee.enterprise.mft.persistence.entity.connecteur;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Signal;

/**
 * @author olivier
 * @version 1.0
 * @created 29-sept.-2021 10:31:42
 */
@Entity
@Table(name = "connecteurElementaire")
public class ConnecteurÉlémentaire extends Connecteur {

	private String jNDIFabriqueSession;

	private String nomCanonique;

	@ManyToMany
	@JoinTable(name = "connecteurElementaire_signal_entrant", joinColumns = @JoinColumn(name = "connecteurElementaire_id"), inverseJoinColumns = @JoinColumn(name = "signal_id"))
	private List<Signal> signauxEntrant_s;

	@ManyToMany
	@JoinTable(name = "connecteurElementaire_signal_sortant", joinColumns = @JoinColumn(name = "connecteurElementaire_id"), inverseJoinColumns = @JoinColumn(name = "signal_id"))
	private List<Signal> signauxSortant_s;

	public ConnecteurÉlémentaire() {

	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * @return the jNDIFabriqueSession
	 */
	public String getjNDIFabriqueSession() {
		return this.jNDIFabriqueSession;
	}

	/**
	 * @return the packageJava
	 */
	public String getNomCanonique() {
		return this.nomCanonique;
	}

	/**
	 * @return the signauxEntrant_s
	 */
	public List<Signal> getSignauxEntrant_s() {
		return this.signauxEntrant_s;
	}

	/**
	 * @return the signauxSortant_s
	 */
	public List<Signal> getSignauxSortant_s() {
		return this.signauxSortant_s;
	}

	/**
	 * @param jNDIFabriqueSession the jNDIFabriqueSession to set
	 */
	public void setjNDIFabriqueSession(final String jNDIFabriqueSession) {
		this.jNDIFabriqueSession = jNDIFabriqueSession;
	}

	/**
	 * @param packageJava the packageJava to set
	 */
	public void setNomCanonique(final String packageJava) {
		this.nomCanonique = packageJava;
	}

	/**
	 * @param signauxEntrant_s the signauxEntrant_s to set
	 */
	public void setSignauxEntrant_s(final List<Signal> signauxEntrant_s) {
		this.signauxEntrant_s = signauxEntrant_s;
	}

	/**
	 * @param signauxSortant_s the signauxSortant_s to set
	 */
	public void setSignauxSortant_s(final List<Signal> signauxSortant_s) {
		this.signauxSortant_s = signauxSortant_s;
	}

}// end ConnecteurÉlémentaire
