package com.gecko.jee.enterprise.mft.exception;

import java.util.Date;

/**
 * <b> Description: Exception métier utilisée pour remonter une erreur
 * fonctionnelle à la couche appelante. N'est pas une {@link RuntimeException}
 * !</b>
 * <p>
 * Peut être dérivée pour créer des erreurs métier spécialisées si nécessaire.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class BusinessException extends Exception {

	/**
	 * Default business error code constant.
	 */
	public static final String ERROR_BUSINESS_CODE_DEFAULT = "ERROR_BUSINESS_CODE_DEFAULT";

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -5700163308154559141L;

	/**
	 * Error code.
	 */
	private String code;

	/**
	 * Error date.
	 */
	private Date date;

	/**
	 * Detailed information on error.
	 */
	private String information = "";

	/**
	 * Criticité de l'anomalie
	 */
	private TypeNiveauAnomalie niveauAnomalie;

	/**
	 * Additional wildcards for the message.
	 */
	private Object[] wildcards;

	/**
	 * Constructor. Initialize code as default error code.
	 */
	public BusinessException() {
		this.code = BusinessException.ERROR_BUSINESS_CODE_DEFAULT;
	}

	/**
	 * Constructor.
	 *
	 * @param code exception code
	 */
	public BusinessException(final String code) {
		this.code = code;
	}

	/**
	 * Constructor.
	 *
	 * @param code        exception code
	 * @param information detailed information
	 */
	public BusinessException(final String code, final String information) {
		this.code = code;
		this.information = information;
	}

	/**
	 * Constructor.
	 *
	 * @param code        exception code
	 * @param information detailed information
	 * @param wildcards   arguments describing exception context
	 */
	public BusinessException(final String code, final String information, final Object... wildcards) {
		this.code = code;
		this.information = this.populateWildcards(information, wildcards);
		this.wildcards = wildcards;
	}

	/**
	 * Constructor.
	 *
	 * @param code        exception code
	 * @param information detailed information
	 * @param cause       underlying throwable
	 */
	public BusinessException(final String code, final String information, final Throwable cause) {
		this.code = code;
		this.information = information;
		this.initCause(cause);
	}

	/**
	 * Constructor.
	 *
	 * @param code        exception code
	 * @param information detailed information
	 * @param cause       underlying throwable
	 * @param wildcards   arguments describing exception context
	 */
	public BusinessException(final String code, final String information, final Throwable cause,
			final Object... wildcards) {
		this.code = code;
		this.information = this.populateWildcards(information, wildcards);
		this.wildcards = wildcards;
		this.initCause(cause);
	}

	/**
	 * Constructor.
	 *
	 * @param code           exception code
	 * @param information    detailed information
	 * @param niveauAnomalie niveau de l'anomalie
	 */
	public BusinessException(final String code, final String information, final TypeNiveauAnomalie niveauAnomalie) {
		this.code = code;
		this.information = information;
		this.niveauAnomalie = niveauAnomalie;
	}

	/**
	 * Constructor.
	 *
	 * @param code  exception code
	 * @param cause underlying throwable
	 */
	public BusinessException(final String code, final Throwable cause) {
		this.code = code;
		this.initCause(cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getCause() {
		return this;
	}

	/**
	 * Retrieve the error code.
	 *
	 * @return the error code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Retrieve the detailed information of the error.
	 *
	 * @return the detailed information of the error
	 */
	public String getInformation() {
		return this.information;
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		final StringBuffer stringBuffer = new StringBuffer();
		if (this.code != null && this.code.trim().equals("") == false) {
			stringBuffer.append(this.code);
		}
		if (this.information != null && this.information.trim().equals("") == false) {
			stringBuffer.append(this.information);
		}
		return this.code + ":" + this.information;
	}

	/**
	 * @return the niveauAnomalie
	 */
	public TypeNiveauAnomalie getNiveauAnomalie() {
		return this.niveauAnomalie;
	}

	/**
	 * @return the wildcards
	 */
	public Object[] getWildcards() {
		return this.wildcards;
	}

	/**
	 * Put wildcards into the message: replace {0},{1}... with provided wildcards.
	 * Wildcards are placed in the order they are given.
	 *
	 * @param wildcards   wildcards values
	 * @param messagePart the message containing {x} references
	 * @return the message populated with wildcards values
	 */
	public String populateWildcards(final String messagePart, final Object... wildcards) {
		int ndxBeginParam;
		String part = messagePart;
		String messToInspect = part;
		while ((ndxBeginParam = messToInspect.indexOf('{')) != -1) {
			final int ndxEndParam = messToInspect.indexOf('}');
			final String param = messToInspect.substring(ndxBeginParam + 1, ndxEndParam);
			final String stringToreplace = "\\{".concat(param).concat("\\}");
			final int numWildcard = Integer.parseInt(param);
			// Replace $ character in the wildcard value to prevent from
			// exception during replaceAll on the full message.
			if (wildcards[numWildcard] != null) {
				wildcards[numWildcard] = wildcards[numWildcard].toString().replace("$", "");
				part = part.replaceAll(stringToreplace, wildcards[numWildcard].toString());
			} else {
				part = part.replaceAll(stringToreplace, "null");
			}
			messToInspect = messToInspect.substring(ndxEndParam + 1, messToInspect.length());
		}
		return part;
	}

	/**
	 * Setter for the error code.
	 *
	 * @param code the error code to use
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Setter for the detailed information.
	 *
	 * @param information the detailed information to use
	 */
	public void setInformation(final String information) {
		this.information = information;
	}

	/**
	 * @param niveauAnomalie the niveauAnomalie to set
	 */
	public void setNiveauAnomalie(final TypeNiveauAnomalie niveauAnomalie) {
		this.niveauAnomalie = niveauAnomalie;
	}

	/**
	 * @param wildcards the wildcards to set
	 */
	public void setWildcards(final Object[] wildcards) {
		this.wildcards = wildcards;
	}

	/**
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {

		final StringBuffer base = new StringBuffer(super.toString());

		if (!"".equals(this.code) && this.code != null) {
			base.append("[Code cause = ").append(this.code).append("]");
		}
		if (this.niveauAnomalie != null) {
			base.append("[Niveau = ").append(this.niveauAnomalie.toString()).append("]");
		}
		if (!"".equals(this.information) && this.information != null) {
			base.append("[Information = ").append(this.information).append("]");
		}
		return base.toString();
	}
}