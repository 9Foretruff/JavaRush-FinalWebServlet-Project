package com.adventurequest.model.dto;

public final class UserDto {
    private final String username;
    private final String email;

    UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDto)) return false;
        final UserDto other = (UserDto) o;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "UserDto(username=" + this.getUsername() + ", email=" + this.getEmail() + ")";
    }

    public static class UserDtoBuilder {
        private String username;
        private String email;

        UserDtoBuilder() {
        }

        public UserDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDto build() {
            return new UserDto(this.username, this.email);
        }

        public String toString() {
            return "UserDto.UserDtoBuilder(username=" + this.username + ", email=" + this.email + ")";
        }
    }
}
