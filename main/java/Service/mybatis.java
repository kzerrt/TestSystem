package Service;

import pojo.Count;

/**
 * @auther:Florence
 * @date:2022/03/16/20:26
 */
public interface mybatis {
    //void getMapper(String name);
    //查找学生账号
    Count selectByStuUserId(String name);
    //查找教师账号
    Count selectByTeaUserId(String name);
    boolean add(Count user);
    void close();

    void commit();
}
