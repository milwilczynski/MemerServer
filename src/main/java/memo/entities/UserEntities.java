package memo.entities;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEntities {
    private String login;
    private String password;

    public UserEntities(@JsonProperty("login") String login,
                        @JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
