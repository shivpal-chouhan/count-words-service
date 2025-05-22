package com.shiv.countwords.security;

import org.springframework.stereotype.Component;
import com.shiv.countwords.model.InvalidToken;
import com.shiv.countwords.model.LoginRequest;

/**
 * TempUserValiation is a temporary class for validating user credentials. It checks if the provided
 * username and password match the hardcoded values.
 * 
 * @author Shvpal Chouhan
 */

@Component
public class TempUserValiation
{

  /**
   * Validates the user credentials. This is a temporary implementation and should be replaced with
   * actual user validation logic.
   *
   * @param loginRequest the login request containing username and password
   * @throws InvalidToken if the username or password is invalid
   */
  public void validateUser(LoginRequest loginRequest)
  {
    // Replace with actual user validation logic
    if (!(loginRequest.getUsername().equals("apiuser")
        && loginRequest.getPassword().equals("password123")))
    {
      throw new InvalidToken("Invalid username or password");
    }
  }
}
