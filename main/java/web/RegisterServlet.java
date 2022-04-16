package web; /**
 * @auther:Florence
 * @date:2022/03/17/12:00
 */

import Service.Imp.mybatisImp;
import pojo.Count;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        mybatisImp mybatisImp = new mybatisImp();
        //获取用户输入的信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        //获取session和正确验证码
        HttpSession session = request.getSession();
        String correctCode = (String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");
        //判断用户输入的验证码是否正确
        if (!correctCode.equalsIgnoreCase(checkCode)) {//忽略大小写
            //验证码错误
            request.setAttribute("checkCode_err", "验证码错误");
            //注册失败，跳转到注册页面
            request.setAttribute("register_msg", "注册失败");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }


        Count user = new Count(username, password);

        mybatisImp.add(user);
        mybatisImp.commit();
        mybatisImp.close();
        //注册成功，进行转发操作
        request.setAttribute("register_msg", "注册成功");
        request.getRequestDispatcher("/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
}
