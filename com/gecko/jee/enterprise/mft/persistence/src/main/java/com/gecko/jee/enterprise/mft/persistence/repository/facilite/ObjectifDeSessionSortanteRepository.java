package com.gecko.jee.enterprise.mft.persistence.repository.facilite;

import javax.transaction.Transactional;

import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionSortante;

@Transactional
public interface ObjectifDeSessionSortanteRepository
		extends UtilisationDeContexteDExecutionAppliqueBaseRepository<ObjectifDeSessionSortante> {

}
