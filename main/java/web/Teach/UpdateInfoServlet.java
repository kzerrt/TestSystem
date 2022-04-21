package web.Teach; /**
 * @auther:Florence
 * @date:2022/04/17/13:36
 */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
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

        mybatisImp mybatisImp = new mybatisImp();
        //获取用户修改信息
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        System.out.println(s);

        Teacher teacher = JSON.parseObject(s, Teacher.class);

        System.out.println(teacher.toString());

        //使用sql将信息修改
        boolean flag = mybatisImp.updateTeachInfo(teacher);
        System.out.println("flag" + flag);
        mybatisImp.commit();
        mybatisImp.close();
        //返回修改后的结果
        String msg;
        if(flag){

            msg = "success";
        }else{
            msg = "fail";
        }
        System.out.println(msg);
        response.getWriter().write(msg);
        response.getWriter().close();
    }
}
