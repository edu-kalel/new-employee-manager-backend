package com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Estado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepo extends JpaRepository<Estado, String> {
//  Optional<Estado> findEstadoById(Long id);
//
//  void deleteEstadoById(Long id);

  boolean existsByNombre(String nombre);
}

