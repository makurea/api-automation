package com.makurea.basetests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.makurea.utils.ApiConfig.BASE_URL;

/**
 * Базовый класс для всех API-тестов.
 * Содержит настройки, которые должны быть выполнены один раз перед запуском всех тестов.
 */
public class BaseTest {

  private static final Logger log = LogManager.getLogger(BaseTest.class);

  @BeforeAll
  static void setup() {
    // Устанавливаем базовый URL для RestAssured.
    // Хотя в клиенте он используется полностью, это хорошая практика.
    RestAssured.baseURI = BASE_URL;
    log.info("Установлен базовый URL: {}", BASE_URL);

    // Включаем логирование запросов и ответов только в случае ошибки валидации (assertion fail).
    // Это делает вывод консоли чище.
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    // Настройка таймаутов для HTTP-соединения.
    // Это позволяет тестам не висеть бесконечно в случае проблем с сетью/сервером.
    RestAssured.config = RestAssured.config()
        .httpClient(RestAssured.config().getHttpClientConfig()
            .setParam("http.connection.timeout", 5000) // Таймаут на установление соединения (5 сек)
            .setParam("http.socket.timeout", 5000));   // Таймаут на ожидание данных (5 сек)
  }
}