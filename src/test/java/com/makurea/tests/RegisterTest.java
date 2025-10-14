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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    assertThat(response.getToken())
        .as("Проверка наличия и непустого значения токена")
        .isNotNull()
        .isNotEmpty();

    assertThat(response.getId())
        .as("Проверка наличия и положительного значения ID")
        .isNotNull()
        .isGreaterThan(0);
  }

  /**
   * Тест: Попытка регистрации с невалидным email.
   * Ожидаемый результат: HTTP Status 400 и сообщение об ошибке.
   */
  @Test
  @DisplayName("Невалидный email: должна вернуться ошибка 400 Bad Request")
  void registration_withInvalidEmail_ShouldReturn400Error() {
    RegisterRequest invalidCredentials = new RegisterRequest()
        .setEmail("готем сити")
        .setPassword("готем123");

    RegisterErrorResponse errorResponse = reqresClient.postRegisterUserWithError(invalidCredentials);

    assertThat(errorResponse)
        .as("Проверка, что ответ с ошибкой десериализован")
        .isNotNull();

    assertThat(errorResponse.getError())
        .as("Проверка наличия сообщения об ошибке")
        .isNotNull()
        .isNotEmpty();

    assertThat(errorResponse.getError())
        .as("Проверка содержания ошибки")
        .contains("Note: Only defined users succeed registration");
  }

  /**
   * Параметризованный тест: проверка разных невалидных комбинаций email/пароля.
   */
  @ParameterizedTest(name = "Невалидный кейс #{index}: email = \"{0}\", password = \"{1}\"")
  @CsvSource({
      // 1. Пустой email
      "'', gotham123",
      // 2. Валидный email, пустой пароль
      "batman@gotham.com, ''",
      // 3. Email без домена
      "joker@, haha123",
      // 4. Email без @
      "alfred.gmail.com, pass123",
      // 5. Email с пробелами
      "'got ham@city.com', dark123",
      // 6. Email слишком длинный (>100 символов)
      "'averylongemailaddress_thatiswaytoolongtobevalid_and_shouldcauseanerror_because_it_exceeds_limit@example.com', longpass",
      // 7. Email с кириллицей
      "готем@сити.бел, gotham123",
      // 8. Пароль слишком короткий
      "robin@gotham.com, 1",
      // 9. Пароль слишком длинный (>50 символов)
      "penguin@gotham.com, '123456789012345678901234567890123456789012345678901'",
      // 10. Оба поля пустые
      "'', ''"
  })
  @DisplayName("Невалидные данные: должна вернуться ошибка 400 Bad Request")
  void registration_withInvalidData_ShouldReturn400Error(String email, String password) {
    RegisterRequest invalidCredentials = new RegisterRequest()
        .setEmail(email)
        .setPassword(password);

    RegisterErrorResponse errorResponse = reqresClient.postRegisterUserWithError(invalidCredentials);

    assertThat(errorResponse)
        .as("Проверка, что ответ с ошибкой десериализован")
        .isNotNull();

    assertThat(errorResponse.getError())
        .as("Проверка наличия сообщения об ошибке")
        .isNotNull()
        .isNotEmpty();

    assertThat(errorResponse.getError())
        .as("Проверка содержания ошибки")
        .contains("Note: Only defined users succeed registration");
  }
}
