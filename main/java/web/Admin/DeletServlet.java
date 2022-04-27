package web.Admin; /**
 * @auther:Florence
 * @date:2022/04/26/15:57
 */

import Service.Imp.AdminImp;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/a/deleteServletAtAdmin")
public class DeletServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "fail";
        AdminImp admin = new AdminImp();
        boolean b = false;
        //获得信息
        String s = request.getReader().readLine();
        //判断删除用户类型
        String name = request.getParameter("name");

        if(s == null){
            response.getWriter().write(msg);
            admin.close();
            return ;
        }

        Integer code = JSON.parseObject(s, int.class);
        //根据信息删除学生信息
        try{
            if("student".equals(name))
                b = admin.deleteStu(code);
            else
                b = admin.deleteTea(code);
        }catch (Exception e){
            admin.roll();
            response.getWriter().write(msg);
            e.printStackTrace();
        }

        //返回结果
        if(b){
            msg = "success";
            admin.commit();
        }
        response.getWriter().write(msg);
        admin.close();
    }
}
