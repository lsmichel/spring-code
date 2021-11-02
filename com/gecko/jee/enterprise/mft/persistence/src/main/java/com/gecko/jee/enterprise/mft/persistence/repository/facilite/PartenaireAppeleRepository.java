package com.gecko.jee.enterprise.mft.persistence.repository.facilite;

import javax.transaction.Transactional;

import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelé;

@Transactional
public interface PartenaireAppeleRepository
		extends UtilisationDeContexteDExecutionAppliqueBaseRepository<PartenaireAppelé> {

}
