package com.gecko.jee.enterprise.mft.web.view.template;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponent;
import com.gecko.jee.enterprise.mft.business.component.ContexteDExecutionComponent;
import com.gecko.jee.enterprise.mft.web.view.LoginView;
import com.gecko.jee.enterprise.mft.web.view.MainLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinServlet;

/**
 * <b>Description: Template vertical pour les écrans (autres que la
 * {@link MainLayout} et {@link LoginView}). Permet de factoriser certains beans
 * et méthodes.</b>
 * <p>
 * Par défaut l'alignement des composants est centré.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public abstract class AbstractViewTemplate extends VerticalLayout {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 7330248322012442301L;

	private CommandeHautNiveauComponent commandeHautNiveauComponent;

	private ContexteDExecutionComponent contexteDExecutionComponent;

	public AbstractViewTemplate() {
		// Alignement centré des composants de la layout
		this.setJustifyContentMode(JustifyContentMode.CENTER);
		this.setAlignItems(FlexComponent.Alignment.CENTER);
	}

	/**
	 * @return the commandeHautNiveauComponent
	 */
	public CommandeHautNiveauComponent getCommandeHautNiveauComponent() {
		if (this.commandeHautNiveauComponent == null) {
			return (CommandeHautNiveauComponent) WebApplicationContextUtils
					.getWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
					.getBean("commandeHautNiveauComponent");

		}
		return this.commandeHautNiveauComponent;
	}

	/**
	 * @return the contexteDExecutionComponent
	 */
	public ContexteDExecutionComponent getContexteDExecutionComponent() {

		if (this.contexteDExecutionComponent == null) {
			return (ContexteDExecutionComponent) WebApplicationContextUtils
					.getWebApplicationContext(VaadinServlet.getCurrent().getServletContext())
					.getBean("contexteDExecutionComponent");

		}
		return this.contexteDExecutionComponent;
	}

}
