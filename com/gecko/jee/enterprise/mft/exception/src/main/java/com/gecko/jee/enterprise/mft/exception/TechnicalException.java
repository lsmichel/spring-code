package com.gecko.jee.enterprise.mft.exception;

import java.util.Date;

/**
 * <b> Description: Exception utilisée pour remonter une erreur technique à la
 * couche appelante. Attention c'est une {@link RuntimeException} !</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 *
 */
public class TechnicalException extends RuntimeException {

	/**
	 * Default error code constant.
	 */
	public static final String ERROR_CODE_DEFAULT = "ERROR_CODE_DEFAULT";

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -5700163308154559141L;

	/**
	 * Erreur technique d'origine
	 */
	private Throwable cause;

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
	 * Additional wildcards for the message.
	 */
	private Object[] wildcards;

	/**
	 * Constructor. Initialize code as default error code.
	 */
	public TechnicalException() {
		this.code = TechnicalException.ERROR_CODE_DEFAULT;
	}

	/**
	 * Constructor.
	 *
	 * @param code exception code
	 */
	public TechnicalException(final String code) {
		this.code = code;
	}

	/**
	 * Constructor.
	 *
	 * @param code        exception code
	 * @param information detailed information
	 */
	public TechnicalException(final String code, final String information) {
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
	public TechnicalException(final String code, final String information, final Object... wildcards) {
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
	public TechnicalException(final String code, final String information, final Throwable cause) {
		this.cause = cause;
		this.code = code;
		this.information = information;
	}

	/**
	 * Constructor.
	 *
	 * @param code        exception code
	 * @param information detailed information
	 * @param cause       underlying throwable
	 * @param wildcards   arguments describing exception context
	 */

	public TechnicalException(final String code, final String information, final Throwable cause,
			final Object... wildcards) {
		this.cause = cause;
		this.code = code;
		this.information = this.populateWildcards(information, wildcards);
		this.wildcards = wildcards;
	}

	/**
	 * Constructor.
	 *
	 * @param code  exception code
	 * @param cause underlying throwable
	 */
	public TechnicalException(final String code, final Throwable cause) {
		this.cause = cause;
		this.code = code;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getCause() {
		return this.cause;
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
		if (!"".equals(this.information) && this.information != null) {
			base.append("[Information = ").append(this.information).append("]");
		}
		return base.toString();
	}
}