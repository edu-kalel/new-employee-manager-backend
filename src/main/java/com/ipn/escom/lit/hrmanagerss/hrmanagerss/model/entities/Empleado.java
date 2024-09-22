package com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.entities;

import com.ipn.escom.lit.hrmanagerss.hrmanagerss.model.enums.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RH_T_RecursoHumano")
public class Empleado implements Serializable, UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false)
  private String id;
  @Column(name = "Nombre", nullable = false, length = 50)
  private String name;
  @Column(name="PrimerApellido", length = 50)
  private String firstSurname;
  @Column(name="SegundoApellido", length = 50)
  private String secondSurname;
  @Column(name = "Email", nullable = false, length = 100)
  private String email;
  @Column(name = "CURP", length = 50)
  private String curp;
  @Column(length = 60, nullable = false)
  private String password;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Rol rol;

  @ManyToOne
  @JoinColumn(name = "Puesto_id", referencedColumnName = "id")
  private Puesto puesto;

  @ManyToOne
  @JoinColumn(name = "Estado_id", referencedColumnName = "id")
  private Estado estado;

  @ManyToOne
  @JoinColumn(name = "Departamento_id", referencedColumnName = "id")
  private Departamento departamento;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(rol.name()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
