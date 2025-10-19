package com.makurea.services;

import com.makurea.client.ReqresClient;
import com.makurea.models.responses.RegisterErrorResponse;
import com.makurea.models.requests.RegisterRequest;
import com.makurea.models.responses.RegisterSuccessResponse;
import com.makurea.models.responses.SingleUserSuccessResponse;
import com.makurea.models.requests.LoginRequest;
import com.makurea.models.responses.LoginSuccessResponse;
import com.makurea.models.responses.UserSuccessResponse;
// Убраны: com.fasterxml.jackson.databind.ObjectMapper, io.restassured.response.Response
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс-сервис, который инкапсулирует бизнес-логику работы с сущностью "Пользователь".
 * Он использует ReqresClient для выполнения запросов и возвращает готовые POJO-объекты.
 */
public class UserService {
  private final ReqresClient client = new ReqresClient();
  private static final Logger log = LogManager.getLogger(UserService.class);


  /**
   * Получает список пользователей.
   * @return Объект-контейнер UserResponse.
   */
  public UserSuccessResponse getUsers() {
    log.info("Получение списка пользователей.");
    // Делегируем запрос и десериализацию клиенту.
    return client.getUsers();
  }

  /**
   * Получает случайного пользователя по ID.
   * @return Объект-контейнер SingleUserResponse.
   */
  public SingleUserSuccessResponse getRandomUser() {
    // В API reqres.in, пользователи с 1 по 12 существуют.
    // Random().nextInt(12) + 1; будет более корректным, но оставим 4 для примера.
    int randomId = new Random().nextInt(4) + 1;
    log.info("Получение пользователя с ID: {}", randomId);
    // Делегируем запрос и десериализацию клиенту.
    return client.getUserById(randomId);
  }

  /**
   * Выполняет POST-запрос на регистрацию.
   *
   * @param credentials Объект с учетными данными (email и password).
   * @return Объект-контейнер RegisterSuccessResponse, содержащий id и token.
   */
  public RegisterSuccessResponse postRegister(RegisterRequest credentials) {
    // Исправлено логирование: теперь оно отражает действие (регистрацию)
    log.info("Регистрация пользователя с email: {}", credentials.getEmail());
    // Делегируем запрос и десериализацию клиенту, передавая необходимые данные.
    return client.postRegisterUser(credentials);
  }

  /**
   * Выполняет POST-запрос на регистрацию, ожидая ошибку 400.
   *
   * @param credentials Объект с учетными данными (email и password).
   * @return Объект-контейнер RegisterErrorResponse, содержащий сообщение об ошибке.
   */
  public RegisterErrorResponse postRegisterAndExpectError(RegisterRequest credentials) {
    log.info("Попытка регистрации с невалидными данными, email: {}", credentials.getEmail());
    // Делегируем запрос клиенту, который ожидает 400.
    return client.postRegisterUserWithError(credentials);
  }

  /**
   * Выполняет POST-запрос на логин.
   *
   * @param credentials Объект с учетными данными (username, email, password).
   * @return Объект-контейнер LoginSuccessResponse, содержащий token.
   */
  public LoginSuccessResponse postLogin(LoginRequest credentials) {
    log.info("Попытка логина пользователя с email: {}", credentials.getEmail());
    return client.postLogin(credentials);
  }

  /**
   * Выполняет POST-запрос на логин, ожидая ошибку 400.
   *
   * @param credentials Объект с учетными данными.
   * @return Объект-контейнер RegisterErrorResponse, содержащий сообщение об ошибке.
   */
  public RegisterErrorResponse postLoginAndExpectError(LoginRequest credentials) {
    log.info("Попытка логина с невалидными данными, email: {}", credentials.getEmail());
    return client.postLoginAndExpectError(credentials);
  }


}