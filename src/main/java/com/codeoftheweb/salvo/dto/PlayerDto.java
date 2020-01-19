package com.codeoftheweb.salvo.dto;

public class PlayerDto {

    private long id;
    private String email;

    public long getId() {
        return id;
    }

    public PlayerDto() {}

    public PlayerDto(String userName) {
        this.email = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static final class Builder {
        private long id;
        private String email;

        private Builder() {
        }

        public static Builder aPlayerDto() {
            return new Builder();
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public PlayerDto build() {
            PlayerDto playerDto = new PlayerDto();
            playerDto.setId(id);
            playerDto.setEmail(email);
            return playerDto;
        }
    }
}
