package Service;

import pojo.Count;
import pojo.Teacher;

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

    //修改账号密码
    boolean updatePassword(String password,String username);

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


    void close();

    void commit();
}
