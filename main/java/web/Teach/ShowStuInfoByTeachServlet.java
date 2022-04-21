package web.Teach; /**
 * @auther:Florence
 * @date:2022/04/19/9:11
 */
/***
 * **在教师端得到学生成绩信息
 * */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.Count;
import pojo.StuInfo_Tea;
import pojo.Teacher;
import pojo.TeacherHelp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/a/showStuInfoByTeachServlet")
public class ShowStuInfoByTeachServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");

        //获取页面请求数据
        BufferedReader reader = request.getReader();
        String s1 = reader.readLine();


        if (TeacherHelp.getStudents() == null) {
            mybatisImp mybatisImp = new mybatisImp();
            //获取教师账号id
            HttpSession session = request.getSession();
            Count user = (Count) session.getAttribute("user");
            //得到教师id
            Teacher teacher = mybatisImp.selectTeachInfoByCountId(user.getId());
            //通过教师id查询学生信息
            List<StuInfo_Tea> stuInfos = mybatisImp.selectStuInfo(teacher.getId());
            TeacherHelp.setStudents(stuInfos);
            mybatisImp.close();
        }
        //打包学生信息
        if ("info".equals(s1)) {
            String s = JSON.toJSONString(TeacherHelp.getStudents());
            response.getWriter().write(s);
        } else {

            String s = JSON.toJSONString(TeacherHelp.getStudents());
            response.getWriter().write(s);

        }
    }
}
