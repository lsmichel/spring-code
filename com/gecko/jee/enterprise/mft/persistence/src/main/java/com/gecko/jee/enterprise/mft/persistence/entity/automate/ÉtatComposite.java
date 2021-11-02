package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:48:54
 */
@Entity
@Table(name = "etatComposite")
public class ÉtatComposite extends État {

	private String dER_Chemin;

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	private List<ÉtatÉlémentaire> enfant_s;

	/**
	 * @return the dER_Chemin
	 */
	public String getdER_Chemin() {
		return this.dER_Chemin;
	}

	/**
	 * @return the enfant_s
	 */
	public List<ÉtatÉlémentaire> getEnfant_s() {
		return this.enfant_s;
	}

	/**
	 * @param dER_Chemin the dER_Chemin to set
	 */
	public void setdER_Chemin(final String dER_Chemin) {
		this.dER_Chemin = dER_Chemin;
	}

	/**
	 * @param enfant_s the enfant_s to set
	 */
	public void setEnfant_s(final List<ÉtatÉlémentaire> enfant_s) {
		this.enfant_s = enfant_s;
	}

}// end ÉtatComposite
