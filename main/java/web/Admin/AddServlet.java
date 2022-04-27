package web.Admin;
/**
 * @auther:Florence
 * @date:2022/04/26/19:12
 */

import Service.Imp.AdminImp;
import com.alibaba.fastjson.JSON;
import pojo.StudentInfo;
import pojo.Teacher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/a/addServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        AdminImp adminImp = new AdminImp();
        StudentInfo student = null;
        Teacher teacher = null;
        boolean b = false;
        String msg = "fail";
        //获取用户添加信息
        String s = request.getReader().readLine();

        //判断添加用户类型
        String name = request.getParameter("name");

        //添加用户
        try {
            if ("student".equals(name)) {
                student = JSON.parseObject(s, StudentInfo.class);
                if (student == null) {
                    response.getWriter().write(msg);
                    adminImp.close();
                    return;
                }
                b = adminImp.addStudent(student);
            } else {
                teacher = JSON.parseObject(s, Teacher.class);
                if (teacher == null) {
                    response.getWriter().write(msg);
                    adminImp.close();
                    return;
                }
                b = adminImp.addTeacher(teacher);
            }
        } catch (Exception e) {
            adminImp.roll();
            e.printStackTrace();
        } finally {
            if (b) {
                adminImp.commit();
                msg = "success";
            }
            adminImp.close();
        }

        //将结果返回
        response.getWriter().write(msg);
    }
}
