package com.adventurequest.model.entity;

import java.util.Arrays;
import java.util.Objects;

public class UserEntity {
    private String username;
    private String password;
    private String email;
    private Byte[] photo;
    private Long gamesPlayed;

    public UserEntity(String username, String password, String email, Byte[] photo, Long gamesPlayed) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.gamesPlayed = gamesPlayed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity user = (UserEntity) o;

        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }


    public Long getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Long gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", photo=" + Arrays.toString(photo) +
               ", gamesPlayed=" + gamesPlayed +
               '}';
    }
}
