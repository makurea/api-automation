package com.makurea.client;

import static com.makurea.utils.ApiConfig.BASE_URL;

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
        .get();
    log.debug("Статус ответа: {}", response.getStatusCode());
    return response;
  }
}

