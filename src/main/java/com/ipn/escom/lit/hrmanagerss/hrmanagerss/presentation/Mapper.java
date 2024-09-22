package com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Departamento;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Empleado;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Estado;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Puesto;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.DepartamentoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.EmpleadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.EstadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.PuestoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

  public EmpleadoDTO mapToEmpleadoDTO(Empleado empleado) {
    return new EmpleadoDTO(
      empleado.getId(),
      (empleado.getName() + " " + empleado.getFirstSurname() + " " + empleado.getSecondSurname()),
      empleado.getEmail(),
      empleado.getCurp(),
      empleado.getPuesto().getNombre(),
      empleado.getDepartamento().getNombre(),
      empleado.getEstado().getNombre(),
      empleado.getRol().toString()
    );
  }


  public DepartamentoDTO mapToDepartamentoDTO(Departamento departamento) {
    String estadoActivo = departamento.isEstado()?"Activo":"Inactivo";
    return new DepartamentoDTO(
      departamento.getId(),
      departamento.getNombre(),
      estadoActivo
    );
  }

  public PuestoDTO mapToPuestoDTO(Puesto puesto) {
    String estadoActivo = puesto.isEstado()?"Activo":"Inactivo";
    return new PuestoDTO(
      puesto.getId(),
      puesto.getNombre(),
      estadoActivo
    );
  }

  public EstadoDTO mapToEstadoDTO(Estado estado) {
    String estadoActivo = estado.isEstado()?"Activo":"Inactivo";
    return new EstadoDTO(
      estado.getId(),
      estado.getNombre(),
      estadoActivo
    );
  }
}
