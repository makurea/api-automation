package com.makurea.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * POJO-класс для представления ответа от API на запрос списка пользователей (GET /users).
 * Содержит мета-информацию (page, total_pages) и список объектов User.
 * Аннотация @Data автоматически генерирует геттеры и сеттеры.
 */
@Data // Lombok: генерирует геттеры, сеттеры, equals, hashCode и toString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

  private int page;

  @JsonProperty("per_page")
  private int perPage;

  private int total;

  @JsonProperty("total_pages")
  private int totalPages;

  private List<User> data;
  private Support support;

  @JsonProperty("_meta")
  private Meta meta;

  // Вложенные классы для вложенных структур ответа API

  @Data
  public static class Support {
    private String url;
    private String text;

  }

  @Data
  public static class Meta {
    @JsonProperty("powered_by")
    private String poweredBy;

    @JsonProperty("upgrade_url")
    private String upgradeUrl;

    @JsonProperty("docs_url")
    private String docsUrl;

    @JsonProperty("template_gallery")
    private String templateGallery;

    private String message;
    private List<String> features;

    @JsonProperty("upgrade_cta")
    private String upgradeCta;

  }
}