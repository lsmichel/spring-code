package com.gecko.jee.enterprise.mft.web.view.component;

import com.gecko.jee.enterprise.mft.web.view.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * <b>Description: Composant Footer.</b>
 * <p>
 * Actuellement n'est pas utilisé car le {@link MainLayout} ne prévoit pas de
 * footer. FIXME: A supprimer ?
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class FooterComponent extends HorizontalLayout {

	public FooterComponent() {
		this.getThemeList().set("dark", true);
		this.setWidthFull();
		this.setSpacing(false);
		// alignement
		this.setAlignItems(FlexComponent.Alignment.CENTER);
		final Button home = new Button("Accueil", new Icon(VaadinIcon.HOME));
		final Button info = new Button("Plus d'info", new Icon(VaadinIcon.INFO));
		final Button licence = new Button("Tous droits réservés", new Icon(VaadinIcon.COPYRIGHT));

		// Set the width
		for (final Button button : new Button[] { home, info, licence }) {
			button.setWidth("20%");
		}
		this.add(home);
		this.add(licence);
		this.add(licence);

	}
}
