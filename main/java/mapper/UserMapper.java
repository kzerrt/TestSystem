package mapper;

import org.apache.ibatis.annotations.*;
import pojo.*;

import java.util.List;

/**
 * @auther:Florence
 * @date:2022/03/16/19:26
 */
public interface UserMapper {

    /**
     * 通过用户名获取到用户账户信息
     * */
    @Select("select * from tb_user where username = #{name};")
    Count selectByUsername(String name);

    /**
     * 将用户名添加到数据库中
     * */
    @Insert("insert into tb_user values(null,#{username},#{password});")
    boolean add(Count user);
    /****
     * **通过账号获取到学生详细信息
     * */
    StudentInfo selectStuInfoByCountId(int id);
    /***
     * ****根据学生的id获得学生成绩相关内容
     * */
    List<StuGrade> showGradeByStuId(int id);

    @Update("update tb_user set password = #{password} where username = #{username};")
    boolean updatePasByStu(@Param("password")String password,@Param("username")String username);

    @Update("update student set phone = #{phone},address = #{address} where id = #{id};")
    boolean updateStuInfo(StudentInfo studentInfo);



    /**
     *
     *
     * ***************教师**********************
     *
     * */

    /**
     * 通过教师账号id查询教师信息
     * */

    Teacher selectByTeaId(int id);

    //查找教师账号
    @Select("select * from teach_user where username = #{name};")
    Count selectByTeaUserId(String name);

    /**
     * 修改教师账号密码
     * */
    @Update("update teach_user set password = #{password} where username = #{username};")
    boolean updatePassword(@Param("password") String password, @Param("username") String username);

    /////通过账号id查询教师信息
    @ResultMap("Teacher")
    @Select("select id,t_code,name, sex, year(now()) - birthday age, phone, address, tea_id from teacher where tea_id = #{id};")
    Teacher selectTeachInfoByCountId(int id);
    ////修改教师信息
    boolean updateTeachInfo(Teacher teacher);
    boolean updateTeachInfo(@Param("code") int code,@Param("name") String name,@Param("phone") String phone,@Param("address") String address);
    //查询学生成绩信息
    List<StuInfo_Tea> selectStuInfo(int teaId);

    //条件查询学生信息
    List<StuInfo_Tea> selectInfoByCondition(@Param("s") StuInfo_Tea s,@Param("teaId")int id);

    //更改学生成绩
    boolean updateGrade(@Param("grade") int grade,@Param("cName")String cName,@Param("code") int code,@Param("teaId") int teaId);

}
