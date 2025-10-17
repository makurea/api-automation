package com.makurea.tests;

import static com.makurea.utils.TestData.VALID_EMAIL;
import static com.makurea.utils.TestData.VALID_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

import com.makurea.client.ReqresClient;
import com.makurea.models.RegisterErrorResponse;
import com.makurea.models.RegisterRequest;
import com.makurea.models.RegisterSuccessResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Epic("API Testing")
@Feature("User Management")
@Story("Registration")
@DisplayName("Проверка эндпоинта POST /api/register")
public class RegisterTest {

  private static ReqresClient reqresClient;

  @BeforeAll
  static void setUp() {
    reqresClient = new ReqresClient();
  }

  @Test
  @DisplayName("Успешная регистрация: должен вернуться статус 200 с ID и Token")
  void registration_withValidCredentials_ShouldReturn200AndToken() {
    RegisterRequest credentials = new RegisterRequest()
        .setEmail(VALID_EMAIL)
        .setPassword(VALID_PASSWORD);

    RegisterSuccessResponse response = reqresClient.postRegisterUser(credentials);

    assertThat(response.getToken())
        .as("Проверка наличия и непустого значения токена")
        .isNotNull()
        .isNotEmpty();

    assertThat(response.getId())
        .as("Проверка наличия и положительного значения ID")
        .isNotNull()
        .isGreaterThan(0);
  }

  @Test
  @DisplayName("Несуществующий пользователь: должна вернуться ошибка 400 и корректное сообщение")
  void registration_withInvalidEmail_ShouldReturn400Error() {
    RegisterRequest invalidCredentials = new RegisterRequest()
        .setEmail("gothem@gotham.com")
        .setPassword("gotham123");

    RegisterErrorResponse errorResponse = reqresClient.postRegisterUserWithError(
        invalidCredentials);

    assertThat(errorResponse)
        .as("Проверка, что ответ с ошибкой десериализован")
        .isNotNull();

    assertThat(errorResponse.getError())
        .as("Проверка содержания ошибки")
        .isEqualTo("Note: Only defined users succeed registration");
  }

  @ParameterizedTest(name = "Невалидный кейс #{index}: email = \"{0}\", password = \"{1}\" → ожидание: {2}")
  @CsvSource({
      "'', gotham123, Missing email or username",
      "batman@gotham.com, '', Missing password",
      "'', '', Missing email or username"
  })
  @DisplayName("Невалидные данные: должна вернуться ошибка 400 Bad Request")
  void registration_withInvalidData_ShouldReturn400Error(String email, String password,
      String expectedError) {
    RegisterRequest invalidCredentials = new RegisterRequest()
        .setEmail(email)
        .setPassword(password);

    RegisterErrorResponse errorResponse = reqresClient.postRegisterUserWithError(
        invalidCredentials);

    assertThat(errorResponse)
        .as("Проверка, что ответ с ошибкой десериализован")
        .isNotNull();

    assertThat(errorResponse.getError())
        .as("Проверка содержания ошибки")
        .isEqualTo(expectedError);
  }
}
