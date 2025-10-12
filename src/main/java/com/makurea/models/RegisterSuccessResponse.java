package com.makurea.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * POJO-класс для представления успешного ответа от API на запрос регистрации (POST /api/register).
 * Ожидаемый ответ (200 OK): { "id": 4, "token": "QpwL5tke4Pnpja7X4" }
 */
@Data // Lombok: генерирует геттеры, сеттеры, equals, hashCode и toString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterSuccessResponse {

  /**
   * ID зарегистрированного пользователя.
   */
  private Integer id;

  /**
   * Токен аутентификации.
   */
  private String token;

}