package com.ipn.escom.lit.hrmanagerss.hrmanagerss.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiError {
  private final int status;
  private final String message;
}