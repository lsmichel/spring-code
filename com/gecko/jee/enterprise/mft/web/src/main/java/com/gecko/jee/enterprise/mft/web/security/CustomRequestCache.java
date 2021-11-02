package com.gecko.jee.enterprise.mft.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

/**
 * <b>Description: Composant de cache de requête HTTP pour rediriger
 * l'utilisateur après son authentification.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class CustomRequestCache extends HttpSessionRequestCache {

	/**
	 * Enregistre la requête HTTP non authentifiée, de sorte qu'une fois
	 * l'utilisateur connecté, on puisse le rediriger vers la page à laquelle il
	 * tentait d'accéder.
	 */
	@Override
	public void saveRequest(final HttpServletRequest request, final HttpServletResponse response) {
		if (!SecurityUtils.isFrameworkInternalRequest(request)) {
			super.saveRequest(request, response);
		}
	}
}