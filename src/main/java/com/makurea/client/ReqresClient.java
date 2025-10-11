package com.makurea.client;

import static com.makurea.utils.ApiConfig.BASE_URL;

import com.makurea.models.SingleUserResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReqresClient {

  private static final Logger log = LogManager.getLogger(ReqresClient.class);

  public Response getUsers() {
    log.info("Выполняем GET запрос к {}", BASE_URL + "/users");

    Response response = RestAssured
        .given()
        .baseUri(BASE_URL)
        .basePath("/users")
//        .header("User-Agent", "Mozilla/5.0 (compatible; makurea-api-tests/1.0)")
        .header("Accept", "application/json")
        .header("x-api-key","reqres-free-v1")
//        .header("Content-Type", "application/json; charset=UTF-8")
//        .header("Cache-Control", "no-cache")
        .when()
        .get();

    log.debug("Статус ответа: {}", response.getStatusCode());
    return response;
  }

  public Response getUsersId() {
    int randomId = (int) (Math.random() * 6) + 1;
    String endpoint = "/users/" + randomId;

    log.info("Выполняем GET запрос к {}", BASE_URL + endpoint);

    Response response = RestAssured
        .given()
        .relaxedHTTPSValidation()
        .baseUri(BASE_URL)
        .basePath(endpoint)
        .header("Accept", "application/json")
        .header("x-api-key","reqres-free-v1")
        .when()
        .get();

    log.debug("Статус ответа: {}", response.getStatusCode());
    log.debug("Тело ответа: {}", response.getBody().asString());

    return response;
  }


}
