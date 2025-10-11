package com.makurea.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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

  // Геттеры и сеттеры
  public int getPage() { return page; }
  public void setPage(int page) { this.page = page; }

  public int getPerPage() { return perPage; }
  public void setPerPage(int perPage) { this.perPage = perPage; }

  public int getTotal() { return total; }
  public void setTotal(int total) { this.total = total; }

  public int getTotalPages() { return totalPages; }
  public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

  public List<User> getData() { return data; }
  public void setData(List<User> data) { this.data = data; }

  public Support getSupport() { return support; }
  public void setSupport(Support support) { this.support = support; }

  public Meta getMeta() { return meta; }
  public void setMeta(Meta meta) { this.meta = meta; }

  // Вложенные классы
  public static class Support {
    private String url;
    private String text;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
  }

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

    public String getPoweredBy() { return poweredBy; }
    public void setPoweredBy(String poweredBy) { this.poweredBy = poweredBy; }

    public String getUpgradeUrl() { return upgradeUrl; }
    public void setUpgradeUrl(String upgradeUrl) { this.upgradeUrl = upgradeUrl; }

    public String getDocsUrl() { return docsUrl; }
    public void setDocsUrl(String docsUrl) { this.docsUrl = docsUrl; }

    public String getTemplateGallery() { return templateGallery; }
    public void setTemplateGallery(String templateGallery) { this.templateGallery = templateGallery; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }

    public String getUpgradeCta() { return upgradeCta; }
    public void setUpgradeCta(String upgradeCta) { this.upgradeCta = upgradeCta; }
  }
}
