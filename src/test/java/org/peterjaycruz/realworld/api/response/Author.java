package org.peterjaycruz.realworld.api.response;

public class Author {

  private String username;
  private String bio;
  private String image;
  private boolean following;

  public String getUsername() {
    return username;
  }

  public Author username(String username) {
    this.username = username;
    return this;
  }

  public String getBio() {
    return bio;
  }

  public Author bio(String bio) {
    this.bio = bio;
    return this;
  }

  public String getImage() {
    return image;
  }

  public Author image(String image) {
    this.image = image;
    return this;
  }

  public boolean isFollowing() {
    return following;
  }

  public Author following(boolean following) {
    this.following = following;
    return this;
  }
}
