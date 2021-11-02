package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Signal;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:48:54
 */
@Entity
@Table(name = "transition")
public class Transition {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "transition_id", referencedColumnName = "id")
	private Set<Affectation> affectation_s;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "etatElementaireDestination_id")
	private ÉtatÉlémentaire destination;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "garde_id", referencedColumnName = "id")
	private Garde garde;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	/**
	 * Null possible
	 */
	private String préfixeDeSignal;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "signalSortant_id", referencedColumnName = "id")
	private Signal sortant;

	/**
	 * @return the affectation_s
	 */
	public Set<Affectation> getAffectation_s() {
		return this.affectation_s;
	}

	/**
	 * @return the destination
	 */
	public ÉtatÉlémentaire getDestination() {
		return this.destination;
	}

	/**
	 * @return the garde
	 */
	public Garde getGarde() {
		return this.garde;
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
	 * @return the préfixeDeSignal
	 */
	public String getPréfixeDeSignal() {
		return this.préfixeDeSignal;
	}

	/**
	 * @return the sortant
	 */
	public Signal getSortant() {
		return this.sortant;
	}

	/**
	 * @param affectation_s the affectation_s to set
	 */
	public void setAffectation_s(final Set<Affectation> affectation_s) {
		this.affectation_s = affectation_s;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(final ÉtatÉlémentaire destination) {
		this.destination = destination;
	}

	/**
	 * @param garde the garde to set
	 */
	public void setGarde(final Garde garde) {
		this.garde = garde;
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
	 * @param préfixeDeSignal the préfixeDeSignal to set
	 */
	public void setPréfixeDeSignal(final String préfixeDeSignal) {
		this.préfixeDeSignal = préfixeDeSignal;
	}

	/**
	 * @param sortant the sortant to set
	 */
	public void setSortant(final Signal sortant) {
		this.sortant = sortant;
	}

}// end Transition

//    public String gi_Ident;
//    public String gi_ÉtatDeDépart;
//    public String gi_ÉtatDArrivée;
//    public Signal gi_SignalIntrant;
//    public String gi_Prédicat;
//    public List<Signal> gi_SignauxExtrant_s = new ArrayList<>();
//    public Map<String, String> gi_Affectation_s = new HashMap<>();
//
//    public Transition(String i_IdentTran, String i_ÉtatDeDépart, String i_ÉtatDArrivée, String i_IdentSignalEntrant,
//            String i_ParamEntrant_s, String i_Prédicat, String i_IdentSignalSortant, String i_ParamSortant_s, String i_Affectations) {
//        gi_Ident = i_IdentTran;
//        gi_ÉtatDeDépart = i_ÉtatDeDépart;
//        gi_ÉtatDArrivée = i_ÉtatDArrivée;
//        gi_SignalIntrant = new Signal(i_IdentSignalEntrant, i_ParamEntrant_s);
//        gi_Prédicat = i_Prédicat;
//        gi_SignauxExtrant_s.add(new Signal(i_IdentSignalSortant, i_ParamSortant_s));
//        String[] l_Affectation_s_TabChnCar = i_Affectations.split(",");
//        for (String c1_Affectation : l_Affectation_s_TabChnCar) {
//            String[] c1_Paire_TabChnCar = c1_Affectation.split(":=");
//            gi_Affectation_s.put(c1_Paire_TabChnCar[0], c1_Paire_TabChnCar[1]);
//        }
//    }
