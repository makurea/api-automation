package com.makurea.client;

import com.makurea.models.responses.RegisterErrorResponse;
import com.makurea.models.requests.RegisterRequest;
import com.makurea.models.responses.RegisterSuccessResponse;
import com.makurea.models.responses.SingleUserSuccessResponse;
import com.makurea.models.requests.LoginRequest;
import com.makurea.models.responses.LoginSuccessResponse;
import com.makurea.models.responses.UserSuccessResponse; // Добавлен для прямого возврата объекта
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
  public UserSuccessResponse getUsers() {
    log.info("Отправка запроса GET {}", BASE_USERS_URL);
    return RequestHelper.doGet(
        getBaseSpec(), SC_OK, BASE_USERS_URL, UserSuccessResponse.class
    );
  }

  /**
   * Получает одного пользователя по ID и десериализует его в объект SingleUserResponse.
   */
  public SingleUserSuccessResponse getUserById(int id) {
    String fullUrl = BASE_USERS_URL + "/" + id;
    log.info("Отправка запроса GET {}", fullUrl);
    return RequestHelper.doGet(
        getBaseSpec(), SC_OK, fullUrl, SingleUserSuccessResponse.class
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

  /**
   * Выполняет POST-запрос на логин с заданными учетными данными (ожидается 200).
   *
   * @param credentials Объект LoginRequest с username, email и password.
   * @return Объект LoginSuccessResponse с token.
   */
  public LoginSuccessResponse postLogin(LoginRequest credentials) {
    // Внимание: Я использую существующую константу REGISTER_URL,
    // но в реальном проекте вам нужно создать новую, например: ApiConfig.LOGIN_URL
    String fullUrl = REGISTER_URL.replace("register", "login"); // Адаптируем URL для примера
    log.info("Отправка запроса POST {} для логина пользователя: {}", fullUrl, credentials.getEmail());

    return RequestHelper.doPost(
        postBaseSpec(),
        credentials,
        SC_OK,
        fullUrl,
        LoginSuccessResponse.class
    );
  }

  /**
   * Выполняет POST-запрос на логин, ожидая ошибку 400.
   *
   * @param credentials Объект LoginRequest с некорректными данными.
   * @return Объект RegisterErrorResponse с сообщением об ошибке.
   */
  public RegisterErrorResponse postLoginAndExpectError(LoginRequest credentials) {
    String fullUrl = REGISTER_URL.replace("register", "login");
    log.info("Попытка логина с невалидными данными, email: {}", credentials.getEmail());

    return RequestHelper.doPost(
        postBaseSpec(),
        credentials,
        SC_BAD_REQUEST, // Ожидаемый статус-код 400
        fullUrl,
        RegisterErrorResponse.class // Используем существующий DTO для ответа с ошибкой
    );
  }

}
