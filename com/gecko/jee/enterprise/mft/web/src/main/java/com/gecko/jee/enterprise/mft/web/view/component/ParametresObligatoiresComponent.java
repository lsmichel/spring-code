package com.gecko.jee.enterprise.mft.web.view.component;

import java.util.ArrayList;
import java.util.List;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ParametresObligatoiresComponent extends FormLayout {
	private static final long serialVersionUID = 1L;

	/**
	 * Liste de TextField permettant de garder un lien avec les TextFields de
	 * paramètres suplementaires pour qu'ils soit accecibles depuis la view
	 * d'exécution de commande.
	 */
	private List<TextField> parametersTextField;

	public ParametresObligatoiresComponent(final List<Paramètre> parameters) {
		this.parametersTextField = new ArrayList<>();
		// Aligement des elements sur quatre colonnes
		this.setResponsiveSteps(new ResponsiveStep("0", 4));
		this.setVisible(true);
		parameters.stream().forEach(parametre -> {
			final TextField parametreTextField = new TextField();
			parametreTextField.setLabel(parametre.getIdent());
			parametreTextField.setPlaceholder(parametre.getExprImpliciteSiFacult());
			this.add(parametreTextField);
			// On conserve les TextFields des paramètres supplémentaires dans une liste pour
			// pouvoir les récupérer dans la view.
			this.parametersTextField.add(parametreTextField);
		});
	}

	/**
	 * @return the parametersTextField
	 */
	public List<TextField> getParametersTextField() {
		return this.parametersTextField;
	}

	/**
	 * @param parametersTextField the parametersTextField to set
	 */
	public void setParametersTextField(final List<TextField> parametersTextField) {
		this.parametersTextField = parametersTextField;
	}
}
