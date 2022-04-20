package pojo;

/**
 * @auther:Florence
 * @date:2022/04/18/15:21
 */
public class StuInfo_Tea {
    private int id;
    private int code;
    private String sex;
    private String name;
    private String dept;
    private String collage;
    private String course;
    private int grade;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
