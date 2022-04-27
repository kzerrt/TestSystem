package Service.Imp;

import Service.Admin;
import mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.StudentInfo;
import pojo.Teacher;

import java.util.List;

/**
 * @auther:Florence
 * @date:2022/04/26/13:49
 */
public class AdminImp implements Admin {

    private SqlSession sqlSession;

    public AdminImp() {
        sqlSession = SqlSessionFactoryImp.getSqlSessionFactory().openSession(false);
    }

    @Override
    public List<StudentInfo> showStuInfo() {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        return mapper.showStuInfo();
    }

    @Override
    public boolean updateStu(StudentInfo studentInfo) {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        return mapper.updateStu(studentInfo);
    }

    @Override
    public boolean deleteStu(int id) {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        return mapper.deleteStu(id);
    }

    @Override
    public boolean addStudent(StudentInfo studentInfo) {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        return mapper.addStudent(studentInfo);
    }

    @Override
    public List<Teacher> showTeaInfo() {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        return mapper.showTeaInfo();
    }

    @Override
    public boolean deleteTea(int code) {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        return mapper.deleteTea(code);
    }

    @Override
    public boolean updateTea(Teacher teacher) {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        return mapper.updateTea(teacher);
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        return mapper.addTeacher(teacher);
    }


    public void close(){
        sqlSession.close();
    }
    public void roll(){
        sqlSession.rollback();
    }
    public void commit(){
        sqlSession.commit();
    }
}
