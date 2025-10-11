package com.makurea.basetests;

import static com.makurea.utils.ApiConfig.BASE_URL;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = BASE_URL;

    final Logger log = LogManager.getLogger(BaseTest.class);
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    RestAssured.config = RestAssured.config()
        .httpClient(RestAssured.config().getHttpClientConfig()
            .setParam("http.connection.timeout", 5000)
            .setParam("http.socket.timeout", 5000));
  }

}

