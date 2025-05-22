package com.shiv.countwords.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * LoginRequest is a data model class that encapsulates the login request parameters. It contains
 * two fields: username and password, along with their respective getters and setters.
 * 
 * @author Shvpal Chouhan
 */
@Schema(description = "Login request model")
@Tag(name = "Login Request", description = "Model for login request")
public class LoginRequest implements java.io.Serializable
{
  private static final long serialVersionUID = 1L;

  @NotNull
  @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
  @Schema(description = "Username for login", example = "apiuser")
  private String username;

  @NotNull
  @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters")
  @Schema(description = "Password for login", example = "password123")
  private String password;

  public LoginRequest()
  {}

  /**
   * Constructor to create a LoginRequest object with the specified username and password.
   *
   * @param username the username for login
   * @param password the password for login
   */
  public LoginRequest(String username, String password)
  {
    this.username = username;
    this.password = password;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

}
