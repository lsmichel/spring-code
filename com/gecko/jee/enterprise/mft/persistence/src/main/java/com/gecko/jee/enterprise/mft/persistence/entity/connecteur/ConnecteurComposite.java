package com.gecko.jee.enterprise.mft.persistence.entity.connecteur;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author olivier
 * @version 1.0
 * @created 29-sept.-2021 10:31:41
 */
@Entity
@Table(name = "connecteurComposite")
public class ConnecteurComposite extends Connecteur {

	public ConnecteurComposite() {

	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}
}// end ConnecteurComposite
