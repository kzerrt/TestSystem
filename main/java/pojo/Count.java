package pojo;

/**
 * @auther:Florence
 * @date:2022/03/16/19:21
 */
public class Count {
    private int id;
    private String username;
    private String password;
    private String peopleType;

    public Count(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Count() {
    }

    public String getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
