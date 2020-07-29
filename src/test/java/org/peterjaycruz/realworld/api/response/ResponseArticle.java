package org.peterjaycruz.realworld.api.response;

import java.util.ArrayList;

public class ResponseArticle {

  private String id;
  private String slug;
  private String title;
  private String description;
  private String body;
  private boolean favorited;
  private int favoritesCount;
  private String createdAt;
  private String updatedAt;
  private ArrayList<String> tagList;
  private Author author;

  public ResponseArticle() {
    tagList = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public ResponseArticle id(String id) {
    this.id = id;
    return this;
  }

  public String getSlug() {
    return slug;
  }

  public ResponseArticle slug(String slug) {
    this.slug = slug;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public ResponseArticle title(String title) {
    this.title = title;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ResponseArticle description(String description) {
    this.description = description;
    return this;
  }

  public String getBody() {
    return body;
  }

  public ResponseArticle body(String body) {
    this.body = body;
    return this;
  }

  public boolean isFavorited() {
    return favorited;
  }

  public ResponseArticle favorited(boolean favorited) {
    this.favorited = favorited;
    return this;
  }

  public int getFavoritesCount() {
    return favoritesCount;
  }

  public ResponseArticle favoritesCount(int favoritesCount) {
    this.favoritesCount = favoritesCount;
    return this;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public ResponseArticle createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public ResponseArticle updatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  public ArrayList<String> getTagList() {
    return tagList;
  }

  public ResponseArticle tagList(ArrayList<String> tagList) {
    this.tagList = tagList;
    return this;
  }

  public Author getAuthor() {
    return author;
  }

  public ResponseArticle author(Author author) {
    this.author = author;
    return this;
  }
}
