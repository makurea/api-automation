package com.makurea.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given; // Может понадобиться для более сложных спецификаций

/**
 * Базовый класс для всех API-клиентов.
 * Отвечает за создание основной спецификации запроса (headers, ContentType).
 */
public class BaseClient {

  /**
   * Создает общую спецификацию запроса для RestAssured.
   * Используется для GET-запросов и как основа для других методов.
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

  /**
   * Создает спецификацию для POST/PUT/PATCH запросов.
   * В данном случае, она совпадает с getBaseSpec(), так как Content-Type уже задан.
   * Однако, метод добавлен, чтобы устранить ошибку 'postBaseSpec' в ReqresClient.
   * @return настроенная RequestSpecification.
   */
  protected RequestSpecification postBaseSpec() {
    // Поскольку getBaseSpec() уже содержит Content-Type: application/json,
    // мы можем просто вернуть его.
    return getBaseSpec();
  }
}