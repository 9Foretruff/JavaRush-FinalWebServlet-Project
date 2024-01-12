package com.adventurequest.model.entity;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;
import java.util.Objects;

public class UserEntity {

    private Long id;
    private String username;
    private String password;
    private String email;
    private byte[] photo;
    private Long gamesPlayed;

    public UserEntity(Long id, String username, String password, String email, byte[] photo, Long gamesPlayed) {
        this.id = id;
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

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Long gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
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
    public String getBase64Image() {
        return Base64.encodeBase64String(photo);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", photo=" + Arrays.toString(photo) +
               ", gamesPlayed=" + gamesPlayed +
               '}';
    }
}
