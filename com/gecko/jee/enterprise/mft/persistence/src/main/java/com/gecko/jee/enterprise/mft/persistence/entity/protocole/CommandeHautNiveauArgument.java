package com.gecko.jee.enterprise.mft.persistence.entity.protocole;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b> Description: Entité correspondant à un argument d'une commande haut
 * niveau.</b>
 * <p>
 * Cette entité est persistée car la configuration des commandes haut niveau
 * sera modifiable depuis l'application web. Cela sera aussi utilisé dans le
 * contrôle de syntaxe d'une commande haut niveau.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Entity
@Table(name = "commandeHautNiveauArgument")
public class CommandeHautNiveauArgument {

	@ManyToOne(optional = false)
	@JoinColumn(name = "CommandeHauNiveau_id", nullable = false)
	private CommandeHautNiveau commandeHautNiveau;

	/**
	 * Description de l'argument. Utilisée pour les aides dans la webapp et le
	 * shell.
	 */
	private String description;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Nom de l'argument qui représente en fait la lettre d'option dans la commande
	 * haut niveau. <br/>
	 * Exemple: -s
	 */
	private String ident;

	/**
	 * Inidique si cet argument est obligatoire ou optionnel.
	 */
	private boolean obligatoire;

	/**
	 * Type de l'argument pour les contrôles.
	 */
	private String type;

	/**
	 * Valeur par défaut de l'argument.
	 */
	private String valeurParDefaut;

	/**
	 * @return the commandeHautNiveau
	 */
	public CommandeHautNiveau getCommandeHautNiveau() {
		return this.commandeHautNiveau;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	public int getId() {
		return this.id;
	}

	/**
	 * @return the ident
	 */
	public String getIdent() {
		return this.ident;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the valeurParDefaut
	 */
	public String getValeurParDefaut() {
		return this.valeurParDefaut;
	}

	/**
	 * @return the obligatoire
	 */
	public boolean isObligatoire() {
		return this.obligatoire;
	}

	/**
	 * @param commandeHautNiveau the commandeHautNiveau to set
	 */
	public void setCommandeHautNiveau(final CommandeHautNiveau commandeHautNiveau) {
		this.commandeHautNiveau = commandeHautNiveau;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param ident the ident to set
	 */
	public void setIdent(final String ident) {
		this.ident = ident;
	}

	/**
	 * @param obligatoire the obligatoire to set
	 */
	public void setObligatoire(final boolean obligatoire) {
		this.obligatoire = obligatoire;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * @param valeurParDefaut the valeurParDefaut to set
	 */
	public void setValeurParDefaut(final String valeurParDefaut) {
		this.valeurParDefaut = valeurParDefaut;
	}

}
