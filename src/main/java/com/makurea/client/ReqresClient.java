package com.makurea.client;

import com.makurea.models.RegisterErrorResponse;
import com.makurea.models.RegisterRequest;
import com.makurea.models.RegisterSuccessResponse;
import com.makurea.models.SingleUserResponse;
import com.makurea.models.UserResponse; // Добавлен для прямого возврата объекта
import com.makurea.utils.ApiConfig;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.makurea.utils.ApiConfig.BASE_USERS_URL;
import static com.makurea.utils.ApiConfig.REGISTER_URL;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

/**
 * Клиент для взаимодействия с API reqres.in.
 * Содержит методы, специфичные для работы с сущностью "Пользователи".
 * Методы возвращают либо "сырой" ответ (Response), либо десериализованный объект.
 */
public class ReqresClient extends BaseClient {

  private static final Logger log = LogManager.getLogger(ReqresClient.class);

  /**
   * Получает список пользователей и десериализует его в объект UserResponse.
   * Использует RequestHelper.doGet для прямой десериализации.
   */
  public UserResponse getUsers() {
    log.info("Отправка запроса GET {}", BASE_USERS_URL);
    return RequestHelper.doGet(
        getBaseSpec(), SC_OK, BASE_USERS_URL, UserResponse.class
    );
  }

  /**
   * Получает одного пользователя по ID и десериализует его в объект SingleUserResponse.
   */
  public SingleUserResponse getUserById(int id) {
    String fullUrl = BASE_USERS_URL + "/" + id;
    log.info("Отправка запроса GET {}", fullUrl);
    return RequestHelper.doGet(
        getBaseSpec(), SC_OK, fullUrl, SingleUserResponse.class
    );
  }

  /**
   * Выполняет POST-запрос на регистрацию с заданными учетными данными.
   * @param credentials Объект RegisterRequest с email и password.
   * @return Объект RegisterSuccessResponse с id и token.
   */
  public RegisterSuccessResponse postRegisterUser(RegisterRequest credentials) {
    String fullUrl = REGISTER_URL;
    log.info("Отправка запроса POST {}", fullUrl);

    // Используем RequestHelper.doPost, передавая тело запроса
    return RequestHelper.doPost(
        postBaseSpec(),                   // Спецификация для POST-запроса (например, Content-Type: application/json)
        credentials,                      // Тело запроса (email и password)
        SC_OK,                            // Ожидаемый статус-код 200
        fullUrl,                          // URL
        RegisterSuccessResponse.class     // Класс для десериализации
    );
  }

  /**
   * Выполняет POST-запрос на регистрацию, ожидая ошибку 400.
   * * @param credentials Объект RegisterRequest.
   * @return Объект RegisterErrorResponse с сообщением об ошибке.
   */
  public RegisterErrorResponse postRegisterUserWithError(RegisterRequest credentials) {
    String fullUrl = REGISTER_URL;
    log.info("Отправка запроса POST {} с невалидными данными: {}", fullUrl, credentials.getEmail());

    return RequestHelper.doPost(
        postBaseSpec(),                   // Спецификация для POST
        credentials,                      // Тело запроса
        SC_BAD_REQUEST,                   // Ожидаемый статус-код 400
        fullUrl,                          // URL
        RegisterErrorResponse.class       // Класс для десериализации ответа с ошибкой
    );
  }
}
