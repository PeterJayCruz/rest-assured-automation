package org.peterjaycruz.realworld.api.request;

import java.util.ArrayList;

public class ArticlesRequestBody extends RequestBody {

  private Article article;

  public ArticlesRequestBody() {
    this.article = article = new Article();
  }

  public Article getArticle() {
    return this.article;
  }

  public ArticlesRequestBody title(String title) {
    this.article.title = title;
    return this;
  }

  public ArticlesRequestBody description(String description) {
    this.article.description = description;
    return this;
  }

  public ArticlesRequestBody body(String body) {
    this.article.body = body;
    return this;
  }

  public ArticlesRequestBody tagList(ArrayList<String> tagList) {
    this.article.tagList = tagList;
    return this;
  }
}
