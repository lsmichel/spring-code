package com.gecko.jee.enterprise.mft.cli.shell;

/**
 * <b>Description: Enumération des codes couleurs pour l'affichage dans le
 * CLI.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public enum CliPromptColor {
	BLACK(0), BLUE(4), BRIGHT(8), CYAN(6), GREEN(2), MAGENTA(5), RED(1), WHITE(7), YELLOW(3);

	private final int value;

	CliPromptColor(final int value) {
		this.value = value;
	}

	public int toJlineAttributedStyle() {
		return this.value;
	}
}