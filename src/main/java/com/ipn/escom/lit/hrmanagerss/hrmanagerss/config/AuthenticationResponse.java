package com.ipn.escom.lit.hrmanagerss.hrmanagerss.config;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private String token;
  private Rol rol;
  private String nombre;
  private String email;
}
