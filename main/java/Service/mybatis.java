package Service;

import org.apache.ibatis.annotations.Param;
import pojo.*;

import java.util.List;

/**
 * @auther:Florence
 * @date:2022/03/16/20:26
 */
public interface mybatis {
    //void getMapper(String name);
    //查找学生账号
    Count selectByStuUserId(String name);

    //添加学生账号
    boolean add(Count user);
    //查找学生信息
    StudentInfo selectStuInfoByCountId(int id);

    boolean updatePasByStu(String password , String username);

    boolean updateStuInfo(StudentInfo s);
    //修改账号密码
    boolean updatePassword(String password,String username);
    //根据学生id获得学生相关成绩信息
    List<StuGrade> showGradeByStuId(int id);

    /**
     * ********教师*********
     *
     * */
    //查找教师账号
    Count selectByTeaUserId(String name);
    //通过账号id查找教师信息
    Teacher selectTeachInfoByCountId(int id);
    //修改教师信息
    boolean updateTeachInfo(Teacher teacher);
    boolean updateTeachInfo(int code,String name,String phone,String address);

    //修改学生成绩
    boolean updateGrade(int grade,  String course, int code,int teaId);

    List<StuInfo_Tea> selectInfoByCondition(StuInfo_Tea s,int id);

    //获取学生信息
    List<StuInfo_Tea> selectStuInfo(int teaId);
    void close();

    void commit();
}
