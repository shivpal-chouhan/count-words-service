package com.shiv.countwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shiv.countwords.model.ApiError;
import com.shiv.countwords.model.LoginRequest;
import com.shiv.countwords.security.JwtUtil;
import com.shiv.countwords.security.TempUserValiation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/**
 * REST controller for handling authentication requests. It generates JWT tokens for valid users.
 * 
 * @author Shvpal Chouhan
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController
{

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private TempUserValiation tempUserValiation;

  /**
   * Generates a JWT token for the given username.
   *
   * @param loginRequest the login request containing username and password
   * @return the generated JWT token
   */
  @Operation(summary = "Login",
      description = "Generates a JWT token for the given username and password.")
  @ApiResponse(responseCode = "200", description = "Successfully logged in",
      content = {@Content(mediaType = "application/json",
          schema = @Schema(implementation = String.class))})
  @ApiResponse(responseCode = "400", description = "Invalid username or password",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = ApiError.class)))
   
  @PostMapping("/login")
  public String login(@RequestBody @Valid LoginRequest loginRequest)
  {
    tempUserValiation.validateUser(loginRequest);
    return jwtUtil.generateToken(loginRequest.getUsername());

  }

}
