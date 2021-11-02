package com.gecko.jee.enterprise.mft.persistence.entity.automate;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;

/**
 * @author olivier
 * @version 1.0
 * @created 31-août-2021 16:48:54
 */
@Entity
@Table(name = "garde")
public class Garde {

	private String expression;

	/**
	 * C'est l'Id autogeneré de la table corrrespondande. Il est de type int
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(mappedBy = "gardes")
	private Set<Paramètre> variable_s;

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
	 * @return the variable_s
	 */
	public Set<Paramètre> getVariable_s() {
		return this.variable_s;
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
	 * @param variable_s the variable_s to set
	 */
	public void setVariable_s(final Set<Paramètre> variable_s) {
		this.variable_s = variable_s;
	}

}// end Garde
