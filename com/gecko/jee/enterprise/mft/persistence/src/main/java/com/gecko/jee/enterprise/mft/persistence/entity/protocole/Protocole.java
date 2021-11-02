package com.gecko.jee.enterprise.mft.persistence.entity.protocole;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.automate.Automate;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:49:39
 */
@Entity
@Table(name = "protocole")
public class Protocole {

	/**
	 * Cet automate est général pour tout un protocole. Ce sont les états composites
	 * qui vont créer des sous-ensembles pour les fonctionnalités du protocole.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "automate_id", referencedColumnName = "id")
	private Automate automate;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "protocole_id", referencedColumnName = "id")
	private Set<Fonctionnalité> fonctionnalité_s;

	/**
	 * C'est l'Id autogénéré de la table correspondante. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String ident;

	@ManyToMany
	@JoinTable(name = "protocole_signal", joinColumns = @JoinColumn(name = "protocole_id"), inverseJoinColumns = @JoinColumn(name = "signal_id"))
	private Set<Signal> signal_s;

	/**
	 * @return the automate
	 */
	public Automate getAutomate() {
		return this.automate;
	}

	/**
	 * @return the fonctionnalité_s
	 */
	public Set<Fonctionnalité> getFonctionnalité_s() {
		return this.fonctionnalité_s;
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
	public Set<Signal> getSignal_s() {
		return this.signal_s;
	}

	/**
	 * @param automate the automate to set
	 */
	public void setAutomate(final Automate automate) {
		this.automate = automate;
	}

	/**
	 * @param fonctionnalité_s the fonctionnalité_s to set
	 */
	public void setFonctionnalité_s(final Set<Fonctionnalité> fonctionnalité_s) {
		this.fonctionnalité_s = fonctionnalité_s;
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
	public void setSignal_s(final Set<Signal> signal_s) {
		this.signal_s = signal_s;
	}

}// end Protocole
