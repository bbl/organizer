package hello.model;

import org.hibernate.validator.constraints.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="login")
    @NotNull
    @NotEmpty(message = "*Please provide your login")
    private String login;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotNull
    @NotEmpty(message = "*Please provide your password")
    @Column(name="password")
    private String password;


    //getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected User(){}
    public User(String login, String pass) {
        this.login = login;
        this.password = pass;
    }
}
