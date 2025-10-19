package com.makurea.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO-класс для представления данных о пользователе.
 * Аннотация @Data автоматически генерирует геттеры, сеттеры,
 * конструктор со всеми аргументами, toString, equals и hashCode.
 */
@Data // Аннотация Lombok
@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем поля, которых нет в классе
public class UserRequest {

  private int id;
  private String email;

  @JsonProperty("first_name")
  private String firstName; // Lombok сам сгенерирует getFirstName() и setFirstName()

  @JsonProperty("last_name")
  private String lastName; // Lombok сам сгенерирует getLastName() и setLastName()

  private String avatar;

  // УДАЛЕНО: Весь блок ручных геттеров и сеттеров
}