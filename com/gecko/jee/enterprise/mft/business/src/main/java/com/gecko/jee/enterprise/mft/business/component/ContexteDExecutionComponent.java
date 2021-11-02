package com.gecko.jee.enterprise.mft.business.component;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gecko.jee.enterprise.mft.persistence.entity.facilite.ObjectifDeSessionSortante;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.PartenaireAppelé;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.SessionSortante;

/**
 * <b>Description: ContexteDExecutionComponent</b>
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Service
public interface ContexteDExecutionComponent {
	/**
	 * Cette méthode liste tous les objectifs de session sortantes enregistrés dans
	 * la base de donnée
	 *
	 * @return Une liste contenant les objectifs de session sortantes
	 */
	List<ObjectifDeSessionSortante> getObjectifsDeSessionSortantes();

	/**
	 * Cette méthode liste tous les partenaires sortants enregistrés dans la base de
	 * donnée
	 *
	 * @return Une liste contenant les partenaires sortants
	 */
	List<PartenaireAppelé> getPartenairesAppeles();

	/**
	 * Cette méthode liste toutes les sessions sortantes enregistrées dans la base
	 * de donnée
	 *
	 * @return Une liste contenant les sessions entrantes
	 */
	List<SessionSortante> getSessionsSortantes();
}
