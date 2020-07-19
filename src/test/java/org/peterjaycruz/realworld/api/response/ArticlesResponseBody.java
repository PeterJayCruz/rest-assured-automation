package org.peterjaycruz.realworld.api.response;

public class ArticlesResponseBody extends ApiResponseBody {

  public ResponseArticle article;

  public ArticlesResponseBody() {
    this.article = new ResponseArticle();
  }
}
