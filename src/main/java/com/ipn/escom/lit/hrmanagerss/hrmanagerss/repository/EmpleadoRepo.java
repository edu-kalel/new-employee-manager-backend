package com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Empleado;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepo extends JpaRepository<Empleado, String> {
//  void deleteEmployeeById(Long id);
//
//  Optional<Empleado> findEmployeeById(Long id);

  boolean existsByCurp(String curp);

  boolean existsByEmail(String email);

  Optional<Empleado> findByEmail(String email);

  List<Empleado> findByPuesto_Id(String id);

}
