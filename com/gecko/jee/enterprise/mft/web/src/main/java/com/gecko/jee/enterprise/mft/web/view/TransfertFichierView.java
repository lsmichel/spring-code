package com.gecko.jee.enterprise.mft.web.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gecko.jee.enterprise.mft.exception.BusinessException;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelé;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;
import com.gecko.jee.enterprise.mft.web.view.component.ParametresObligatoiresComponent;
import com.gecko.jee.enterprise.mft.web.view.template.AbstractViewTemplate;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * <b>Description: c'est la view pour le transfert de fichier</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Route(value = "transfert", layout = MainLayout.class)
@PageTitle("Transfert | MFT X-Protocols")
public class TransfertFichierView extends AbstractViewTemplate {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 5908720792610380539L;

	private final Button executerCommandeButton = new Button("executer commande");
	// formulaire d'envoie de commande de haut niveau
	private final FormLayout executerCommandeFormLayout = new FormLayout();
	private ParametresObligatoiresComponent parametresObligatoiresComponent;

	public TransfertFichierView() {
		super();
		// Aligement des elements sur quatre colonnes
		this.executerCommandeFormLayout.setResponsiveSteps(new ResponsiveStep("0", 4));
		// combobox pour charger les partenaires en base de donnée s'il y en a
		final ComboBox<String> selectPartenaire = new ComboBox<>();
		selectPartenaire.setLabel("Partenaire");
		// Recupération de la liste des partenaires pour remplir le combobox de
		// selection de partenaire
		final List<PartenaireAppelé> PartenairesAppeles = this.getContexteDExecutionComponent().getPartenairesAppeles();
		final List<String> selectPartenaireItems = new ArrayList<>();
		for (final PartenaireAppelé partenairesAppele : PartenairesAppeles) {
			selectPartenaireItems.add(partenairesAppele.getIdent());
		}
		selectPartenaire.setItems(selectPartenaireItems);

		// combobox pour charger les session
		final ComboBox<String> selectSession = new ComboBox<>();
		selectSession.setLabel("Session");
		// Recupération de la liste des sessions sortantes pour remplir le combobox de
		// selection de session sortantes
		final List<SessionSortante> sessionsSortantes = this.getContexteDExecutionComponent().getSessionsSortantes();
		final List<String> selectSessionItems = new ArrayList<>();
		for (final SessionSortante sessionsSortante : sessionsSortantes) {
			selectSessionItems.add(sessionsSortante.getIdent());
		}
		selectSession.setItems(selectSessionItems);

		// combobox pour charger objectif de session
		final ComboBox<String> selectObjectifSession = new ComboBox<>();
		selectObjectifSession.setLabel("Objectif de session");
		// Recupération de la liste des Objectifs de sessions sortantes pour remplir le
		// combobox de selection des Objectifs de sessions sortantes
		final List<ObjectifDeSessionSortante> ObjectifsDeSessionSortantes = this.getContexteDExecutionComponent()
				.getObjectifsDeSessionSortantes();
		final List<String> selectObjectifSessionItems = new ArrayList<>();
		for (final ObjectifDeSessionSortante objectifDeSessionSortante : ObjectifsDeSessionSortantes) {
			selectObjectifSessionItems.add(objectifDeSessionSortante.getIdent());
		}
		selectObjectifSession.setItems(selectObjectifSessionItems);
		// Les objets crées actuelement nous aide à pofinier la mise en
		// page de cette view d'execution de commande //
		// il faut preparer aussi les composant pour afficher des messages en cas
		// d'erreur de saisie ou d'incomplétude des paramètre

		// bouton qui permet lancer l'envoie de fichier (voir aussi DCT Business)
		final Button saisirParamètreButton = new Button("saisir les paramètres");
		saisirParamètreButton.setIcon(VaadinIcon.PAPERPLANE.create());
		// En fonction du choix de l'utilisateur des trois champs
		// on construira un menu dynamique correspondant à la commande que l'utilisateur
		// veux effectuer
		saisirParamètreButton.addClickListener(e -> {
			final PartenaireAppelé partenaireAppelé = new PartenaireAppelé();
			partenaireAppelé.setIdent(selectPartenaire.getValue());
			final SessionSortante sessionSortante = new SessionSortante();
			sessionSortante.setIdent(selectSession.getValue());
			final ObjectifDeSessionSortante objectifSessionSortante = new ObjectifDeSessionSortante();
			objectifSessionSortante.setIdent(selectObjectifSession.getValue());
			final UtilisationDeContexteDExécutionAppliqué[] contexteDExecutionAppliques = new UtilisationDeContexteDExécutionAppliqué[3];
			contexteDExecutionAppliques[0] = partenaireAppelé;
			contexteDExecutionAppliques[1] = sessionSortante;
			contexteDExecutionAppliques[2] = objectifSessionSortante;
			final List<Paramètre> parametresObligatoires = this.getCommandeHautNiveauComponent()
					.getParametresObligatoires(contexteDExecutionAppliques);
			// On enlève le composant des paramètres obligatoires manquantes pour en créer
			// un autre
			if (this.parametresObligatoiresComponent != null) {
				this.remove(this.parametresObligatoiresComponent);
			}
			this.parametresObligatoiresComponent = new ParametresObligatoiresComponent(parametresObligatoires);
			this.add(this.parametresObligatoiresComponent);
			this.executerCommandeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			this.executerCommandeButton.setIcon(VaadinIcon.UPLOAD.create());
			this.add(this.executerCommandeButton);

			this.executerCommandeButton.addClickListener(ev -> {
				// Création de map pour transmètre les paramètres suplementaires
				final Map<String, String> parametresComplementaires = new HashMap<>();
				// On parcours La liste des TextFields des paramètres suplementaires pour
				// remplir le map des paramètres suplémentaires
				this.parametresObligatoiresComponent.getParametersTextField().forEach(textField -> {
					parametresComplementaires.put(textField.getLabel(), textField.getValue());
				});
				try {
					this.getCommandeHautNiveauComponent().executerCommandeHautNiveau(partenaireAppelé, sessionSortante,
							objectifSessionSortante, parametresComplementaires);
				} catch (final BusinessException e1) {
					// En cas d'erreur on envoie une notification à l'utilisateur
					Notification.show("une ereur s'est produite pendant l'execution de la commande");
				}
			});
		});

		this.executerCommandeFormLayout.add(selectPartenaire, 0);

		this.executerCommandeFormLayout.add(selectSession, 0);

		this.executerCommandeFormLayout.add(selectObjectifSession, 0);

		this.executerCommandeFormLayout.add(saisirParamètreButton, 0);

		this.add(this.executerCommandeFormLayout);
	}

}
