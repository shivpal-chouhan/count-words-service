package com.shiv.countwords.model;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * ApiError is a custom error response class that encapsulates error details such as timestamp,
 * message, HTTP status, and any additional errors.
 * 
 * @author Shivpal Chouhan
 */
@Schema(description = "API error response model")
@Tag(name = "API Error", description = "Model for API error response")
public class ApiError implements java.io.Serializable
{
  private static final long serialVersionUID = 1L;
  @Schema(description = "Timestamp of the error", example = "2023-10-01T12:00:00")
  private LocalDateTime timestamp;

  @Schema(description = "Error message", example = "Invalid input")
  private String message;

  @Schema(description = "HTTP status code", example = "400")
  private HttpStatus status;

  @Schema(description = "Additional error details")
  private Map<String, String> errors;

  public ApiError(String message, HttpStatus status)
  {
    this.timestamp = LocalDateTime.now();
    this.message = message;
    this.status = status;
  }

  public ApiError(String message, HttpStatus status, Map<String, String> errors)
  {
    this(message, status);
    this.errors = errors;
  }

  public LocalDateTime getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp)
  {
    this.timestamp = timestamp;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public HttpStatus getStatus()
  {
    return status;
  }

  public void setStatus(HttpStatus status)
  {
    this.status = status;
  }

  public Map<String, String> getErrors()
  {
    return errors;
  }

  public void setErrors(Map<String, String> errors)
  {
    this.errors = errors;
  }

  @Override
  public String toString()
  {
    return "ApiError [message=" + message + ", status=" + status + ", errors=" + errors + "]";
  }

}
