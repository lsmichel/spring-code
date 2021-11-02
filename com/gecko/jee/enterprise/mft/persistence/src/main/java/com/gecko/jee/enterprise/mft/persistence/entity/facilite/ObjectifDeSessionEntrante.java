package com.gecko.jee.enterprise.mft.persistence.entity.facilite;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author olivier
 * @version 1.0
 * @created 21-sept.-2021 14:59:18
 */
@Entity
public class ObjectifDeSessionEntrante extends UtilisationDeContexteDExécutionConstaté {

	@Enumerated(EnumType.STRING)
	private Sorte sorte;

	/**
	 * @return the sorte
	 */
	public Sorte getSorte() {
		return this.sorte;
	}

	/**
	 * @param sorte the sorte to set
	 */
	public void setSorte(final Sorte sorte) {
		this.sorte = sorte;
	}

}// end ObjectifDeSessionEntrante
