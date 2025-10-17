package com.makurea.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * POJO-класс для представления успешного ответа от API на запрос логина (200 OK).
 * Ожидаемый ответ: { "token": "string" }
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginSuccessResponse {
  private String token;
}