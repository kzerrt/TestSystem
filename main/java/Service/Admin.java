package Service;

import org.apache.ibatis.annotations.Param;
import pojo.StuInfo_Tea;
import pojo.StudentInfo;
import pojo.Teacher;

import java.util.List;

/**
 * @auther:Florence
 * @date:2022/04/26/13:48
 */
public interface Admin {
    //将学生信息获取
     List<StudentInfo> showStuInfo();

    //更新学生信息
    boolean updateStu(StudentInfo studentInfo);
    //删除学生信息
    boolean deleteStu(int id);
    //添加学生信息
    boolean addStudent(StudentInfo studentInfo);

    //显示教师信息

    List<Teacher> showTeaInfo();
    //删除教师信息
    boolean deleteTea(int code);

    //更新教师信息
    boolean updateTea(Teacher teacher);

    //添加教师信息
    boolean addTeacher(Teacher teacher);

    //显示学生成绩信息
    List<StuInfo_Tea> showStuGrade();

    boolean updateGrade(int grade,String cName, int code, String teaName);

}
