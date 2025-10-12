package com.makurea.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Базовый класс для всех API-клиентов.
 * Отвечает за создание основной спецификации запроса (headers, ContentType).
 */
public class BaseClient {

  /**
   * Создает базовую спецификацию запроса для RestAssured.
   * @return настроенная RequestSpecification.
   */
  protected RequestSpecification getBaseSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .addHeader("Accept", "application/json")
        // Заголовок x-api-key для reqres.in обычно не нужен, но оставлен для примера.
        .addHeader("x-api-key", "reqres-free-v1")
        .build();
  }
}