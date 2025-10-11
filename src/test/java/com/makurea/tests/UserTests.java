package com.makurea.tests;

import com.makurea.basetests.BaseTest;
import com.makurea.models.User;
import com.makurea.models.UserResponse;
import com.makurea.services.UserService;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("API")
public class UserTests extends BaseTest {

  private final UserService userService = new UserService();

  @Test
  @DisplayName("GET /users - Проверка списка пользователей")
  @Link(name = "Reqres API", url = "https://reqres.in/api/users")
  @Story("Получение списка пользователей")
  void getUsers_ShouldReturnListOfUsers() throws Exception {
    UserResponse response = userService.getUsers();
    List<User> users = response.getData();

    assertThat(users)
        .isNotEmpty()
        .satisfies(list -> {
          User firstUser = list.get(0);
          assertThat(firstUser.getId()).isGreaterThan(0);
          assertThat(firstUser.getEmail()).contains("@");
          assertThat(firstUser.getFirstName()).isNotBlank();
          assertThat(firstUser.getLastName()).isNotBlank();
          assertThat(firstUser.getAvatar()).startsWith("https://");
        });
  }
}
