package com.gecko.jee.enterprise.mft.persistence.repository.automate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gecko.jee.enterprise.mft.persistence.entity.automate.Transition;
import com.gecko.jee.enterprise.mft.persistence.entity.automate.ÉtatÉlémentaire;

public interface TransitionRepository extends JpaRepository<Transition, Integer> {

	List<Transition> findByDestination(ÉtatÉlémentaire etatElementaire);
}
