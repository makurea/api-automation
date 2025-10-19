package com.makurea.tests;


import static com.makurea.utils.TestData.ERROR_MESSAGE_LOGIN_NOT_FOUND;
import static com.makurea.utils.TestData.VALID_LOGIN_EMAIL;
import static com.makurea.utils.TestData.VALID_LOGIN_PASSWORD;
import static com.makurea.utils.TestData.VALID_LOGIN_USERNAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.makurea.basetests.BaseTest;
import com.makurea.models.requests.LoginRequest;
import com.makurea.models.responses.RegisterErrorResponse;
import com.makurea.models.responses.LoginSuccessResponse;
import com.makurea.services.UserService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Класс, содержащий автотесты для API-запросов, связанных с логином.
 */
@Epic("API Testing")
@Feature("Authentication")
@Story("Login")
@DisplayName("Проверка эндпоинта POST /login")
public class LoginTests extends BaseTest {

  private final UserService userService = new UserService();

  @Test
  @DisplayName("POST /login - Успешный логин с валидными данными")
  @Link(name = "Reqres API Login", url = "https://reqres.in/api/login")
  @Story("Успешное получение токена")
  void postLogin_ShouldReturnToken() {
    // 1. Подготовка: Создаем объект запроса с валидными данными
    LoginRequest credentials = new LoginRequest()
        //.setUsername(VALID_LOGIN_USERNAME)
        .setEmail(VALID_LOGIN_EMAIL)
        .setPassword(VALID_LOGIN_PASSWORD);

    // 2. Действие: Выполняем логин (ожидаем 400 со стороны Reqres.in)
    LoginSuccessResponse response = userService.postLogin(credentials);

    // 3. Проверки (Assertions): Проверяем ТОЛЬКО успешный ответ
    assertThat(response)
        .as("Объект успешного ответа (с токеном) не должен быть null")
        .isNotNull();

    assertThat(response.getToken())
        .as("Токен не должен быть пустым и должен быть строкой")
        .isNotBlank()
        .isInstanceOf(String.class);
  }

  @ParameterizedTest(name = "Логин с невалидным: {0}")
  @CsvSource({
      // Для Reqres.in эти случаи возвращают "user not found"
      "Неверный email, wrong@email.com, cityslicka",
      "Неверный пароль, eve.holt@reqres.in, wrongpassword",
      "Отсутствует email, , cityslicka"
  })
  @DisplayName("POST /login - Логин с невалидными данными должен вернуть ошибку 400")
  @Link(name = "Reqres API Login", url = "https://reqres.in/api/login")
  @Story("Обработка ошибок при логине")
  void postLogin_WithInvalidData_ShouldReturnError(String testCase, String email, String password) {
    // 1. Подготовка: Создаем объект запроса с невалидными данными
    LoginRequest credentials = new LoginRequest()
        .setUsername(VALID_LOGIN_USERNAME)
        .setEmail(email)
        .setPassword(password);

    // 2. Действие: Выполняем логин, ожидая ошибку 400
    RegisterErrorResponse errorResponse = userService.postLoginAndExpectError(credentials);

    // 3. Проверки (Assertions)
    assertThat(errorResponse)
        .as("Объект ответа с ошибкой не должен быть null")
        .isNotNull();

    assertThat(errorResponse.getError())
        .as("Поле 'error' должно содержать ожидаемое сообщение 'user not found'")
        .isNotBlank()
        // !!! ИСПРАВЛЕНО: Ожидаем точное совпадение с фактическим ответом API
        .isEqualTo(ERROR_MESSAGE_LOGIN_NOT_FOUND);
  }
}