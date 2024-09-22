package com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Departamento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepo extends JpaRepository<Departamento, String> {
//  Optional<Departamento> findDepartamentoById(Long id);
//
//  void deleteDepartamentoById(Long id);

  boolean existsByNombre(String nombre);
}
