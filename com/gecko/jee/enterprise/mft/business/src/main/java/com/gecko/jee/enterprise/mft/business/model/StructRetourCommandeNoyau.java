package com.gecko.jee.enterprise.mft.business.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author olivier
 */

public class StructRetourCommandeNoyau {

	public List<Object> gi_Retour = new ArrayList<>();

	public List<String> gi_Retour_ComplémentPourRetypage = new ArrayList<>();

	public Boolean gi_SortieDemandée = Boolean.FALSE;

	/**
	 * @return the gi_Retour_ComplémentPourRetypage
	 */
	public List<String> getGi_Retour_ComplémentPourRetypage() {
		return this.gi_Retour_ComplémentPourRetypage;
	}

	/**
	 * @return the gi_SortieDemandée
	 */
	public Boolean getGi_SortieDemandée() {
		return this.gi_SortieDemandée;
	}

	/**
	 * @param gi_Retour_ComplémentPourRetypage the gi_Retour_ComplémentPourRetypage
	 *                                         to set
	 */
	public void setGi_Retour_ComplémentPourRetypage(final List<String> gi_Retour_ComplémentPourRetypage) {
		this.gi_Retour_ComplémentPourRetypage = gi_Retour_ComplémentPourRetypage;
	}

	/**
	 * @param gi_SortieDemandée the gi_SortieDemandée to set
	 */
	public void setGi_SortieDemandée(final Boolean gi_SortieDemandée) {
		this.gi_SortieDemandée = gi_SortieDemandée;
	}
}
