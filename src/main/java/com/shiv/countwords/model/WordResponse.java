package com.shiv.countwords.model;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * WordResponse is a data model class that encapsulates the response of the word processing API. It
 * contains two fields: wordsStartingWithM and wordsLongerThanFive, along with their respective
 * getters and setters.
 * 
 * @author Shvpal Chouhan
 */
@Schema(description = "Word processing response model")
@Tag(name = "Word Processing Response", description = "Model for word processing response")
public class WordResponse implements java.io.Serializable
{

  private static final long serialVersionUID = 1L;

  @Schema(description = "Count of words starting with 'M/m'", example = "5")
  private long wordsStartingWithM;
  @Schema(description = "List of words longer than 5 characters")
  private List<String> wordsLongerThanFive;

  /**
   * Constructor for WordResponse.
   */
  public WordResponse(long wordCountStartsWithM, List<String> longerThanFive)
  {
    this.wordsLongerThanFive = longerThanFive;
    this.wordsStartingWithM = wordCountStartsWithM;
  }

  /**
   * @return the wordsStartingWithM
   */
  public long getWordsStartingWithM()
  {
    return wordsStartingWithM;
  }

  /**
   * @param wordsStartingWithM the wordsStartingWithM to set
   */
  public void setWordsStartingWithM(long wordsStartingWithM)
  {
    this.wordsStartingWithM = wordsStartingWithM;
  }

  /**
   * @return the wordsLongerThanFive
   */
  public List<String> getWordsLongerThanFive()
  {
    return wordsLongerThanFive;
  }

  /**
   * @param wordsLongerThanFive the wordsLongerThanFive to set
   */
  public void setWordsLongerThanFive(List<String> wordsLongerThanFive)
  {
    this.wordsLongerThanFive = wordsLongerThanFive;
  }


}
