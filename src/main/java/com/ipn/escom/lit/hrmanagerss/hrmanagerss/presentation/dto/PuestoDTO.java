package com.ipn.escom.lit.hrmanagerss.hrmanagerss.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PuestoDTO {
  private String id;
  private String nombre;
  private String estado;
}
