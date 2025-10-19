package com.makurea.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * POJO-класс для представления ответа с ошибкой от API на запрос регистрации (400 Bad Request).
 * Ожидаемый ответ: { "error": "string" }
 */
@Data // Lombok: генерирует геттеры, сеттеры, equals, hashCode и toString
@JsonIgnoreProperties(ignoreUnknown = true) // Игнорировать поля, которых нет в классе
public class RegisterErrorResponse {

  /**
   * Сообщение об ошибке регистрации.
   */
  private String error;
}