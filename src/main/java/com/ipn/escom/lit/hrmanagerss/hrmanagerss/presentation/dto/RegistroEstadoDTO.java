package com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroEstadoDTO {
  private String nombre;
  private boolean estado;
}
