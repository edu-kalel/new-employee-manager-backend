package com.ipn.escom.lit.hrmanagerss.hrmanagerss.controller;


import com.ipn.escom.lit.hrmanagerss.hrmanagerss.config.AuthenticationRequest;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.config.AuthenticationResponse;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.EstadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.PuestoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroEmpleadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.config.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<Object> registerNewUser(@RequestBody RegistroEmpleadoDTO registroEmpleadoDTO){
    authenticationService.registerNewUser(registroEmpleadoDTO);
    return new ResponseEntity<>("Registro exitoso, regresa e inicia sesión con tus credenciales", HttpStatus.CREATED);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
    return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
  }

  @GetMapping("/puestos-list")
  @Operation(summary = "Obtener lista de puestos")
  public ResponseEntity<List<PuestoDTO>> getAllPuestos(){
    return new ResponseEntity<>(authenticationService.getPuestosList(), HttpStatus.OK);
  }

  @GetMapping("/states-list")
  @Operation(summary = "Obtener lista de estados")
  public ResponseEntity<List<EstadoDTO>> getAllStates(){
    return new ResponseEntity<>(authenticationService.getEstadosList(), HttpStatus.OK);
  }
}
