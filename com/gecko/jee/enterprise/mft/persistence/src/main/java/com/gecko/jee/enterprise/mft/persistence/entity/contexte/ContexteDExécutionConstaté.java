package com.gecko.jee.enterprise.mft.persistence.entity.contexte;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author olivier
 * @version 1.0
 * @created 21-sept.-2021 14:27:47
 */

@Entity(name = "contexteDExecutionConstate")
@Table(name = "contexteDExecutionConstate")
public class ContexteDExécutionConstaté extends ContexteDExécution {

	private String exprLogique;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "contexteDExecutionConstate_id", referencedColumnName = "id")
	private List<TestSurParamètres> tests;

	/**
	 * @return the exprLogique
	 */
	public String getExprLogique() {
		return this.exprLogique;
	}

	/**
	 * @return the tests
	 */
	public List<TestSurParamètres> getTests() {
		return this.tests;
	}

	/**
	 * @param exprLogique the exprLogique to set
	 */
	public void setExprLogique(final String exprLogique) {
		this.exprLogique = exprLogique;
	}

	/**
	 * @param tests the tests to set
	 */
	public void setTests(final List<TestSurParamètres> tests) {
		this.tests = tests;
	}

}// end ContexteDExécutionConstaté
