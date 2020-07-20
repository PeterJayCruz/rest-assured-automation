package org.peterjaycruz.realworld.api.response;

import java.util.ArrayList;

public class ArticlesResponseBody extends ApiResponseBody {

  private ResponseArticle article;

  public ArticlesResponseBody() {
    this.article = new ResponseArticle();
  }

  public ResponseArticle getArticle() {
    return article;
  }

  public ArticlesResponseBody title(String title) {
    this.article.title(title);
    return this;
  }

  public ArticlesResponseBody description(String description) {
    this.article.description(description);
    return this;
  }

  public ArticlesResponseBody body(String body) {
    this.article.body(body);
    return this;
  }

  public ArticlesResponseBody tagList(ArrayList<String> tagList) {
    this.article.tagList(tagList);
    return this;
  }

}
