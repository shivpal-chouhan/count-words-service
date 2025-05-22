package com.shiv.countwords.service;

import org.springframework.stereotype.Service;

import com.shiv.countwords.model.WordResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WordService is a service class that processes a list of words according to specific business
 * rules. It counts the number of words starting with 'M' or 'm' and returns a list of words longer
 * than 5 characters.
 * 
 * @author Shvpal Chouhan
 */
@Service
public class WordService
{

  /**
   * Processes the input list of words and returns a response with the number of words starting with
   * 'M' or 'm' and a list of words longer than 5 characters.
   *
   * @param words the list of input strings
   * @return WordResponse containing the business rule results
   */
  public WordResponse processWords(List<String> words)
  {
    long wordCountStartsWithM = words.stream().filter(word -> word != null && !word.isBlank())
        .filter(word -> word.toLowerCase().startsWith("m")).count();

    List<String> longerThanFive = words.stream().filter(word -> word != null && word.length() > 5)
        .collect(Collectors.toList());

    return new WordResponse(wordCountStartsWithM, longerThanFive);
  }
}
