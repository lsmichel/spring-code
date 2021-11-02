package com.gecko.jee.enterprise.mft.cli.shell;

import org.jline.reader.LineReader;
import org.springframework.util.StringUtils;

/**
 * <b>Description: Classe pour la lecture des valeurs saisies par l'utilisateur
 * dans le shell.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class CliInputReader {

	public static final Character DEFAULT_MASK = '*';

	private final LineReader lineReader;
	private final Character mask;

	public CliInputReader(final LineReader lineReader) {
		this(lineReader, null);
	}

	public CliInputReader(final LineReader lineReader, final Character mask) {
		this.lineReader = lineReader;
		this.mask = mask != null ? mask : DEFAULT_MASK;
	}

	public String prompt(final String prompt) {
		return this.prompt(prompt, null, true);
	}

	public String prompt(final String prompt, final String defaultValue) {
		return this.prompt(prompt, defaultValue, true);
	}

	public String prompt(final String prompt, final String defaultValue, final boolean echo) {
		String answer = "";
		if (echo) {
			answer = this.lineReader.readLine(prompt + ": ");
		} else {
			answer = this.lineReader.readLine(prompt + ": ", this.mask);
		}
		if (StringUtils.isEmpty(answer)) {
			return defaultValue;
		}
		return answer;
	}
}
