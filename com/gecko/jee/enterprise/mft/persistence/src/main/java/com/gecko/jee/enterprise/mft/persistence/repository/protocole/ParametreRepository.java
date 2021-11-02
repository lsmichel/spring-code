package com.gecko.jee.enterprise.mft.persistence.repository.protocole;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gecko.jee.enterprise.mft.persistence.entity.protocole.Paramètre;

public interface ParametreRepository extends JpaRepository<Paramètre, Integer> {
	List<Paramètre> findByIdent(String ident);
}
