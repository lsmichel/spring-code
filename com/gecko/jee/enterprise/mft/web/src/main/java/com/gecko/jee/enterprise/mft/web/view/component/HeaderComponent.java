package com.gecko.jee.enterprise.mft.web.view.component;

import com.gecko.jee.enterprise.mft.web.view.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

/**
 * <b>Description: Composant Header de la {@link MainLayout}.</b>
 * <p>
 * Il contient :
 * <ul>
 * <li>le bouton pour afficher/masquer le menu latéral</li>
 * <li>le logo</li>
 * <li>le bouton de déconnexion</li>
 * </ul>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class HeaderComponent extends HorizontalLayout {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -6885344171473426437L;

	public HeaderComponent() {
		// le theme du header (noir)
		this.getThemeList().set("dark", true);

		// la taille du header est à 100% du navbar qui le contiendra
		this.setWidth("100%");

		// On aligne les composants dans le header suivant l'espace qu'il occupe
		this.setAlignItems(FlexComponent.Alignment.CENTER);

		// Ajout du bouton pour masquer/afficher le menu latéral
		this.add(new DrawerToggle());

		// Ajout du logo byGecko
		final Image image = new Image("images/byGecko_fx_final_small.png", "byGecko");
		image.setWidth(90, Unit.PIXELS);
		image.setHeight(60, Unit.PIXELS);
		this.add(image);

		// Ajout du bouton de déconnexion
		// TODO: remplacer ce Tab par un Button pour pouvoir câbler le logout
		final Tab signOutTab = new Tab(VaadinIcon.SIGN_OUT.create(), new Span("Se déconnecter"));
		final Tabs connexionstabs = new Tabs(signOutTab);
		connexionstabs.setOrientation(Tabs.Orientation.HORIZONTAL);
		final HorizontalLayout connexionsLayout = new HorizontalLayout(connexionstabs);
		// mettre le layout connexionstabs en haut à droite
		connexionsLayout.getElement().getStyle().set("margin-left", "auto");
		this.add(connexionsLayout);
	}
}
