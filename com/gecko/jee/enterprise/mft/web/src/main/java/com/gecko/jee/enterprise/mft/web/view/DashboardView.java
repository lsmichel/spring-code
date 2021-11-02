package com.gecko.jee.enterprise.mft.web.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ValeurDeParamètre;
import com.gecko.jee.enterprise.mft.persistence.entity.instance.CommandeCoucheHaute;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;
import com.gecko.jee.enterprise.mft.web.view.template.AbstractViewTemplate;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 * <b>Description: Dashboard général, il présente différentes sections sur les
 * flux en cours/passés.</b>
 * <p>
 * </p>
 *
 * @RouteAlias: permet d'indiquer que cette view est affichée par défaut après
 *              le login.
 *
 * @author GECKO SOFTWARE
 */
@RouteAlias(value = "", layout = MainLayout.class)
@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | MFT X-Protocols")
public class DashboardView extends AbstractViewTemplate {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -3998975278831848979L;
	private final Grid<CommandeCoucheHaute> commandeCoucheHauteGrid;

	public DashboardView() {
		this.add(new H2("This place intentionally left empty"));
		this.add(new Paragraph("Vue Dashboard des transferts de fichiers 🤗"));

		this.setSizeFull();
		this.setJustifyContentMode(JustifyContentMode.CENTER);
		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.getStyle().set("text-align", "center");
		this.commandeCoucheHauteGrid = new Grid<>(CommandeCoucheHaute.class, false);
		this.commandeCoucheHauteGrid.addComponentColumn(cmd -> {
			final List<String> valeurDeParamètreItems = cmd.getValuationLocale_s().stream()
					.map(valParam -> valParam.getValeurSérialiséeChnCar() + " " + valParam.getIdentEtType().getIdent())
					.collect(Collectors.toList());
			final ComboBox<String> valeurDeParamètreCombo = new ComboBox<>();
			valeurDeParamètreCombo.setItems(valeurDeParamètreItems);
			return valeurDeParamètreCombo;

		}).setHeader("valeur de paramètre");
		final Paramètre paramètre1 = new Paramètre();
		paramètre1.setIdent("chemin fichier");
		final ValeurDeParamètre valeurDeParamètre1 = new ValeurDeParamètre();
		valeurDeParamètre1.setIdentEtType(paramètre1);
		valeurDeParamètre1.setValeurSérialiséeChnCar("test1");

		final Paramètre paramètre2 = new Paramètre();
		paramètre2.setIdent("destination fichier");
		final ValeurDeParamètre valeurDeParamètre2 = new ValeurDeParamètre();
		valeurDeParamètre2.setIdentEtType(paramètre2);
		valeurDeParamètre2.setValeurSérialiséeChnCar("test2");

		final Paramètre paramètre3 = new Paramètre();
		paramètre3.setIdent("ip adresse");
		final ValeurDeParamètre valeurDeParamètre3 = new ValeurDeParamètre();
		valeurDeParamètre3.setIdentEtType(paramètre3);
		valeurDeParamètre3.setValeurSérialiséeChnCar("test3");
		final CommandeCoucheHaute CommandeCoucheHaute1 = new CommandeCoucheHaute();
		CommandeCoucheHaute1
				.setValuationLocale_s(Arrays.asList(valeurDeParamètre2, valeurDeParamètre1, valeurDeParamètre3));
		this.commandeCoucheHauteGrid.setItems(Arrays.asList(CommandeCoucheHaute1, CommandeCoucheHaute1));
		this.add(this.commandeCoucheHauteGrid);
	}
}
