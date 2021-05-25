package ua.taras.kushmyruk.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
  USER, MANAGER, ADMIN;

  @Override
  public String getAuthority() {
    return null;
  }

}
