package web; /**
 * @auther:Florence
 * @date:2022/04/19/9:11
 */
/***
 * **在教师端得到学生成绩信息
 * */

import com.alibaba.fastjson.JSON;
import pojo.TeacherHelp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/showStuInfoByTeachServlet")
public class ShowStuInfoByTeachServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(TeacherHelp.getStudents() == null)
            return ;
        //打包学生信息
        response.setContentType("text/json;charset=utf-8");
        String s = JSON.toJSONString(TeacherHelp.getStudents());
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
