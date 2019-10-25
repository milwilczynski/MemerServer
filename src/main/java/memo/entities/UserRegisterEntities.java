package memo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterEntities extends UserEntities{
    private String email;
    public UserRegisterEntities(@JsonProperty("login") String login,
                                @JsonProperty("password") String password,
                                @JsonProperty("email") String email) {
        super(login, password);
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
