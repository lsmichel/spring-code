package com.gecko.jee.enterprise.mft.exception;

/**
 * <b>Description: Permet d'identifier les types de bénéficiaires: assuré
 * principal, bénéficiaire.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public enum TypeNiveauAnomalie {

	BLOQUANT("BLQ"), MAJEUR("MAJ"), MINEUR("MIN");

	private String typeNiveauAnomalie;

	/**
	 * @param typeNiveauAnomalie
	 */
	private TypeNiveauAnomalie(final String typeNiveauAnomalie) {
		this.typeNiveauAnomalie = typeNiveauAnomalie;
	}

	/**
	 * See {@link Enum#name()}
	 */
	public String getName() {
		return this.name();
	}

	/**
	 * @return the typeNiveauAnomalie
	 */
	public String getTypeNiveauAnomalie() {
		return this.typeNiveauAnomalie;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.typeNiveauAnomalie;
	}

}
