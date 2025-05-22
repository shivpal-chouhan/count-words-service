package com.shiv.countwords.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shiv.countwords.model.WordResponse;
import com.shiv.countwords.security.JwtUtil;
import com.shiv.countwords.service.WordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for processing a list of words. Secured with Basic Auth and documented with
 * Swagger.
 * 
 * @author Shvpal Chouhan
 */
@RestController
@RequestMapping("/api/words")
@Tag(name = "Word Processing API", description = "Processes words with business rules")
public class WordController
{

  @Autowired
  private WordService wordService;

  @Autowired
  private JwtUtil jwtUtil;

  /**
   * Processes the input list of words and returns a response with the number of words starting with
   * 'M' or 'm' and a list of words longer than 5 characters.
   *
   * @param words the list of input strings
   * @param authHeader the JWT token for authentication
   * @return WordResponse containing the business rule results
   * @throws RuntimeException if the token is invalid
   */
  @PostMapping("/process")
  @Operation(summary = "Process words",
      description = "Counts words starting with 'M/m' and returns words longer than 5 characters.")
  @Tag(name = "Word Processing API")

  @ApiResponse(responseCode = "200", description = "Successfully processed words",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = WordResponse.class)))
  @ApiResponse(responseCode = "400", description = "Invalid input",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = WordResponse.class)))
  @ApiResponse(responseCode = "401", description = "Unauthorized",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = WordResponse.class)))
  public WordResponse processWords(@RequestHeader("Authorization") String authHeader,
      @RequestBody List<String> words)
  {
    String token = authHeader.replace("Bearer ", "");

    // Validate the token and extract the username
    jwtUtil.validateTokenAndGetUsername(token);
    return wordService.processWords(words);

  }
}
