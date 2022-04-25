package web.Teach; /**
 * @auther:Florence
 * @date:2022/04/17/13:36
 */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.Count;
import pojo.StudentInfo;
import pojo.Teacher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/a/updateInfoServlet")
public class UpdateInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Count user = (Count) request.getSession().getAttribute("user");
        mybatisImp mybatisImp = new mybatisImp();
        Teacher teacher = null;
        StudentInfo studentInfo = null;
        boolean flag = false;
        String msg;


        //获取用户修改信息
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        //判断用户类型
        if("stu".equals(user.getPeopleType())){
            studentInfo = JSON.parseObject(s,StudentInfo.class);
            flag = mybatisImp.updateStuInfo(studentInfo);
        }else {
            teacher = JSON.parseObject(s, Teacher.class);
            //使用sql将信息修改
            flag = mybatisImp.updateTeachInfo(teacher);
        }


        //返回修改后的结果

        if(flag){
            mybatisImp.commit();
            msg = "success";
        }else{
            msg = "fail";
        }
        mybatisImp.close();
        response.getWriter().write(msg);
        response.getWriter().close();
    }
}
