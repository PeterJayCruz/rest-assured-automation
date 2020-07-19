package org.peterjaycruz.realworld.api.request;

import java.util.ArrayList;

public class Article {

  public String title;
  public String description;
  public String body;
  public ArrayList<String> tagList;

  public Article() {
    tagList = new ArrayList<>();
  }
}
