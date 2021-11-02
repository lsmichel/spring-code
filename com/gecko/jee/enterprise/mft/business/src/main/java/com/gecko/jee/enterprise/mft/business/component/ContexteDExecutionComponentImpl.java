package com.gecko.jee.enterprise.mft.business.component;

import java.util.List;

import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelé;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionSortante;

public class ContexteDExecutionComponentImpl extends AbstractComponent implements ContexteDExecutionComponent {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ObjectifDeSessionSortante> getObjectifsDeSessionSortantes() {
		// On prend la liste des ObjectifDeSessionSortante.
		return this.objectifDeSessionSortanteRepository.findAll();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PartenaireAppelé> getPartenairesAppeles() {
		// On prend la liste des ObjectifDeSessionSortante.
		return this.partenaireAppeleRepository.findAll();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SessionSortante> getSessionsSortantes() {
		// On prend la liste des ObjectifDeSessionSortante.
		return this.sessionSortanteRepository.findAll();

	}

}
