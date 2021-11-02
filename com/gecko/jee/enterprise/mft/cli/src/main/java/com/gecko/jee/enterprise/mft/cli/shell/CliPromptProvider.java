package com.gecko.jee.enterprise.mft.cli.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;;

/**
 * <b>Description: Classe de personnalisation du shell.</b>
 * <p>
 * On peut changer le prompt ainsi que les couleurs, message d'accueil.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Component
public class CliPromptProvider implements PromptProvider {

	/**
	 * Personnalisation du prompt (cf. fichier messages.properties)
	 */
	@Value("${mft.cli.prompt}")
	private String mftCliPrompt;

	/**
	 * Personnalisation du prompt
	 */
	@Override
	public AttributedString getPrompt() {
		return new AttributedString(this.mftCliPrompt, AttributedStyle.DEFAULT.foreground(AttributedStyle.WHITE));
	}

}
