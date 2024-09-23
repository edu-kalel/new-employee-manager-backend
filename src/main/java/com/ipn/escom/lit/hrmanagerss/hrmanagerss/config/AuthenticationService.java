package com.ipn.escom.lit.hrmanagerss.hrmanagerss.config;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto.RegistroEmpleadoDTO;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.repository.EmpleadoRepo;
import com.ipn.escom.lit.hrmanagerss.hrmanagerss.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final AdminService adminService;
  private final EmpleadoRepo empleadoRepo;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public void registerNewUser(RegistroEmpleadoDTO registroEmpleadoDTO) {
    adminService.registerNewUser(registroEmpleadoDTO);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    var empleado = empleadoRepo.findByEmail(request.getEmail())
      .orElseThrow(() -> new UsernameNotFoundException("Empleado no existe"));
    try{
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getEmail(),
          request.getPassword()
        )
      );
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Contraseña incorrecta");
    }
    var jwtToken = jwtService.generateToken(empleado);
    return AuthenticationResponse.builder()
      .token(jwtToken)
      .rol(empleado.getRol())
      .nombre(empleado.getName())
      .email(empleado.getEmail())
      .build();
  }
}
