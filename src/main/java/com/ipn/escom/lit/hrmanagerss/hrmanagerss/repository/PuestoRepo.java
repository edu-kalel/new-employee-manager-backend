package com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Puesto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuestoRepo extends JpaRepository<Puesto, String> {
//  Optional<Puesto> findPuestoById(Long id);
//
//  void deletePuestoById(Long id);

  boolean existsByNombre(String nombre);
}
