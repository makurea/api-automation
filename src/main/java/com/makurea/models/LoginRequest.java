package com.makurea.models;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * POJO-класс для тела запроса логина (POST /api/login).
 * Требует: username, email, password.
 */
@Data
@Accessors(chain = true)
public class LoginRequest {
  private String username;
  private String email;
  private String password;
}