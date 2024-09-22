package com.ipn.escom.lit.hrmanagerss.hrmanagerss.controller;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.ActualizaDepartamentoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.ActualizaEmpleadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.ActualizarEstadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.ActualizarPuestoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.DepartamentoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.EmpleadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.EstadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.PuestoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroDepartamentoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroEmpleadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroEstadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroPuestoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  //Users
  @PostMapping("/new-user")
  @Operation(summary = "Registrar nuevo empleado")
  public ResponseEntity<EmpleadoDTO> registerNewUser(@RequestBody RegistroEmpleadoDTO registroEmpleadoDTO){
    return new ResponseEntity<>(adminService.registerNewUser(registroEmpleadoDTO), HttpStatus.CREATED);
  }

  @PutMapping("/update-user")
  @Operation(summary = "Actualizar empleado")
  public ResponseEntity<EmpleadoDTO> updateUser(@RequestBody ActualizaEmpleadoDTO actualizaEmpleadoDTO){
    return new ResponseEntity<>(adminService.updateUser(actualizaEmpleadoDTO), HttpStatus.OK);
  }

  @DeleteMapping("/delete-user/{id}")
  @Operation(summary = "Eliminar empleado")
  public ResponseEntity<String> deleteUser(@PathVariable String id){
    return new ResponseEntity<>(adminService.deleteUser(id), HttpStatus.OK);
  }

  @GetMapping("/user/{id}")
  @Operation(summary = "Obtener info de un empleado")
  public ResponseEntity<EmpleadoDTO> getUserInfo(@PathVariable String id){
    return new ResponseEntity<>(adminService.getUser(id), HttpStatus.OK);
  }

  @GetMapping("/all-users")
  @Operation(summary = "Obtener lista de empleados")
  public ResponseEntity<List<EmpleadoDTO>> getAllUsers(){
    return new ResponseEntity<>(adminService.getAllUsers(), HttpStatus.OK);
  }

  //Departments
  @PostMapping("/new-department")
  @Operation(summary = "Registrar nuevo departamento")
  public ResponseEntity<DepartamentoDTO> addDepartment(@RequestBody RegistroDepartamentoDTO registroDepartamentoDTO){
    return new ResponseEntity<>(adminService.addDepartment(registroDepartamentoDTO), HttpStatus.CREATED);
  }

  @PutMapping("/update-department")
  @Operation(summary = "Actualizar departamento")
  public ResponseEntity<DepartamentoDTO> updateDepartment(@RequestBody ActualizaDepartamentoDTO departamentoDTO){
    return new ResponseEntity<>(adminService.updateDepartment(departamentoDTO), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete-department/{id}")
  @Operation(summary = "Eliminar departamento")
  public ResponseEntity<String> deleteDepartment(@PathVariable String id){
    return new ResponseEntity<>(adminService.deleteDepartment(id), HttpStatus.OK);
  }

  @GetMapping("/department-list")
  @Operation(summary = "Obtener lista de departamentos")
  public ResponseEntity<List<DepartamentoDTO>> getAllDepartments(){
    return new ResponseEntity<>(adminService.getDeparmentList(), HttpStatus.OK);
  }

  //Positions
  @PostMapping("/new-puesto")
  @Operation(summary = "Registrar nuevo puesto")
  public ResponseEntity<PuestoDTO> addPuesto(@RequestBody RegistroPuestoDTO registroPuestoDTO){
    return new ResponseEntity<>(adminService.addPuesto(registroPuestoDTO), HttpStatus.CREATED);
  }

  @PutMapping("/update-puesto")
  @Operation(summary = "Actualizar puesto")
  public ResponseEntity<PuestoDTO> updatePuesto(@RequestBody ActualizarPuestoDTO actualizarPuestoDTO){
    return new ResponseEntity<>(adminService.updatePuesto(actualizarPuestoDTO), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete-puesto/{id}")
  @Operation(summary = "Eliminar puesto")
  public ResponseEntity<String> deletePuesto(@PathVariable String id){
    return new ResponseEntity<>(adminService.deletePuesto(id), HttpStatus.OK);
  }

  @GetMapping("/puestos-list")
  @Operation(summary = "Obtener lista de puestos")
  public ResponseEntity<List<PuestoDTO>> getAllPuestos(){
    return new ResponseEntity<>(adminService.getPuestosList(), HttpStatus.OK);
  }

  //States??
  @PostMapping("/new-state")
  @Operation(summary = "Registrar nuevo estado")
  public ResponseEntity<EstadoDTO> addState(@RequestBody RegistroEstadoDTO registroEstadoDTO){
    return new ResponseEntity<>(adminService.addEstado(registroEstadoDTO), HttpStatus.CREATED);
  }

  @PutMapping("/update-state")
  @Operation(summary = "Actualizar estado")
  public ResponseEntity<EstadoDTO> updateState(@RequestBody ActualizarEstadoDTO actualizarEstadoDTO){
    return new ResponseEntity<>(adminService.updateEstado(actualizarEstadoDTO), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete-state/{id}")
  @Operation(summary = "Eliminar estado")
  public ResponseEntity<String> deleteState(@PathVariable String id){
    return new ResponseEntity<>(adminService.deleteEstado(id), HttpStatus.OK);
  }

  @GetMapping("/states-list")
  @Operation(summary = "Obtener lista de estados")
  public ResponseEntity<List<EstadoDTO>> getAllStates(){
    return new ResponseEntity<>(adminService.getEstadosList(), HttpStatus.OK);
  }
}
