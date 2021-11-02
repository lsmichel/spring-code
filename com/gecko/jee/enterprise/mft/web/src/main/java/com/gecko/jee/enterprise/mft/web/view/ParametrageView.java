package com.gecko.jee.enterprise.mft.web.view;

import com.gecko.jee.enterprise.mft.web.view.template.AbstractViewTemplate;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * <b>Description: Paramétrages de l'application: configurations du noyau,
 * contextes d'exécution...</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Route(value = "parametrage", layout = MainLayout.class)
@PageTitle("Paramétrages | MFT X-Protocols")
public class ParametrageView extends AbstractViewTemplate {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -1079010805176120153L;

	public ParametrageView() {
		this.add(new H2("Bienvenue"));
		this.add(new Paragraph("la vue pour le parametrage 🤗"));

		this.setSizeFull();
		this.setJustifyContentMode(JustifyContentMode.CENTER);
		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.getStyle().set("text-align", "center");
	}
}
