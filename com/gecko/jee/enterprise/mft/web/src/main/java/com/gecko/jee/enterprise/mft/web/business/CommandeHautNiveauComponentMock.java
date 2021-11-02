package com.gecko.jee.enterprise.mft.web.business;

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

	@Override
	public List<CommandeHautNiveau> getCommandesHautNiveau() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paramètre> getParametresFonctionnalités(final List<Fonctionnalité> fonctionnalites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paramètre> getParametresObligatoires(
			final UtilisationDeContexteDExécutionAppliqué... utilisationDeContexteDExecutionAppliques) {
		final List<Paramètre> parameters = new ArrayList<>();
		final Paramètre parameter1 = new Paramètre();
		parameter1.setIdent("fichier source");
		parameter1.setExprImpliciteSiFacult("mftTest.txt");
		final Paramètre parameter2 = new Paramètre();
		parameter2.setIdent("fichier destination");
		parameter2.setExprImpliciteSiFacult("mftTest");
		parameters.add(parameter2);
		parameters.add(parameter1);
		return parameters;
	}

}
