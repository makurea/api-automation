package com.makurea.utils;

/**
 * Класс для хранения всех констант, связанных с API.
 * Это позволяет легко менять адреса и пути в одном месте.
 */

public class ApiConfig {
  public static final String BASE_URL = "https://reqres.in/api";
  public static final String USERS_URL = "/users";
  public static final String BASE_USERS_URL = BASE_URL + USERS_URL;
  public static final String REGISTER_URL = BASE_URL + "/register";


}
