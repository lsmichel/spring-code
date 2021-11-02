package com.gecko.jee.enterprise.mft.persistence.entity.protocole;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.automate.Transition;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.TransitionSimple;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.ÉtatComposite;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.ÉtatÉlémentaire;
import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ContexteDExécutionAppliqué;

/**
 * Exemple: le 'put ' du protocole 'ftp'
 *
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:49:39
 */
@Entity
@Table(name = "fonctionnalite")
public class Fonctionnalité {

	@ManyToMany
	@JoinTable(name = "contexteExecutionApplique_fonctionnalite", joinColumns = @JoinColumn(name = "fonctionnalite_id"), inverseJoinColumns = @JoinColumn(name = "contexteExecutionApplique_id"))
	private List<ContexteDExécutionAppliqué> contexteExecutionAppliques;

	private Boolean estActivée;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "etatCompositeEtatAssocie_id", referencedColumnName = "id")
	private ÉtatComposite étatAssocié;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	/**
	 * Représentent les "méta" paramètres d'une fonctionnalité.
	 */
	@ManyToMany
	@JoinTable(name = "fonctionnalite_parametre", joinColumns = @JoinColumn(name = "fonctionnalite_id"), inverseJoinColumns = @JoinColumn(name = "parametre_id"))
	private List<Paramètre> paramètres;

	@ManyToOne
	@JoinColumn(name = "protocole_id", referencedColumnName = "id")
	private Protocole protocole;

	/**
	 * @return the contexteExecutionAppliques
	 */
	public List<ContexteDExécutionAppliqué> getContexteExecutionAppliques() {
		return this.contexteExecutionAppliques;
	}

	/**
	 * @return the estActivée
	 */
	public Boolean getEstActivée() {
		return this.estActivée;
	}

	/**
	 * @return the étatAssocié
	 */
	public ÉtatComposite getÉtatAssocié() {
		return this.étatAssocié;
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
	 * @return the paramètres
	 */
	public List<Paramètre> getParamètres() {
		return this.paramètres;
	}

	/**
	 * @return the automate
	 */
	public Protocole getProtocole() {
		return this.protocole;
	}

	/**
	 * Cette méthode permet de parcourir le modèle pour une fonctionnalité afin de
	 * retrouver tous les signaux entrants et sortants.
	 * Fonctionnalité.étatAssocié.inverse(parent).inverse(destination).entrant
	 *
	 * @return Les signaux entrants/sortants d'un protocole.
	 */
	public List<Signal> getSignaux() {
		final List<Signal> signaux = new ArrayList<>();
		final ÉtatComposite etatComposite = this.getÉtatAssocié();
		final List<ÉtatÉlémentaire> etats = etatComposite.getEnfant_s();
		for (final ÉtatÉlémentaire etat : etats) {
			for (final Transition transition : etat.getTransitions()) {
				// Signaux entrants et sortants
				signaux.add(transition.getSortant());
				// Si la transition est simple, on a aussi un état entrant.
				// (les transition rdv n'ont pas de signaux entrants)
				if (transition instanceof TransitionSimple) {
					signaux.add(((TransitionSimple) transition).getEntrant());
				}
			}
		}
		return signaux;
	}

	/**
	 * @param contexteExecutionAppliques the contexteExecutionAppliques to set
	 */
	public void setContexteExecutionAppliques(final List<ContexteDExécutionAppliqué> contexteExecutionAppliques) {
		this.contexteExecutionAppliques = contexteExecutionAppliques;
	}

	/**
	 * @param estActivée the estActivée to set
	 */
	public void setEstActivée(final Boolean estActivée) {
		this.estActivée = estActivée;
	}

	/**
	 * @param étatAssocié the étatAssocié to set
	 */
	public void setÉtatAssocié(final ÉtatComposite étatAssocié) {
		this.étatAssocié = étatAssocié;
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
	 * @param paramètres the paramètres to set
	 */
	public void setParamètres(final List<Paramètre> paramètres) {
		this.paramètres = paramètres;
	}

	/**
	 * @param protocole the protocole to set
	 */
	public void setProtocole(final Protocole protocole) {
		this.protocole = protocole;
	}

}// end Fonctionnalité
