package com.gecko.jee.enterprise.mft.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponent;
import com.gecko.jee.enterprise.mft.exception.BusinessException;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionEntrante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelant;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelé;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionEntrante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.CommandeHautNiveau;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Fonctionnalité;
import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;

/**
 * <b>Description: Implémentation bouchon du composant business des commandes
 * macro pour les tests unitaires.</b>
 * <p>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
public class CommandeHautNiveauComponentMock implements CommandeHautNiveauComponent {

	@Override
	public void controleParametresObligatoires(final List<Fonctionnalité> fonctionnalités,
			final UtilisationDeContexteDExécutionAppliqué partenaireAppele,
			final UtilisationDeContexteDExécutionAppliqué sessionSortante,
			final UtilisationDeContexteDExécutionAppliqué objectifDeSessionSortante,
			final Map<String, String> parametresComplementaires) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BusinessException> controlerCommandeHautNiveau(final PartenaireAppelant partenaireAppelant,
			final SessionEntrante sessionEntrante, final ObjectifDeSessionEntrante objectifDeSessionEntrante,
			final Map<String, String> parametres) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessException> controlerCommandeHautNiveau(final String commandeEtArguments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executerCommandeHautNiveau(final PartenaireAppelé partenaireAppele,
			final SessionSortante sessionSortant, final ObjectifDeSessionSortante objectifDeSessionSortante,
			final Map<String, String> parametresComplementaires) throws BusinessException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}. Implémentation bouchonnée.
	 */
	@Override
	public List<CommandeHautNiveau> getCommandesHautNiveau() {
		final List<CommandeHautNiveau> CommandeHautNiveaus = new ArrayList<>();
		final CommandeHautNiveau commandeHautNiveau = new CommandeHautNiveau();
		commandeHautNiveau.setIdent("FTP");
		commandeHautNiveau.setDescription("protocole FTP port 21");
		CommandeHautNiveaus.add(commandeHautNiveau);
		final CommandeHautNiveau commandeHautNiveau2 = new CommandeHautNiveau();
		commandeHautNiveau2.setIdent("SFTP");
		commandeHautNiveau2.setDescription("protocole SFTP port 22");
		CommandeHautNiveaus.add(commandeHautNiveau2);
		return CommandeHautNiveaus;
	}

	@Override
	public List<Paramètre> getParametresFonctionnalités(final List<Fonctionnalité> fonctionnalites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paramètre> getParametresObligatoires(
			final UtilisationDeContexteDExécutionAppliqué... utilisationDeContexteDExecutionAppliques) {
		// TODO Auto-generated method stub
		return null;
	}

}
