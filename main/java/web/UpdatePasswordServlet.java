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

@WebServlet("/a/updateServlet")
public class UpdatePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg;
        mybatisImp mybatisImp = new mybatisImp();
        boolean flag;

        response.setContentType("application/json;charset=utf-8");
        //得到用户的账号
        HttpSession session = request.getSession();
        Count user = (Count)session.getAttribute("user");


        if(user == null){
            msg = "账户问题，请重试";
            response.getWriter().write(msg);
            response.getWriter().close();
            mybatisImp.close();
            return ;
        }
        //获取用户修改的密码
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        String password = JSON.parseObject(s, String.class);

        if("tea".equals(user.getPeopleType())) {
            //通过sql语句将密码修改
            flag = mybatisImp.updatePassword(password, user.getUsername());
        }else{
            flag = mybatisImp.updatePasByStu(password, user.getUsername());
        }
        //返回修改结果
        if(flag){
            msg = "success";
            mybatisImp.commit();
        }else {
            msg = "fail";

        }
        System.out.println(msg);
        response.getWriter().write(msg);
        response.getWriter().close();
        mybatisImp.close();

    }
}
