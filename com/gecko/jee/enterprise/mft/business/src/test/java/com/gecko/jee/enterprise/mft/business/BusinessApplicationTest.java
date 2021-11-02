package com.gecko.jee.enterprise.mft.business;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponent;
import com.gecko.jee.enterprise.mft.business.component.CommandeHautNiveauComponentImpl;
import com.gecko.jee.enterprise.mft.business.component.MonitoringNotifierComponent;
import com.gecko.jee.enterprise.mft.business.component.MonitoringNotifierComponentMock;
import com.gecko.jee.enterprise.mft.business.service.NoyauService;
import com.gecko.jee.enterprise.mft.business.service.NoyauServiceRest;

@TestConfiguration
public class BusinessApplicationTest {
	@Bean
	public CommandeHautNiveauComponent commandeHautNiveauComponent() {
		return new CommandeHautNiveauComponentImpl();
	}

	@Bean
	public MonitoringNotifierComponent monitoringNotifierComponent() {
		return new MonitoringNotifierComponentMock();
	}

	@Bean
	public NoyauService NoyauService() {
		return new NoyauServiceRest();
	}

}
