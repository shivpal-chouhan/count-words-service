package com.shiv.countwords;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.shiv.countwords.model.LoginRequest;
import com.shiv.countwords.model.WordResponse;

/**
 * Integration tests for the CountWordsService application.
 * 
 * @author Shvpal Chouhan
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountWordsServiceApplicationTests
{

  @Autowired
  private TestRestTemplate restTemplate;

  private String getJwtToken(String username, String password)
  {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    LoginRequest payload = new LoginRequest(username, password);
    HttpEntity<LoginRequest> request = new HttpEntity<>(payload, headers);
    ResponseEntity<String> response =
        restTemplate.postForEntity("/api/auth/login", request, String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    return response.getBody();
  }

  @Test
  void testTokenWithInvalidUserDetails()
  {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    LoginRequest payload = new LoginRequest("12", "12");
    HttpEntity<LoginRequest> request = new HttpEntity<>(payload, headers);
    ResponseEntity<String> response =
        restTemplate.postForEntity("/api/auth/login", request, String.class);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertTrue(response.getBody().contains("Validation failed"));

  }

  @Test
  void testProcessWords_withInvalidJwtToken()
  {
    String jwt = "INVALID_TOKEN";
    List<String> input = List.of("Monkey", "banana", "mountain", "Mouse", "cat", "elephant");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(jwt);
    HttpEntity<List<String>> request = new HttpEntity<>(input, headers);
    ResponseEntity<WordResponse> response =
        restTemplate.postForEntity("/api/words/process", request, WordResponse.class);
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

  }

  @Test
  void testProcessWords_withJwtToken()
  {
    String jwt = getJwtToken("apiuser", "password123");
    List<String> input = List.of("Monkey", "banana", "mountain", "Mouse", "cat", "elephant");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(jwt);

    HttpEntity<List<String>> request = new HttpEntity<>(input, headers);

    ResponseEntity<WordResponse> response =
        restTemplate.postForEntity("/api/words/process", request, WordResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    WordResponse body = response.getBody();
    assertNotNull(body);
    assertEquals(3, body.getWordsStartingWithM());
    assertTrue(body.getWordsLongerThanFive().contains("banana"));
    assertTrue(body.getWordsLongerThanFive().contains("mountain"));
  }

  @Test
  void testProcessWords_ForNegetiveData()
  {
    String jwt = getJwtToken("apiuser", "password123");

    List<String> input = List.of("cat", "rat", "dog", "man");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(jwt);

    HttpEntity<List<String>> request = new HttpEntity<>(input, headers);

    ResponseEntity<WordResponse> response =
        restTemplate.postForEntity("/api/words/process", request, WordResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    WordResponse body = response.getBody();
    assertNotNull(body);
    /* The words starting with M are */
    assertEquals(1, body.getWordsStartingWithM());

    /* The words longer than 5 should be empty */
    assertTrue(body.getWordsLongerThanFive().isEmpty());
  }

}
