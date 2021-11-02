package com.gecko.jee.enterprise.mft.utils;

/**
 * Verifie la valeur d'un argument au cli ou web est de type
 * java.lang.Integer(retourne true si c'est le cas)
 *
 * @return Boolean
 */
public class IntegerTypeVerification {
	public static Boolean isInteger(final String value, final String type) {
		if (type.equals("java.lang.Integer")) {
			try {
				java.lang.Integer.parseInt(value);
				return true;
			} catch (final Exception e) {
				return false;
			}
		}
		return false;
	}

}
