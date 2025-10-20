package com.makurea.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.makurea.basetests.BaseTest;
import com.makurea.models.responses.SingleUserSuccessResponse;
import com.makurea.models.requests.UserRequest;
import com.makurea.models.responses.UserSuccessResponse;
import com.makurea.services.UserService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс, содержащий авто тесты для API-запросов, связанных с сущностью "Пользователи".
 */
@Epic("API Testing")
@Feature("User Management")
@Story("Users")
@DisplayName("Проверка эндпоинта GET /users")
public class UserTests extends BaseTest {

  private final UserService userService = new UserService();

  @Test
  @DisplayName("GET /users - Проверка списка пользователей")
  @Link(name = "Reqres API", url = "https://reqres.in/api/users")
  @Story("Получение списка пользователей")
  void getUsers_ShouldReturnListOfUsers() { // Убран throws Exception
    // 1. Действие: Получаем ответ в виде POJO-объекта UserResponse
    UserSuccessResponse response = userService.getUsers();
    List<UserRequest> users = response.getData();

    // 2. Проверки (Assertions)
    assertThat(users)
        .as("Список пользователей не должен быть пустым")
        .isNotEmpty()
        .satisfies(list -> {
          // Проверяем мета-данные списка
          assertThat(response.getTotal()).as("Общее количество должно быть > 0").isGreaterThan(0);
          assertThat(response.getPage()).as("Номер страницы должен быть > 0").isGreaterThan(0);

          // Проверяем поля первого пользователя для гарантии качества данных
          UserRequest firstUser = list.get(0);
          assertThat(firstUser.getId()).as("ID пользователя должен быть > 0").isGreaterThan(0);
          assertThat(firstUser.getEmail()).as("Email должен содержать символ '@'").contains("@");
          assertThat(firstUser.getFirstName()).as("Имя не должно быть пустым").isNotBlank();
          assertThat(firstUser.getLastName()).as("Фамилия не должна быть пустой").isNotBlank();
          assertThat(firstUser.getAvatar()).as(
              "Аватар должен быть ссылкой, начинающейся с 'https://'").startsWith("https://");
        });
  }

  // test
  @Test
  @DisplayName("GET /users/{id} - Проверка пользователя по id")
  @Link(name = "Reqres API", url = "https://reqres.in/api/users/{id}")
  @Story("Получение определенного пользователя по id")
  void getUsersForId_ShouldReturnOneUser() { // Убран throws Exception
    // 1. Действие: Получаем случайного пользователя
    SingleUserSuccessResponse response = userService.getRandomUser(); // Использовал getRandomUser
    UserRequest user = response.getData();

    // 2. Проверки  (Assertions)
    assertThat(user)
        .as("Объект пользователя не должен быть null")
        .isNotNull()
        .satisfies(u -> {
          // Проверяем поля полученного пользователя
          assertThat(u.getId()).as("ID пользователя должен быть > 0").isGreaterThan(0);
          assertThat(u.getEmail()).as("Email должен содержать символ '@'").contains("@");
          assertThat(u.getFirstName()).as("Имя не должно быть пустым").isNotBlank();
          assertThat(u.getLastName()).as("Фамилия не должна быть пустой").isNotBlank();
          assertThat(u.getAvatar()).as("Аватар должен быть ссылкой, начинающейся с 'https://'")
              .startsWith("https://");
        });
  }
}