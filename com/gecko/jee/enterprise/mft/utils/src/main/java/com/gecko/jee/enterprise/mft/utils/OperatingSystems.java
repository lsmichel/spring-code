package com.gecko.jee.enterprise.mft.utils;

/**
 * <b>Description: Utilitaires liés aux systèmes d'exploitation.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class OperatingSystems {

	/**
	 * Contient la version de l'OS exécutant la JVM
	 */
	public static String OS = System.getProperty("os.name").toLowerCase();

	/**
	 * Inidique si l'OS est Mac
	 *
	 * @return true si l'OS est Mac
	 */
	public static boolean isMac() {
		return OS.indexOf("mac") >= 0;
	}

	/**
	 * Inidique si l'OS est Solaris
	 *
	 * @return true si l'OS est Solaris
	 */
	public static boolean isSolaris() {
		return OS.indexOf("sunos") >= 0;
	}

	/**
	 * Inidique si l'OS est Unix
	 *
	 * @return true si l'OS est Unix
	 */
	public static boolean isUnix() {
		return OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0;
	}

	/**
	 * Inidique si l'OS est Windows
	 *
	 * @return true si l'OS est Windows
	 */
	public static boolean isWindows() {
		return OS.indexOf("win") >= 0;
	}

}
