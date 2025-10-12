package com.makurea.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Вспомогательный класс для выполнения HTTP-запросов (GET, POST, PUT, DELETE).
 * Содержит обобщенные методы, которые скрывают низкоуровневую логику RestAssured.
 */
public class RequestHelper {

  /**
   * Выполняет GET-запрос и преобразует ответ в List<T>, проверяя при этом статус-код.
   * @param spec Спецификация запроса (заголовки, параметры и т.д.).
   * @param statusCode Ожидаемый HTTP-статус-код.
   * @param url Полный URL для запроса.
   * @param clazz Массив класса, в который нужно десериализовать ответ (например, User[].class).
   * @return Список объектов типа T.
   */
  public static <T> List<T> doGetList(RequestSpecification spec, int statusCode, String url, Class<T[]> clazz) {
    return Arrays.asList(
        given()
            .spec(spec)
            .when()
            .log().all() // Логирование запроса
            .get(url)
            .then()
            .statusCode(statusCode)
            .log().all() // Логирование ответа
            .extract().body().as(clazz)
    );
  }

  /**
   * Выполняет GET-запрос и преобразует ответ в объект типа T, проверяя при этом статус-код.
   * Этот метод будет использоваться в UserService.
   * @param spec Спецификация запроса.
   * @param statusCode Ожидаемый HTTP-статус-код.
   * @param url Полный URL для запроса.
   * @param clazz Класс, в который нужно десериализовать ответ (например, UserResponse.class).
   * @return Объект типа T.
   */
  public static <T> T doGet(RequestSpecification spec, int statusCode, String url, Class<T> clazz) {
    return given()
        .spec(spec)
        .when()
        .log().all()
        .get(url)
        .then()
        .statusCode(statusCode)
        .log().all()
        .extract().body().as(clazz);
  }

  /**
   * Выполняет GET-запрос и возвращает необработанный объект Response.
   * Используется для тестов, где нужно проверить заголовки или статус-код без десериализации.
   * @param spec Спецификация запроса.
   * @param url Полный URL для запроса.
   * @return Объект Response.
   */
  public static Response doGetRaw(RequestSpecification spec, String url) {
    return given()
        .spec(spec)
        .when()
        .log().all()
        .get(url)
        .then()
        .log().all()
        .extract().response();
  }

  // --- Метод для POST-запросов (добавлен для соответствия ReqresClient) ---

  /**
   * Выполняет POST-запрос с телом и преобразует ответ в объект типа T, проверяя при этом статус-код.
   * @param spec Спецификация запроса.
   * @param requestBody Объект, который будет отправлен в теле запроса (автоматически сериализуется в JSON).
   * @param statusCode Ожидаемый HTTP-статус-код.
   * @param url Полный URL для запроса.
   * @param clazz Класс, в который нужно десериализовать ответ.
   * @return Объект типа T.
   */
  public static <T> T doPost(RequestSpecification spec, Object requestBody, int statusCode, String url, Class<T> clazz) {
    return given()
        .spec(spec)
        .body(requestBody)
        .when()
        .log().all()
        .post(url)
        .then()
        .statusCode(statusCode)
        .log().all()
        .extract().body().as(clazz);
  }

}