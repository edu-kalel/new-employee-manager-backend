package com.ipn.escom.lit.hrmanagerss.hrmanagerss.service;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Departamento;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Empleado;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Estado;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities.Puesto;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.Mapper;
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
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository.DepartamentoRepo;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository.EmpleadoRepo;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository.EstadoRepo;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository.PuestoRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final EmpleadoRepo empleadoRepo;
  private final PasswordEncoder passwordEncoder;
  private final DepartamentoRepo departamentoRepo;
  private final PuestoRepo puestoRepo;
  private final EstadoRepo estadoRepo;
  private final Mapper mapper;

  public EmpleadoDTO registerNewUser(RegistroEmpleadoDTO registroEmpleadoDTO) {
    if (empleadoRepo.existsByCurp(registroEmpleadoDTO.getCurp())) {
      throw new EntityExistsException("Curp ya registrado");
    }
    if (empleadoRepo.existsByEmail(registroEmpleadoDTO.getEmail())) {
      throw new EntityExistsException("Email ya registrado");
    }
    var puesto = puestoRepo.findById(registroEmpleadoDTO.getPuesto_id())
      .orElseThrow(() -> new EntityNotFoundException("Puesto no existe"));
    var estado = estadoRepo.findById(registroEmpleadoDTO.getEstado_id())
      .orElseThrow(() -> new EntityNotFoundException("Estado no válido"));
    var empleado = Empleado.builder()
      .name(registroEmpleadoDTO.getName())
      .firstSurname(registroEmpleadoDTO.getFirstSurname())
      .secondSurname(registroEmpleadoDTO.getSecondSurname())
      .email(registroEmpleadoDTO.getEmail())
      .curp(registroEmpleadoDTO.getCurp())
      .puesto(puesto)
      .estado(estado)
      .departamento(puesto.getDepartamento())
      .password(passwordEncoder.encode(registroEmpleadoDTO.getPassword()))
      .rol(registroEmpleadoDTO.getRol())
      .build();
    return mapper.mapToEmpleadoDTO(empleadoRepo.save(empleado));
  }

  public EmpleadoDTO updateUser(ActualizaEmpleadoDTO actualizaEmpleadoDTO) {
    var empleado = empleadoRepo.findById(actualizaEmpleadoDTO.getId())
      .orElseThrow(() -> new EntityNotFoundException("Empleado no existe"));
    var puesto = puestoRepo.findById(actualizaEmpleadoDTO.getPuesto_id())
      .orElseThrow(() -> new EntityNotFoundException("Puesto no existe"));
    var estado = estadoRepo.findById(actualizaEmpleadoDTO.getEstado_id())
      .orElseThrow(() -> new EntityNotFoundException("Estado no válido"));

    if (!actualizaEmpleadoDTO.getPassword().isBlank())
      empleado.setPassword(passwordEncoder.encode(actualizaEmpleadoDTO.getPassword()));

    empleado.setEmail(actualizaEmpleadoDTO.getEmail());
    empleado.setName(actualizaEmpleadoDTO.getName());
    empleado.setCurp(actualizaEmpleadoDTO.getCurp());
    empleado.setFirstSurname(actualizaEmpleadoDTO.getFirstSurname());
    empleado.setSecondSurname(actualizaEmpleadoDTO.getSecondSurname());
    empleado.setPuesto(puesto);
    empleado.setEstado(estado);
    empleado.setDepartamento(puesto.getDepartamento());
    empleado.setRol(actualizaEmpleadoDTO.getRol());
    return mapper.mapToEmpleadoDTO(empleadoRepo.save(empleado));
  }

  public String deleteUser(String id) {
    var empleado = empleadoRepo.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Empleado no existe"));
    empleadoRepo.delete(empleado);
    return "Empleado eliminado";
  }

  public EmpleadoDTO getUser(String id) {
    return mapper.mapToEmpleadoDTO(empleadoRepo.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Empleado no existe")));
  }

  public List<EmpleadoDTO> getAllUsers() {
    List<Empleado> empleados = empleadoRepo.findAll();
    List<EmpleadoDTO> empleadoDTOS = new ArrayList<>();
    for (Empleado empleado : empleados) {
      empleadoDTOS.add(mapper.mapToEmpleadoDTO(empleado));
    }
    return empleadoDTOS;
  }

  public DepartamentoDTO addDepartment(RegistroDepartamentoDTO registroDepartamentoDTO) {
    if (departamentoRepo.existsByNombre(registroDepartamentoDTO.getNombre())) {
      throw new EntityExistsException("Departamento ya existe");
    }
    var departamento = Departamento.builder()
      .nombre(registroDepartamentoDTO.getNombre())
      .estado(registroDepartamentoDTO.isEstado())
      .build();
    return mapper.mapToDepartamentoDTO(departamentoRepo.save(departamento));
  }

  public DepartamentoDTO updateDepartment(ActualizaDepartamentoDTO actualizaDepartamentoDTO) {
    var departamento = departamentoRepo.findById(actualizaDepartamentoDTO.getId())
      .orElseThrow(() -> new EntityNotFoundException("Departamento no existe"));
    departamento.setNombre(actualizaDepartamentoDTO.getNombre());
    departamento.setEstado(actualizaDepartamentoDTO.isEstado());
    return mapper.mapToDepartamentoDTO(departamentoRepo.save(departamento));
  }

  public String deleteDepartment(String id) {
    var departamento = departamentoRepo.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Departamento no existe"));
    if (!departamento.getEmpleados().isEmpty()) {
      throw new EntityExistsException("El departamento aún tiene empleados asociados, editalos o eliminalos antes de eliminar este departamento.");
    }
    else if (!departamento.getPuestos().isEmpty()) {
      throw new EntityExistsException("El departamento aún tiene puestos asociados, editalos o eliminalos antes de eliminar este departamento.");
    }
    else{
      departamentoRepo.delete(departamento);
      return "Departamento eliminado";
    }
  }

  public List<DepartamentoDTO> getDeparmentList() {
    List<Departamento> departamentos = departamentoRepo.findAll();
    List<DepartamentoDTO> departamentoDTOS = new ArrayList<>();
    for (Departamento departamento : departamentos) {
      departamentoDTOS.add(mapper.mapToDepartamentoDTO(departamento));
    }
    return departamentoDTOS;
  }

  public PuestoDTO addPuesto(RegistroPuestoDTO registroPuestoDTO) {
    if (puestoRepo.existsByNombre(registroPuestoDTO.getNombre())) {
      throw new EntityExistsException("Puesto ya existe");
    }
    var departamento = departamentoRepo.findById(registroPuestoDTO.getDepartamento_id())
      .orElseThrow(() -> new EntityNotFoundException("Departamento no existe"));
    var puesto = Puesto.builder()
      .nombre(registroPuestoDTO.getNombre())
      .estado(registroPuestoDTO.isEstado())
      .departamento(departamento)
      .build();
    return mapper.mapToPuestoDTO(puestoRepo.save(puesto));
  }

  public PuestoDTO updatePuesto(ActualizarPuestoDTO actualizarPuestoDTO) {
    var puesto = puestoRepo.findById(actualizarPuestoDTO.getId())
      .orElseThrow(() -> new EntityNotFoundException("Puesto no existe"));
    var departamento = departamentoRepo.findById(actualizarPuestoDTO.getDepartamento_id())
      .orElseThrow(() -> new EntityNotFoundException("Departamento no existe"));

    if (!departamento.equals(puesto.getDepartamento())){
      List<Empleado> empleados = empleadoRepo.findByPuesto_Id(actualizarPuestoDTO.getId());
      if (!empleados.isEmpty()) {
        for (Empleado empleado : empleados) {
          empleado.setDepartamento(departamento);
          empleadoRepo.save(empleado);
        }
      }
      puesto.setDepartamento(departamento);
    }

    puesto.setNombre(actualizarPuestoDTO.getNombre());
    puesto.setEstado(actualizarPuestoDTO.isEstado());

    return mapper.mapToPuestoDTO(puestoRepo.save(puesto));
  }

  public String deletePuesto(String id) {
    var puesto = puestoRepo.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Puesto no existe"));
    if (!puesto.getEmpleados().isEmpty()) {
      throw new EntityExistsException("El puesto aún tiene empleados asociados, editalos o eliminalos antes de eliminar este departamento.");
    }
    else{
      puestoRepo.delete(puesto);
      return "Puesto eliminado";
    }
  }

  public List<PuestoDTO> getPuestosList() {
    List<Puesto> puestos = puestoRepo.findAll();
    List<PuestoDTO> puestoDTOS = new ArrayList<>();
    for (Puesto puesto : puestos) {
      puestoDTOS.add(mapper.mapToPuestoDTO(puesto));
    }
    return puestoDTOS;
  }

  public EstadoDTO addEstado(RegistroEstadoDTO registroEstadoDTO) {
    if (estadoRepo.existsByNombre(registroEstadoDTO.getNombre())) {
      throw new EntityExistsException("Estado ya existe");
    }
    var estado = Estado.builder()
      .nombre(registroEstadoDTO.getNombre())
      .estado(registroEstadoDTO.isEstado())
      .build();
    return mapper.mapToEstadoDTO(estadoRepo.save(estado));
  }

  public EstadoDTO updateEstado(ActualizarEstadoDTO actualizarEstadoDTO) {
    var estado = estadoRepo.findById(actualizarEstadoDTO.getId())
      .orElseThrow(() -> new EntityNotFoundException("Estado no existe"));
    estado.setNombre(actualizarEstadoDTO.getNombre());
    estado.setEstado(actualizarEstadoDTO.isEstado());
    return mapper.mapToEstadoDTO(estadoRepo.save(estado));
  }


  public String deleteEstado(String id) {
    var estado = estadoRepo.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Estado no existe"));
    if (!estado.getEmpleados().isEmpty()) {
      throw new EntityExistsException("El estado aún tiene empleados asociados, editalos o eliminalos antes de eliminar este departamento.");
    }
    else{
      estadoRepo.delete(estado);
      return "Estado eliminado";
    }
  }

  public List<EstadoDTO> getEstadosList() {
    List<Estado> estados = estadoRepo.findAll();
    List<EstadoDTO> estadoDTOS = new ArrayList<>();
    for (Estado estado : estados) {
      estadoDTOS.add(mapper.mapToEstadoDTO(estado));
    }
    return estadoDTOS;
  }
}
