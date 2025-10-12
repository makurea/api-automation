package com.makurea.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * POJO-класс для представления ответа от API на запрос одного пользователя (GET /users/{id}).
 * Содержит объект User в поле data.
 * Аннотация @Data автоматически генерирует геттеры и сеттеры.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserResponse {

  private User data;

}