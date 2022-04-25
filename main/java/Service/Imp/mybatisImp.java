package Service.Imp;

import Service.mybatis;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.*;

import java.util.List;


/**
 * @auther:Florence
 * @date:2022/03/16/20:27
 */
public class mybatisImp implements mybatis {

    private SqlSession sqlSession;

    public mybatisImp(){
        sqlSession = SqlSessionFactoryImp.getSqlSessionFactory().openSession(false);
    }

    @Override
    public Count selectByStuUserId(String name) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Count user = mapper.selectByUsername(name);
        return user;
    }
    @Override
    public boolean add(Count user) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.add(user);
    }

    @Override
    public StudentInfo selectStuInfoByCountId(int id) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectStuInfoByCountId(id);
    }
    @Override
    public boolean updatePasByStu(String password , String username) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean b = mapper.updatePasByStu(password,username);
        return b;
    }

    @Override
    public boolean updateStuInfo(StudentInfo s) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.updateStuInfo(s);
    }
    @Override
    public List<StuGrade> showGradeByStuId(int id) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.showGradeByStuId(id);
    }


    /**
     *
     * *************教师*************
     * */

    @Override
    public Count selectByTeaUserId(String name) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Count user = mapper.selectByTeaUserId(name);
        return user;
    }

    @Override
    public boolean updatePassword(String password , String username) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean b = mapper.updatePassword(password,username);
        return b;
    }



    //通过账号id查找教师信息
    @Override
    public Teacher selectTeachInfoByCountId(int id){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        return mapper.selectTeachInfoByCountId(id);
    }

    //修改教师信息
    public boolean updateTeachInfo(Teacher teacher){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        return mapper.updateTeachInfo(teacher);
    }
    public boolean updateTeachInfo(int code,String name,String phone,String address){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        return mapper.updateTeachInfo(code,name,phone,address);
    }



    @Override
    public List<StuInfo_Tea> selectStuInfo(int teaId) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectStuInfo(teaId);
    }

    @Override
    public boolean updateGrade(int grade, String course, int code, int teaId) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.updateGrade(grade,course,code,teaId);
    }

    @Override
    public List<StuInfo_Tea> selectInfoByCondition(StuInfo_Tea s,int id) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectInfoByCondition(s,id);
    }

    @Override
    public void close()
    {
        sqlSession.close();
    }

    @Override
    public void commit(){
        sqlSession.commit();
    }

    public void roll(){
        sqlSession.rollback();
    }
}
