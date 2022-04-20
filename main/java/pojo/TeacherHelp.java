package pojo;

import java.util.List;

/**
 * @auther:Florence
 * @date:2022/04/19/13:27
 */
/****
 * **将教师信息临时存入到该类中
 * */
public class TeacherHelp {
    static List<StuInfo_Tea> students;
    static Teacher teacher;

    public static Teacher getTeacher() {
        return teacher;
    }

    public static void setTeacher(Teacher teacher) {
        TeacherHelp.teacher = teacher;
    }

    public static List<StuInfo_Tea> getStudents() {
        return students;
    }

    public static void setStudents(List<StuInfo_Tea> students) {
        TeacherHelp.students = students;
    }
}
