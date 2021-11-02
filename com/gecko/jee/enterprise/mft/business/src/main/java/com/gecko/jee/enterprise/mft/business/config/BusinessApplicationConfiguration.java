package com.gecko.jee.enterprise.mft.business.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gecko.jee.enterprise.mft.business.BusinessApplication;

/**
 * <b>Description: Configuration Spring pour le chargement des beans de la
 * couche Business et de la couche Persistence.</b>
 * <p>
 * On active aussi les transactions JPA pour que les couches Web et CLI puissent
 * utiliser le @Transactional.
 * </p>
 *
 * @author GECKO SOFTWARE
 */
@Configuration
@EnableJpaRepositories("com.gecko.jee.enterprise.mft.persistence.repository")
@EntityScan("com.gecko.jee.enterprise.mft.persistence.entity")
@PropertySources({ @PropertySource("classpath:application.properties"),
		@PropertySource("classpath:businessException.properties"), @PropertySource("classpath:config.properties") })
@ComponentScan(basePackages = { "com.gecko.jee.enterprise.mft.persistence.initialize",
		"com.gecko.jee.enterprise.mft.business" }, basePackageClasses = BusinessApplication.class)
@EnableTransactionManagement
public class BusinessApplicationConfiguration {

}
