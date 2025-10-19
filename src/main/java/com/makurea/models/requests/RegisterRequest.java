package com.makurea.models.requests;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * POJO-класс для тела запроса регистрации (POST /api/register).
 */
@Data
@Accessors(chain = true) // Позволяет строить объект цепочкой методов: new RegisterRequest().setEmail("...").setPassword("...")
public class RegisterRequest {
  private String email;
  private String password;
}