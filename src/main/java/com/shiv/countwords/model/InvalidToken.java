package com.shiv.countwords.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * InvalidToken is a custom exception class that represents an invalid token error. It extends
 * RuntimeException and implements Serializable.
 * 
 * @author Shivpal Chouhan
 */
@Schema(description = "Invalid token error model")
@Tag(name = "Invalid Token", description = "Model for invalid token error")
public class InvalidToken extends RuntimeException implements java.io.Serializable
{
  private static final long serialVersionUID = 1L;

  public InvalidToken(String message)
  {
    super(message);
  }

  public InvalidToken(String message, Throwable cause)
  {
    super(message, cause);
  }

  public InvalidToken(Throwable cause)
  {
    super(cause);
  }

}
