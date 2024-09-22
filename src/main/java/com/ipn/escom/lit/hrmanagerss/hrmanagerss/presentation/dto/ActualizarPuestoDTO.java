package com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarPuestoDTO {
  private String id;
  private String nombre;
  private boolean estado;
  private String  departamento_id;
}
