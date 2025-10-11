package com.makurea.services;


import com.makurea.client.ReqresClient;
import com.makurea.models.SingleUserResponse;
import com.makurea.models.User;
import com.makurea.models.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserService {
  private final ReqresClient client = new ReqresClient();
  private final ObjectMapper mapper = new ObjectMapper();
  private static final Logger log = LogManager.getLogger(UserService.class);

  public UserResponse getUsers() throws Exception {
    log.info("Отправка GET /users запроса");
    Response response = client.getUsers();
    log.debug("Ответ API: {}", response.asString());
    return mapper.readValue(response.asString(), UserResponse.class);
  }

  public SingleUserResponse getUsersId() throws Exception {
    log.info("Отправка GET /users запроса");
    Response response = client.getUsersId();
    log.debug("Ответ API: {}", response.asString());
    return mapper.readValue(response.asString(), SingleUserResponse.class);
  }
}