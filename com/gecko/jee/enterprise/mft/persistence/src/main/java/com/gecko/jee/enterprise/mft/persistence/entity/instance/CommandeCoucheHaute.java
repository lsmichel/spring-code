package com.gecko.jee.enterprise.mft.persistence.entity.instance;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.gecko.jee.enterprise.mft.persistence.entity.contexte.ValeurDeParamètre;
import com.gecko.jee.enterprise.mft.persistence.entity.facilite.UtilisationDeContexteDExécutionAppliqué;

/**
 * @author olivier
 * @version 1.0
 * @created 21-oct.-2021 14:25:14
 */
@Entity
public class CommandeCoucheHaute {
	@OneToOne
	@JoinColumn(name = "imageAssocie_id", referencedColumnName = "id")
	private ImageDeGdS gdsAssocié;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String identSessionNoyau;

	@ManyToMany
	@JoinTable(name = "cmdCoucheHaute_utilisationCtxDeExecutionApplique", joinColumns = @JoinColumn(name = "commandeCoucheHaute_id"), inverseJoinColumns = @JoinColumn(name = "utilisationDeContexteDExecutionApplique_id"))
	private List<UtilisationDeContexteDExécutionAppliqué> prédéfinition_s;

	@OneToMany
	@JoinColumn(name = "valeurDeParametre_id", referencedColumnName = "id")
	private List<ValeurDeParamètre> valuationLocale_s;

	private int verbositéDemandée;

	/**
	 * @return the gdsAssocié
	 */
	public ImageDeGdS getGdsAssocié() {
		return this.gdsAssocié;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the identSessionNoyau
	 */
	public String getIdentSessionNoyau() {
		return this.identSessionNoyau;
	}

	/**
	 * @return the prédéfinition_s
	 */
	public List<UtilisationDeContexteDExécutionAppliqué> getPrédéfinition_s() {
		return this.prédéfinition_s;
	}

	/**
	 * @return the valuationLocale_s
	 */
	public List<ValeurDeParamètre> getValuationLocale_s() {
		return this.valuationLocale_s;
	}

	/**
	 * @return the verbositéDemandée
	 */
	public int getVerbositéDemandée() {
		return this.verbositéDemandée;
	}

	/**
	 * @param gdsAssocié the gdsAssocié to set
	 */
	public void setGdsAssocié(final ImageDeGdS gdsAssocié) {
		this.gdsAssocié = gdsAssocié;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param identSessionNoyau the identSessionNoyau to set
	 */
	public void setIdentSessionNoyau(final String identSessionNoyau) {
		this.identSessionNoyau = identSessionNoyau;
	}

	/**
	 * @param prédéfinition_s the prédéfinition_s to set
	 */
	public void setPrédéfinition_s(final List<UtilisationDeContexteDExécutionAppliqué> prédéfinition_s) {
		this.prédéfinition_s = prédéfinition_s;
	}

	/**
	 * @param valuationLocale_s the valuationLocale_s to set
	 */
	public void setValuationLocale_s(final List<ValeurDeParamètre> valuationLocale_s) {
		this.valuationLocale_s = valuationLocale_s;
	}

	/**
	 * @param verbositéDemandée the verbositéDemandée to set
	 */
	public void setVerbositéDemandée(final int verbositéDemandée) {
		this.verbositéDemandée = verbositéDemandée;
	}

}// end CommandeCoucheHaute
