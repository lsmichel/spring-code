package com.gecko.jee.enterprise.mft.web.view;

import com.gecko.jee.enterprise.mft.exception.TechnicalException;
import com.gecko.jee.enterprise.mft.web.view.component.DrawerMenuComponent;
import com.gecko.jee.enterprise.mft.web.view.component.HeaderComponent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

/**
 * <b>Description: Représente la layout principale de l'application.</b>
 * <p>
 * Il s'agit d'une AppLayout Vaadin pour faire du SPA (Single Page Application).
 * Tout le contenu sera affiché dans cette layout. Ce modèle de layout dispose :
 * <ul>
 * <li>d'un header : bouton pour afficher/masquer le menu latéral, logo, bouton
 * de déconnexion</li>
 * <li>d'un menu latéral (Drawer)</li>
 * <li>d'une zone de contenu à droite</li>
 * </ul>
 *
 * Il n'y a pas de footer dans cette layout.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Theme(value = Material.class)
@Push
public class MainLayout extends AppLayout {

	/**
	 * Cette class nous permettra d'instancier un objet Thread. Cet Thead avec un
	 * intervall de temps ira regarder dans le repository de stockage des
	 * notifications, pour determiner s'il y a une nouvelle notification à
	 * affichier:Cette fonctionnalité est implementée dans la methode run que
	 * redefinie cette class
	 */
	private static class NotifierObserver extends Thread {
		/**
		 * c'est la fréquence à la quelle le thred effectue sa tache decrite plus haut
		 */
		private final Integer frequence = 100;

		/**
		 * le composant ou séront afficher les notifications
		 */
		private final MainLayout mainLayout;

		private final UI ui;

		public NotifierObserver(final UI ui, final MainLayout view) {
			this.ui = ui;
			this.mainLayout = view;
		}

		@Override
		public void run() {
			try {
				// mettre le thread au repos pendant frequence ms
				Thread.sleep(this.frequence);
				/**
				 * A faire: aller chercher la dernier notification l'afficher comme ceci
				 * this.ui.access(() -> Notification.show(""));
				 */
			} catch (final InterruptedException e) {
				/**
				 * A faire :Gestion de l'exeption en attente de la mutualisation entre business
				 * cli et web
				 */
				throw new TechnicalException("");

			}
		}
	}

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -7108587153647024490L;
	/**
	 * thead pour la gestion des notifications: collecte , affichage
	 */
	private NotifierObserver notifierObserver;

	public MainLayout() {
		// création et configuration du header
		final HeaderComponent header = new HeaderComponent();
		header.setClassName("header");
		// ajout du header dans la layout
		this.addToNavbar(header);

		// création et configuration du menu latéral
		final DrawerMenuComponent drawerMenuComponent = new DrawerMenuComponent();
		// ajout du menu latéral dans la layout
		this.addToDrawer(drawerMenuComponent);
		// Le menu latéral sera en-dessous du header
		this.setPrimarySection(Section.NAVBAR);

	}

	@Override
	protected void onAttach(final AttachEvent attachEvent) {
		this.notifierObserver = new NotifierObserver(attachEvent.getUI(), this);
		this.notifierObserver.start();
	}

	@Override
	protected void onDetach(final DetachEvent detachEvent) {
		this.notifierObserver.interrupt();
		this.notifierObserver = null;
	}
}
