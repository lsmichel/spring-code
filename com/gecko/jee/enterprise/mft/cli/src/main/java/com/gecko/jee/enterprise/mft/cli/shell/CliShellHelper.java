package com.gecko.jee.enterprise.mft.cli.shell;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Value;

import com.gecko.jee.enterprise.mft.exception.BusinessException;

/**
 * <b>Description: Composant pour la mise en forme des messages en couleur ou
 * selon un type (erreur, info...).</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class CliShellHelper {

	@Value("${mft.cli.prompt.out.error}")
	public String errorColor;

	@Value("${mft.cli.prompt.out.info}")
	public String infoColor;

	@Value("${mft.cli.prompt.out.success}")
	public String successColor;

	private final Terminal terminal;

	@Value("${mft.cli.prompt.out.warning}")
	public String warningColor;

	public CliShellHelper(final Terminal terminal) {
		this.terminal = terminal;
	}

	public String getColored(final String message, final CliPromptColor color) {
		return new AttributedStringBuilder()
				.append(message, AttributedStyle.DEFAULT.foreground(color.toJlineAttributedStyle())).toAnsi();
	}

	public String getErrorMessage(final String message) {
		return this.getColored(message, CliPromptColor.valueOf(this.errorColor));
	}

	public String getInfoMessage(final String message) {
		return this.getColored(message, CliPromptColor.valueOf(this.infoColor));
	}

	public String getSuccessMessage(final String message) {
		return this.getColored(message, CliPromptColor.valueOf(this.successColor));
	}

	public String getWarningMessage(final String message) {
		return this.getColored(message, CliPromptColor.valueOf(this.warningColor));
	}

	/**
	 * Print message to the console in the default color.
	 *
	 * @param message message to print
	 */
	public void print(final String message) {
		this.print(message, null);
	}

	/**
	 * Generic Print to the console method.
	 *
	 * @param message message to print
	 * @param color   (optional) prompt color
	 */
	public void print(final String message, final CliPromptColor color) {
		String toPrint = message;
		if (color != null) {
			toPrint = this.getColored(message, color);
		}
		this.terminal.writer().println(toPrint);
		this.terminal.flush();
	}

	/**
	 * Print message to the console in the error color.
	 *
	 * @param message message to print
	 */
	public void printError(final BusinessException businessException) {
		this.print(businessException.getMessage(), CliPromptColor.valueOf(this.errorColor));
	}

	/**
	 * Print message to the console in the error color.
	 *
	 * @param message message to print
	 */
	public void printError(final String message) {
		this.print(message, CliPromptColor.valueOf(this.errorColor));
	}

	/**
	 * Print message to the console in the info color.
	 *
	 * @param message message to print
	 */
	public void printInfo(final String message) {
		this.print(message, CliPromptColor.valueOf(this.infoColor));
	}

	/**
	 * Print message to the console in the success color.
	 *
	 * @param message message to print
	 */
	public void printSuccess(final String message) {
		this.print(message, CliPromptColor.valueOf(this.successColor));
	}

	/**
	 * Print message to the console in the warning color.
	 *
	 * @param message message to print
	 */
	public void printWarning(final String message) {
		this.print(message, CliPromptColor.valueOf(this.warningColor));
	}

}
