package web.Teach; /**
 * @auther:Florence
 * @date:2022/04/20/15:24
 */
/***
 * 当教师登录后将教师信息获取
 * */
/*****
 * *********该方法暂时没有起到作用
 * ****方法可以使用
 * */

import Service.Imp.mybatisImp;
import pojo.Count;
import pojo.StuInfo_Tea;
import pojo.Teacher;
import pojo.TeacherHelp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/a/showAllInfoByteaServlet")
public class ShowAllInfoByteaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mybatisImp mybatisImp = new mybatisImp();
        //获取教师账号id
        HttpSession session = request.getSession();
        Count user = (Count) session.getAttribute("user");

        if("stu".equals(user.getPeopleType())){
            return ;
        }

        //将帮助类清理
        TeacherHelp.getStudents().clear();
        //得到教师id
        Teacher teacher = mybatisImp.selectTeachInfoByCountId(user.getId());

        //通过教师id查询学生信息
        List<StuInfo_Tea> stuInfos = mybatisImp.selectStuInfo(teacher.getId());
        //将教师端的学生信息存入到类中
        TeacherHelp.setStudents(stuInfos);
        TeacherHelp.setTeacher(teacher);
        mybatisImp.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
