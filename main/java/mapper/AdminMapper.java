package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import pojo.StudentInfo;
import pojo.Teacher;

import java.util.List;

/**
 * @auther:Florence
 * @date:2022/04/26/13:42
 */
public interface AdminMapper {

    //将学生信息获取
    List<StudentInfo> showStuInfo();

    //更新学生信息
    boolean updateStu(StudentInfo studentInfo);
    //删除学生
    @Delete("delete from student where s_code = #{id}")
    boolean deleteStu(int id);

    //添加学生信息
    boolean addStudent(StudentInfo studentInfo);

    //显示教师信息

    List<Teacher> showTeaInfo();

    //删除教师信息
    @Delete("delete from teacher where tea_id = #{code}")
    boolean deleteTea(int code);

    //更新教师信息
    boolean updateTea(Teacher teacher);

    //添加教师信息
    boolean addTeacher(Teacher teacher);
}
