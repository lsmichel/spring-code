package com.gecko.jee.enterprise.mft.web.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;

/**
 * <b>Description: Service Spring lié à la sécurité pour récupérer des
 * informations sur l'utilisateur connecté et pouvoir le déconnecter.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Component
public class SecurityService {

	/**
	 * On revient à la racine de la webapp lors d'un logout.
	 */
	private static final String LOGOUT_SUCCESS_URL = "/";

	/**
	 * Permet de récupérer les informations de l'utilisateur connecté.
	 *
	 * @return les détails de l'utilisateur connecté
	 */
	public UserDetails getAuthenticatedUser() {
		final SecurityContext context = SecurityContextHolder.getContext();
		final Object principal = context.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserDetails) context.getAuthentication().getPrincipal();
		}
		// Anonymous or no authentication.
		return null;
	}

	/**
	 * Méthode pour déconnecter l'utilisateur et terminer sa session web.
	 */
	public void logout() {
		UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
		final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
	}
}