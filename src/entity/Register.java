package entity;

import java.util.UUID;

public class Register implements AbstractEntity {
    protected String id;
    protected String username;
    protected String password;

    public Register() {}

    public Register(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    public Register(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Register [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    @Override
    public int hashCode() {
        return 31 + id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Register) {
            Register o = (Register) obj;
            return this.id.equals(o.id);
        }
        return false;
    }

    public boolean equals2(Object obj) {
        if (obj instanceof Register) {
            Register o = (Register) obj;
            return this.username.equals(o.username) && this.password.equals(o.password);
        }
        return false;
    }
}
