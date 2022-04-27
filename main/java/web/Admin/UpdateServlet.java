package web.Admin; /**
 * @auther:Florence
 * @date:2022/04/26/15:26
 */

import Service.Imp.AdminImp;
import com.alibaba.fastjson.JSON;
import pojo.StudentInfo;
import pojo.Teacher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/a/updateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        AdminImp admin = new AdminImp();
        StudentInfo student = null;
        Teacher teacher = null;
        String msg = "fail";
        boolean b = false;
        //获取用户修改的学生信息
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        //判断修改用户类型
        String name = request.getParameter("name");



        //修改信息
        try {
            if("student".equals(name)) {
                student = JSON.parseObject(s, StudentInfo.class);
                b = admin.updateStu(student);
            }
        else {
                teacher = JSON.parseObject(s, Teacher.class);
                b = admin.updateTea(teacher);
            }

        }catch (Exception e){
            admin.roll();
            e.printStackTrace();
        }
        //将修改结果发送至页面
        if(b){
            admin.commit();
            msg = "success";
        }

        response.getWriter().write(msg);
        admin.close();

    }
}
