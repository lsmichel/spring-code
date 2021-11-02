package com.gecko.jee.enterprise.mft.persistence.entity.protocole;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.connecteur.ConnecteurÉlémentaire;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:49:39
 */
@Entity
@Table(name = "signal_")
public class Signal {

	@ManyToMany(mappedBy = "signauxEntrant_s")
	private List<ConnecteurÉlémentaire> connecteurElementaireEntrant_s;

	@ManyToMany(mappedBy = "signauxSortant_s")
	private List<ConnecteurÉlémentaire> connecteurElementaireSortant_s;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ident;

	@ManyToMany(mappedBy = "signal_s", fetch = FetchType.EAGER)
	private List<Paramètre> paramètre_s;

	@ManyToMany(mappedBy = "signal_s")
	private Set<Protocole> protocole_s;

	/**
	 * @return the connecteurElementaire_s
	 */
	public List<ConnecteurÉlémentaire> getConnecteurElementaireEntrant_s() {
		return this.connecteurElementaireEntrant_s;
	}

	/**
	 * @return the connecteurElementaireSortant_s
	 */
	public List<ConnecteurÉlémentaire> getConnecteurElementaireSortant_s() {
		return this.connecteurElementaireSortant_s;
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
	 * @return the paramètre_s
	 */
	public List<Paramètre> getParamètre_s() {
		return this.paramètre_s;
	}

	/**
	 * @return the protocole_s
	 */
	public Set<Protocole> getProtocole_s() {
		return this.protocole_s;
	}

	/**
	 * @param connecteurElementaire_s the connecteurElementaire_s to set
	 */
	public void setConnecteurElementaireEntrant_s(final List<ConnecteurÉlémentaire> connecteurElementaire_s) {
		this.connecteurElementaireEntrant_s = connecteurElementaire_s;
	}

	/**
	 * @param connecteurElementaireSortant_s the connecteurElementaireSortant_s to
	 *                                       set
	 */
	public void setConnecteurElementaireSortant_s(final List<ConnecteurÉlémentaire> connecteurElementaireSortant_s) {
		this.connecteurElementaireSortant_s = connecteurElementaireSortant_s;
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
	 * @param paramètre_s the paramètre_s to set
	 */
	public void setParamètre_s(final List<Paramètre> paramètre_s) {
		this.paramètre_s = paramètre_s;
	}

	/**
	 * @param protocole_s the protocole_s to set
	 */
	public void setProtocole_s(final Set<Protocole> protocole_s) {
		this.protocole_s = protocole_s;
	}

}// end Signal

//    public String gi_Ident;
//    public List<Paramètre> gi_Argument_s = new ArrayList<>();
//
//    public Signal(String i_Ident, String i_ParamEntrant_s) {
//        gi_Ident = i_Ident;
//        String[] l_ParamEntrant_s_TabChnCar = i_ParamEntrant_s.split(",");
//        for (String c1_ParamEntrant : l_ParamEntrant_s_TabChnCar) {
//            Paramètre c1_Paramètre = new Paramètre(c1_ParamEntrant);
//            gi_Argument_s.add(c1_Paramètre);
//        }
//    }
