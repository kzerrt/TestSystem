package web.Teach; /**
 * @auther:Florence
 * @date:2022/04/20/15:16
 */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pojo.Count;
import pojo.Teacher;
import pojo.TeacherHelp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/a/updateGradeServlet")
public class UpdateGradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取用户修改的参数
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        System.out.println(s);

        //修改成绩
        mybatisImp mybatisImp = new mybatisImp();
        boolean flag = false;
        try {
            JSONObject jsonObject = JSON.parseObject(s);
            Integer code = jsonObject.getObject("code", int.class);
            String course = jsonObject.getObject("course", String.class);
            Integer grade = jsonObject.getObject("grade", int.class);
            System.out.println(code);
            System.out.println(course);
            System.out.println(grade);
            if (TeacherHelp.getTeacher() != null) {
                flag = mybatisImp.updateGrade(grade, course, code, TeacherHelp.getTeacher().getId());
            }
            else{
                HttpSession session = request.getSession();
                Count user = (Count) session.getAttribute("user");
                if(user.getPeopleType().equals("tea")) {
                    Teacher teacher = mybatisImp.selectTeachInfoByCountId(user.getId());
                    flag = mybatisImp.updateGrade(grade, course, code, teacher.getId());
                    TeacherHelp.setTeacher(teacher);
                }
            }
            if(flag){
                mybatisImp.commit();
                response.getWriter().write("success");
            }else
                response.getWriter().write("fail");
        }catch(Exception e){
            response.getWriter().write("fail");
            mybatisImp.roll();
            e.printStackTrace();
        }finally {
            mybatisImp.close();
        }


    }
}
