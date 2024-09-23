package com.ipn.escom.lit.hrmanagerss.hrmanagerss.controller;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.DepartamentoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.EstadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.PuestoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroDepartamentoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroEstadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroPuestoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
public class DevController {
  private final AdminService adminService;

  //Positions
  @PostMapping("/new-puesto")
  @Operation(summary = "Registrar nuevo puesto")
  public ResponseEntity<PuestoDTO> addPuesto(@RequestBody RegistroPuestoDTO registroPuestoDTO){
    return new ResponseEntity<>(adminService.addPuesto(registroPuestoDTO), HttpStatus.CREATED);
  }

  //States??
  @PostMapping("/new-state")
  @Operation(summary = "Registrar nuevo estado")
  public ResponseEntity<EstadoDTO> addState(@RequestBody RegistroEstadoDTO registroEstadoDTO){
    return new ResponseEntity<>(adminService.addEstado(registroEstadoDTO), HttpStatus.CREATED);
  }

  //Departments
  @PostMapping("/new-department")
  @Operation(summary = "Registrar nuevo departamento")
  public ResponseEntity<DepartamentoDTO> addDepartment(@RequestBody RegistroDepartamentoDTO registroDepartamentoDTO){
    return new ResponseEntity<>(adminService.addDepartment(registroDepartamentoDTO), HttpStatus.CREATED);
  }
}
