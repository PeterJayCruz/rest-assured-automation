package org.peterjaycruz.realworld.api.response;

import java.util.ArrayList;

public class ResponseArticle {

  public String id;
  public String slug;
  public String title;
  public String description;
  public String body;
  public boolean favorited;
  public int favoritesCount;
  public String createdAt;
  public String updatedAt;
  public ArrayList<String> tagList;
  public Author author;

  public ResponseArticle() {
    tagList = new ArrayList<>();
  }
}
