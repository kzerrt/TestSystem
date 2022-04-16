package pojo;

import java.io.Serializable;

/**
 * @auther:Florence
 * @date:2022/04/06/16:42
 */
public class Count_Json implements Serializable {
    private String username;
    private String password;
    private String radio;
    private boolean remember;

    public Count_Json() {
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

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public boolean getRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember =  remember;
    }
}
