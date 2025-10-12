package com.makurea.client;

import com.makurea.models.SingleUserResponse;
import com.makurea.models.UserResponse; // Добавлен для прямого возврата объекта
import com.makurea.utils.ApiConfig;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.makurea.utils.ApiConfig.BASE_USERS_URL;
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

}