package com.gecko.jee.enterprise.mft.persistence.repository.facilite;

import javax.transaction.Transactional;

import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionSortante;

@Transactional
public interface SessionSortanteRepository
		extends UtilisationDeContexteDExecutionAppliqueBaseRepository<SessionSortante> {

}
