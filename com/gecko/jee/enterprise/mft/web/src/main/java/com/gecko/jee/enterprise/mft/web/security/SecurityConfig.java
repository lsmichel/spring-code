package com.gecko.jee.enterprise.mft.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * <b>Description: Composant de configuration Spring Security pour l'application
 * Web.</b>
 * <p>
 * C'est ici que l'on définit les URLs des ressources sécurisées / non
 * sécurisées.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_FAILURE_URL = "/login?error";
	private static final String LOGIN_PROCESSING_URL = "/login";
	private static final String LOGIN_URL = "/login";
	private static final String LOGOUT_SUCCESS_URL = "/login";

	/**
	 * Méthode permettant de configurer l'accès aux pages (autoriser , interdir ou
	 * autoriser après authentification).
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// Vaadin handles CSRF internally
		http.csrf().disable()
				// Enregistrez notre CustomRequestCache, qui enregistre les tentatives d'accès
				// non autorisées, afin que l'utilisateur soit redirigé après la connexion.
				.requestCache().requestCache(new CustomRequestCache())
				// Restreindre l'accès aux url de notre application.
				.and().authorizeRequests()
				// Permettre les requettes internes à Vaadin
				.requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll().antMatchers("/notify")
				.permitAll()
				// Autoriser toutes les requettes des utilisateurs connectés.
				.anyRequest().authenticated()
				// Configuration de la page de login et permission de faire des requette vers
				// cette page
				.and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
				.failureUrl(LOGIN_FAILURE_URL)
				// Configuration de l'url de logout
				.and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
	}

	/**
	 * Permet l'accès aux ressources statiques, en contournant Spring Security.
	 */
	@Override
	public void configure(final WebSecurity web) {
		web.ignoring().antMatchers(
				// les codes javascript
				"/VAADIN/**",

				// favicon URI
				"/favicon.ico",

				// the robots exclusion standard
				"/robots.txt",

				// web application manifest
				"/manifest.webmanifest", "/sw.js", "/offline.html",

				// icons et images
				"/icons/**", "/images/**", "/styles/**",

				// (en mode development ) H2 console de deboguage
				"/h2-console/**");
	}

	/**
	 * Cette méthode est utilisée pour récupérer les données relatives à
	 * l'utilisateur. Elle est appelée par le {@link DaoAuthenticationProvider} pour
	 * charger les détails de l'utilisateur lors de l'authentification.<br/>
	 * TODO Implementer notre propre user repository avec une base de donnée ou un
	 * gestionnaire d'identité comme WSO2 IS.
	 */
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		// Pour le moment, nous mettons un bouchon avec un user géré 'in memory'
		final UserDetails user = User.withUsername("user").password("{noop}userpass").roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}
}