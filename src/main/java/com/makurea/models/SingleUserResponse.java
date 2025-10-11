package com.makurea.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserResponse {
  private User data;
  private UserResponse.Support support;
  private UserResponse.Meta meta;

  public User getData() {
    return data;
  }

  public void setData(User data) {
    this.data = data;
  }

  public UserResponse.Support getSupport() {
    return support;
  }

  public void setSupport(UserResponse.Support support) {
    this.support = support;
  }

  public UserResponse.Meta getMeta() {
    return meta;
  }

  public void setMeta(UserResponse.Meta meta) {
    this.meta = meta;
  }
}
