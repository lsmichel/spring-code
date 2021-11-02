package com.gecko.jee.enterprise.mft.web.security;

import org.springframework.stereotype.Component;

import com.gecko.jee.enterprise.mft.web.view.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

/**
 * <b>Description: Composant pour intégrer Spring Security à Vaadin et le mode
 * Single Page Application.</b>
 * <p>
 * Spring Security restricts access to content based on paths. Vaadin
 * applications are single-page applications. This means that they do not
 * trigger a full browser refresh when you navigate between views, even though
 * the path does change. To secure a Vaadin application, you need to wire Spring
 * Security to the Vaadin navigation system.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 3153938574776947009L;

	/**
	 * Vérification de l'authentification avant l'accès à une ressource de
	 * l'application web.
	 *
	 * @param event
	 */
	private void authenticateNavigation(final BeforeEnterEvent event) {
		if (!LoginView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()) {
			event.rerouteTo(LoginView.class);
		}
	}

	/**
	 * Intégration du listener Spring dans Vaadin.
	 *
	 * @param event
	 */
	@Override
	public void serviceInit(final ServiceInitEvent event) {
		event.getSource().addUIInitListener(uiEvent -> {
			final UI ui = uiEvent.getUI();
			ui.addBeforeEnterListener(this::authenticateNavigation);
		});
	}
}