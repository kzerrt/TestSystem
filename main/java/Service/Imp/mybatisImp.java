package Service.Imp;

import Service.mybatis;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Count;

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
    public Count selectByTeaUserId(String name) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Count user = mapper.selectByTeaUserId(name);
        return user;
    }

    @Override
    public boolean add(Count user) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
       return mapper.add(user);
    }

    public List<Count> select(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Count> select = mapper.select();
        return select;
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
}
