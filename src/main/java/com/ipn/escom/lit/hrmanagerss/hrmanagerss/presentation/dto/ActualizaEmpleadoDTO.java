package com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizaEmpleadoDTO {
  private String id;
  private String name;
  private String firstSurname;
  private String secondSurname;
  private String email;
  private String curp;
  private String puesto_id;
  private String estado_id;
  private String password;
  private Rol rol;
}
