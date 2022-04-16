package web; /**
 * @auther:Florence
 * @date:2022/04/16/16:28
 */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.Count;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg;
        mybatisImp mybatisImp = new mybatisImp();

        response.setContentType("application/json;charset=utf-8");
        //得到用户的账号
        HttpSession session = request.getSession();
        Count user = (Count)session.getAttribute("user");

        System.out.println(user.toString());

        if(user == null){
            msg = "账户问题，请重试";
            response.getWriter().write(msg);
            response.getWriter().close();
            return ;
        }
        //获取用户修改的密码
        BufferedReader reader = request.getReader();
        String s = reader.readLine();


        System.out.println( "json" + s);

        String password = JSON.parseObject(s, String.class);

        //通过sql语句将密码修改
        boolean flag = mybatisImp.updatePassword(password,user.getUsername());
        //返回修改结果
        if(flag){
            msg = "success";
        }else
            msg = "fail";

        //将现存账户从Session中删除
        session.removeAttribute("user");
        //重定向到登录界面
        response.sendRedirect("http://localhost:8080/web/login.html");
        response.getWriter().write(msg);
        response.getWriter().close();
        mybatisImp.commit();
        mybatisImp.close();
    }
}
