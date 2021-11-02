package com.gecko.jee.enterprise.mft.cli.shell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.gecko.jee.enterprise.mft.cli.CliApplication;
import com.gecko.jee.enterprise.mft.cli.CliApplicationTest;

/**
 * <b>Description: Classe de tests unitaires du shell.</b>
 * <p>
 * On peut ainsi exécuter des commandes. Le shell est chargé au démarrage mais
 * il n'est pas en mode interactif: on appelle les commandes dans le test.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@SpringBootTest(classes = { CliApplication.class, CliApplicationTest.class }, properties = {
		InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
		ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
		"spring.main.allow-bean-definition-overriding=true" })
@RunWith(SpringRunner.class)
public class CliShellComponentTest {

	/**
	 * Injection du bean shell à tester.
	 */
	@Autowired
	CliShellComponent cliShellComponent;

	/**
	 * Test method for
	 * {@link com.gecko.jee.enterprise.mft.cli.shell.CliShellComponent#listMacro()}.
	 */
	@Test
	public void testListCommands() {
		final String listeCommandesMacro = this.cliShellComponent.listCommands();
		System.out.println(listeCommandesMacro);
	}

}
