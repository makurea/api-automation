package com.makurea.tests;

import com.makurea.models.RegisterErrorResponse;
import com.makurea.models.RegisterRequest;
import com.makurea.models.RegisterSuccessResponse;
import com.makurea.client.ReqresClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API Testing")
@Feature("User Management")
@Story("Registration")
@DisplayName("Проверка эндпоинта POST /api/register")
public class RegisterTest {

  // Валидные учетные данные для успешной регистрации на reqres.in
  private static final String VALID_EMAIL = "eve.holt@reqres.in";
  private static final String VALID_PASSWORD = "pistol";

  // Инициализация сервиса, который содержит метод postRegister()
  private static ReqresClient reqresClient;

  @BeforeAll
  static void setUp() {
    // Здесь происходит инициализация клиента и сервиса
    reqresClient = new ReqresClient();
  }

  /**
   * Тест: Успешная регистрация пользователя с предопределенными валидными данными.
   * Ожидаемый результат: HTTP Status 200, наличие ID и Token.
   */
  @Test
  @DisplayName("Успешная регистрация: должен вернуться статус 200 с ID и Token")
  void registration_withValidCredentials_ShouldReturn200AndToken() {
    // 1. Подготовка тела запроса (Payload)
    RegisterRequest credentials = new RegisterRequest()
        .setEmail(VALID_EMAIL)
        .setPassword(VALID_PASSWORD);

    // 2. Выполнение POST-запроса через сервисный метод
    RegisterSuccessResponse response = reqresClient.postRegisterUser(credentials);

    // 3. Проверки (Assertions)

    // Проверка 1: Token должен присутствовать и быть не пустым
    assertThat(response.getToken())
        .as("Проверка наличия и непустого значения токена")
        .isNotNull()
        .isNotEmpty();

    // Проверка 2: ID должен присутствовать и быть положительным числом
    assertThat(response.getId())
        .as("Проверка наличия и положительного значения ID")
        .isNotNull()
        .isGreaterThan(0);
  }

  /**
   * Тест: Попытка регистрации с невалидными данными.
   * Ожидаемый результат: HTTP Status 400 и сообщение об ошибке.
   */
  @Test
  @DisplayName("Невалидный email: должна вернуться ошибка 400 Bad Request")
  void registration_withInvalidEmail_ShouldReturn400Error() {
    // 1. Ваши исходные данные
    RegisterRequest invalidCredentials = new RegisterRequest()
        // Используем ваши данные, которые не соответствуют валидному email
        .setEmail("готем сити")
        .setPassword("готем123");

    // 2. Выполнение POST-запроса через клиентский метод, ожидающий 400
    RegisterErrorResponse errorResponse = reqresClient.postRegisterUserWithError(invalidCredentials);

    // 3. Проверки (Assertions)

    // Проверка 1: Объект ошибки не должен быть пустым
    assertThat(errorResponse)
        .as("Проверка, что ответ с ошибкой десериализован")
        .isNotNull();

    // Проверка 2: Поле 'error' должно присутствовать и быть не пустым
    assertThat(errorResponse.getError())
        .as("Проверка наличия сообщения об ошибке")
        .isNotNull()
        .isNotEmpty();

    // Проверка 3: Сообщение об ошибке должно содержать ожидаемый текст
    // Reqres возвращает эту ошибку, когда email или пароль не совпадают с предопределенными
    assertThat(errorResponse.getError())
        .as("Проверка содержания ошибки")
        .contains("Note: Only defined users succeed registration");
  }

}