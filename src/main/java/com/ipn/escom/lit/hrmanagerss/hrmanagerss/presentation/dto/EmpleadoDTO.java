package com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
  private String id;
  private String fullName;
  private String email;
  private String curp;
  private String puesto;
  private String departamento;
  private String estado;
  private String rol;
}