package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 17:10:49
 */
@Entity
@Table(name = "automate")
public class Automate {

	/**
	 * Indique si l'automate est un automate de groupe de synchro
	 */
	private Boolean estDeGdS;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "automate_id", referencedColumnName = "id")
	private Set<État> etats;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	/**
	 * @return the estDeGdS
	 */
	public Boolean getEstDeGdS() {
		return this.estDeGdS;
	}

	/**
	 * @return the etats
	 */
	public Set<État> getEtats() {
		return this.etats;
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
	 * @param estDeGdS the estDeGdS to set
	 */
	public void setEstDeGdS(final Boolean estDeGdS) {
		this.estDeGdS = estDeGdS;
	}

	/**
	 * @param etats the etats to set
	 */
	public void setEtats(final Set<État> etats) {
		this.etats = etats;
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

}// end Automate

//    public String gi_ident;
//    public Map<String, Transition> gi_Transition_s = new HashMap<>();
//
//    public Automate(String i_Ident) {
//        gi_Ident = i_Ident;
//    }
//
//    public void ajouterTransition(String i_IdentTran, String i_ÉtatDeDépart, String i_ÉtatDArrivée,
//            String i_IdentSignalEntrant, String i_ParamEntrant_s, String i_Prédicat, String i_IdentSignalSortant,
//            String i_ParamSortant_s, String i_Affectations) {
//        gi_Transition_s.put(i_IdentTran, new Transition(i_IdentTran, i_ÉtatDeDépart, i_ÉtatDArrivée, i_IdentSignalEntrant,
//                i_ParamEntrant_s, i_Prédicat, i_IdentSignalSortant, i_ParamSortant_s, i_Affectations));
//    }
