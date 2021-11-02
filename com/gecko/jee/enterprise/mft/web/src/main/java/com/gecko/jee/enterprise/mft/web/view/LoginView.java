package com.gecko.jee.enterprise.mft.web.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginI18n.Form;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * <b>C'est la page d'authentification de notre web appli mft</b>
 * <p>
 * basé sur le composant LoginForm de vaadin et l'authentification est faite
 * avec spring security
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Route("login")
@PageTitle("Login | MFT X-Protocols")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
	// composant LoginForm (formulaire de connexion)
	private final LoginForm login = new LoginForm();

	public LoginView() {
		this.addClassName("login-view");
		this.setSizeFull();
		// alignement du formulaire au centre
		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.CENTER);

		// tritre du formulaire
		this.login.setAction("login");
		// configuration de l'internalization
		final LoginI18n i18n = LoginI18n.createDefault();
		// changer le label du bouton de mot de passe oublié
		final Form form = i18n.getForm();
		form.setForgotPassword("Mot de pass oublié ?");
		// i18n.setAdditionalInformation("To close the login form submit non-empty
		// username and password");
		this.login.setI18n(i18n);

		this.add(new H1("MFT X-Protocols"), this.login);
	}

	@Override
	public void beforeEnter(final BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
			this.login.setError(true);
		}
	}
}
