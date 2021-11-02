package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:48:53
 */
@Entity
@Table(name = "affectation")
public class Affectation {

	private String expression;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 *
	 */
	@ManyToOne
	@JoinColumn(name = "parametreVariableEx_id", referencedColumnName = "id")
	private Paramètre variableEx;

	/**
	 *
	 */
	@ManyToMany(mappedBy = "affectations")
	private Set<Paramètre> variableIn_s;

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return this.expression;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the variableEx
	 */
	public Paramètre getVariableEx() {
		return this.variableEx;
	}

	/**
	 * @return the variableIn_s
	 */
	public Set<Paramètre> getVariableIn_s() {
		return this.variableIn_s;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(final String expression) {
		this.expression = expression;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param variableEx the variableEx to set
	 */
	public void setVariableEx(final Paramètre variableEx) {
		this.variableEx = variableEx;
	}

	/**
	 * @param variableIn_s the variableIn_s to set
	 */
	public void setVariableIn_s(final Set<Paramètre> variableIn_s) {
		this.variableIn_s = variableIn_s;
	}
}// end Affectation
