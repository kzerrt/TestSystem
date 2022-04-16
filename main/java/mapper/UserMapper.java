package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import pojo.Count;
import pojo.Teacher;

import java.util.List;

/**
 * @auther:Florence
 * @date:2022/03/16/19:26
 */
public interface UserMapper {

    /**
     * 通过用户名获取到用户账户信息
     * */
    @Select("select * from tb_user where username = #{name}")
    Count selectByUsername(String name);

    /**
     * 将用户名添加到数据库中
     * */
    @Insert("insert into tb_user values(null,#{username},#{password})")
    boolean add(Count user);

    @Select("select * from tb_user")
    List<Count> select();

    //查找教师账号
     @Select("select * from teach_user where username = #{name}")
    Count selectByTeaUserId(String name);
    /**
     * 通过教师账号id查询教师信息
     * */

    Teacher selectByTeaId(int id);


}
