package com.gecko.jee.enterprise.mft.web.view.component;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

/**
 * <b>Description: Composant du menu latéral.</b>
 * <p>
 * Les items (ici Tab) du menu sont static d'ou l'encapsulation des evenements
 * au click dans la construction.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class DrawerMenuComponent extends VerticalLayout {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 4391228934551820362L;

	public DrawerMenuComponent() {
		// Menu Dashboard
		final Tab dashboardTab = new Tab(VaadinIcon.DASHBOARD.create(), new Span("Dashboard"));
		dashboardTab.getElement().addEventListener("click", e -> {
			dashboardTab.getUI().ifPresent(ui -> ui.navigate("dashboard"));
		});

		// Menu Transfert
		final Tab transfertTab = new Tab(VaadinIcon.FILE_PROCESS.create(), new Span("Transfert"));
		transfertTab.getElement().addEventListener("click", e -> {
			transfertTab.getUI().ifPresent(ui -> ui.navigate("transfert"));
		});

		// Menu Parametrages
		final Tab parametrageTab = new Tab(VaadinIcon.COG_O.create(), new Span("Paramétrage"));
		parametrageTab.getElement().addEventListener("click", e -> {
			parametrageTab.getUI().ifPresent(ui -> ui.navigate("parametrage"));
		});

		final Tabs menuTabs = new Tabs(dashboardTab, transfertTab, parametrageTab);
		// Orientation du menu en vertical pour avoir un alignement des tab
		menuTabs.setOrientation(Tabs.Orientation.VERTICAL);
		// Ajout des items dans la layout du drawer
		this.add(menuTabs);
	}
}
