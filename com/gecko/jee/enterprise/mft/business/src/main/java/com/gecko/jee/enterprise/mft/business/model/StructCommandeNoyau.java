package com.gecko.jee.enterprise.mft.business.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 *
 * @author olivier
 */

public class StructCommandeNoyau {

	public List<Object> gi_Cmd = new ArrayList<>();

	public List<String> gi_Cmd_ComplémentPourRetypage = new ArrayList<>();

	/**
	 * @return the gi_Cmd_ComplémentPourRetypage
	 */
	public List<String> getGi_Cmd_ComplémentPourRetypage() {
		return this.gi_Cmd_ComplémentPourRetypage;
	}

	/**
	 * @param gi_Cmd_ComplémentPourRetypage the gi_Cmd_ComplémentPourRetypage to set
	 */
	public void setGi_Cmd_ComplémentPourRetypage(final List<String> gi_Cmd_ComplémentPourRetypage) {
		this.gi_Cmd_ComplémentPourRetypage = gi_Cmd_ComplémentPourRetypage;
	}

	@Override
	public String toString() {
		final Gson gson = new Gson();
		return gson.toJson(this);
	}

}
