package pojo;

/**
 * @auther:Florence
 * @date:2022/04/13/16:19
 */
public class Teacher {

    private int id;
    private int code;
    private String name;
    private String sex;
    private int age;
    private String phone;
    private String address;
    private int teacherCountId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTeacherCountId() {
        return teacherCountId;
    }

    public void setTeacherCountId(int teacherCountId) {
        this.teacherCountId = teacherCountId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", teacherCountId=" + teacherCountId +
                '}';
    }
}
