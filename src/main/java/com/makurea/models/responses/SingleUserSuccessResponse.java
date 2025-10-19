package com.makurea.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.makurea.models.requests.UserRequest;
import lombok.Data;

/**
 * POJO-класс для представления ответа от API на запрос одного пользователя (GET /users/{id}).
 * Содержит объект User в поле data.
 * Аннотация @Data автоматически генерирует геттеры и сеттеры.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserSuccessResponse {

  private UserRequest data;

}