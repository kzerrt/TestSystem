package web.Admin; /**
 * @auther:Florence
 * @date:2022/04/27/15:58
 */

import Service.Imp.AdminImp;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/a/updateGradeServletAtAdmin")
public class UpdateGradeServletAtAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String msg = "fail";
        AdminImp adminImp = new AdminImp();
        boolean flag = false;
        //获取用户修改的参数
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        //获取用户输入的数据
        JSONObject jsonObject = JSON.parseObject(s);
        Integer code = jsonObject.getObject("code", int.class);
        String course = jsonObject.getObject("course", String.class);
        Integer grade = jsonObject.getObject("grade", int.class);
        String teacher = jsonObject.getObject("teacher", String.class);
        //根据数据修改学生分数
        flag = adminImp.updateGrade(grade,course,code,teacher);
        if(flag){
            adminImp.commit();
            msg = "success";
        }
        //将结果返回到页面
        adminImp.close();
        response.getWriter().write(msg);
    }
}
