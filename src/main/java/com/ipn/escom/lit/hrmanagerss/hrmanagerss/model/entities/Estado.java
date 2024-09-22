package com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RH_T_Estado")
public class Estado implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(name = "Nombre", nullable = false, length = 50)
  private String nombre;
  @Column(name = "EstaActivo")
//  @Value("${some.key:true}")
  private boolean estado;

  @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
  @JsonBackReference
  private List<Empleado> empleados = new ArrayList<>();

}
