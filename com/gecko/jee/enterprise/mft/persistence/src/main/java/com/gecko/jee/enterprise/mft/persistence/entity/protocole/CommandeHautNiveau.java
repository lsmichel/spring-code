package com.gecko.jee.enterprise.mft.persistence.entity.protocole;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b> Description: Entité correspondant à une commande haut niveau.</b>
 * <p>
 * Elle représente une commande de "haut niveau" (ex: Transfert de fichier FTP).
 * Cette entité est persistée car la configuration des commandes haut niveau
 * sera modifiable depuis l'application web. Cela sera aussi utilisé dans le
 * contrôle de syntaxe d'une commande haut niveau, et dans la webapp pour
 * générer les formulaires.
 * </p>
 *
 * mft-transfer <prot> <id_part> <ficsrc> <ficdest> mft-transfer ftp
 * partenaire01 fichier_source fichier_dest
 *
 * mft-exec <prot> <id_part> <commande>
 *
 * @author GECKO SOFTWARE
 */
@Entity
@Table(name = "commandeHautNiveau")
public class CommandeHautNiveau {

	/**
	 * Arguments de la commande. Une CommandeHautNiveau a 0 ou plusieurs argument
	 */
	@OneToMany(mappedBy = "commandeHautNiveau", cascade = CascadeType.ALL)
	private Set<CommandeHautNiveauArgument> commandeHautNiveauArguments = new HashSet<>();

	/**
	 * Description de la commande. Utilisée pour les aides dans la webapp et le
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
	 * Nom de la commande (ex: ftp-put)
	 */
	private String ident;

	/**
	 * @return the commandeHautNiveauArguments
	 */
	public Set<CommandeHautNiveauArgument> getCommandeHautNiveauArguments() {
		return this.commandeHautNiveauArguments;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the id
	 */
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
	 * @param commandeHautNiveauArguments the commandeHautNiveauArguments to set
	 */
	public void setCommandeHautNiveauArguments(final Set<CommandeHautNiveauArgument> commandeHautNiveauArguments) {
		this.commandeHautNiveauArguments = commandeHautNiveauArguments;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the id to set
	 */
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
	 * Cette méthode toString peut être utilisée dans les messages d'aide de la
	 * webapp et du shell.
	 */
	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Commande haut niveau ");
		stringBuilder.append(this.ident);
		stringBuilder.append(" - Description: ");
		stringBuilder.append(this.description);
		stringBuilder.append(" - Arguments: \n");
		if (this.commandeHautNiveauArguments != null) {
			for (final CommandeHautNiveauArgument commandeHautNiveauArgument : this.commandeHautNiveauArguments) {
				stringBuilder.append(" * Argument ");
				stringBuilder.append(commandeHautNiveauArgument.getIdent());
				stringBuilder.append(" * Description: ");
				stringBuilder.append(commandeHautNiveauArgument.getDescription());
				stringBuilder.append(" * Obligatoire: ");
				stringBuilder.append(commandeHautNiveauArgument.isObligatoire());
				stringBuilder.append(" * Valeur par defaut");
				stringBuilder.append(commandeHautNiveauArgument.getValeurParDefaut());
			}
		}
		return stringBuilder.toString();
	}

}
