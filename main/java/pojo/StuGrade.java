package pojo;

/**
 * @auther:Florence
 * @date:2022/04/25/12:53
 */
public class StuGrade {
    private String course;
    private String teacherName;
    private Integer grade;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
