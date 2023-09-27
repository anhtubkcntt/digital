package com.digital.shop.exception;

import lombok.Data;

@Data
public class ApiError {

  String error;
  String message;
  String status;
}
