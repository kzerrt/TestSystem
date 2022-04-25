package web;

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSONObject;
import pojo.Count_Json;
import pojo.Count;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * @auther:Florence
 * @date:2022/03/16/20:22
 */
@WebServlet("/a/loginServlet")
public class LoginServelt extends HttpServlet {

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置获取编码格式
        req.setCharacterEncoding("utf-8");
        mybatisImp my = new mybatisImp();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        User user = my.selectbyusername(username);

        my.close();

        //获取对应的字符输出流格式
        resp.setContentType("text/html;charset=utf-8");

        //判断用户密码是否正确
        //当用户不存在时报空指针异常

        if(user == null){
            //存储错误信息到request中
            req.setAttribute("login_msg","用户名不存在!!!");
            //跳转到jsp
            req.getRequestDispatcher("/login.jsp").forward(req,resp);

            return ;
        }
        if(password.equals(user.getPassword()))
        {
            if("1".equals(remember)){//注意如果remember为空就会报异常所以要将常量写前
                //设置Cookie
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password",password);
                //设置Cookie生命时间
                c_username.setMaxAge(60 * 60);
                c_password.setMaxAge(60 * 60);
                resp.addCookie(c_username);
                resp.addCookie(c_password);
            }

            //获取Session类
            HttpSession session = req.getSession();
            //将session类中设置参数
            session.setAttribute("user",user);

            req.getRequestDispatcher("/main.jsp").forward(req,resp);
            //使用重定向转到网页
            //resp.sendRedirect("/web/main.jsp");
            //session.setAttribute("password",password); 如果是一个相同对象设置两边会产生什么影响impact
            //将对话重定向到其他页面
           // session.getAttribute("")

        }else{
            //存储错误信息到request中
            req.setAttribute("login_msg","用户名或密码错误");
            //跳转到jsp
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        mybatisImp my = new mybatisImp();
        String msg = "";
       //使用req.getParameter无法获得json对象.需要使用输入流来接受json对象
        //获得json请求
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        Count user = null;
        //将json对象进行转化
        JSONObject jsonObject = JSONObject.parseObject(s);
        Count_Json count = jsonObject.toJavaObject(Count_Json.class);


        String username = count.getUsername();
        String password = count.getPassword();
        String radio = count.getRadio();
        boolean remember = count.getRemember();


        System.out.println("username"+username);
        System.out.println(password);
        System.out.println(remember);

        //判断用户类型
        if("stu".equals(radio)){
            //从数据库中获取学生账号对象
            user = my.selectByStuUserId(username);
        }else{
            user = my.selectByTeaUserId(username);
        }

        //System.out.println(user.toString());
        //判断是否存在用户
        if(user == null)
        {
            msg = "用户名不存在，请注册";

            resp.getWriter().write(msg);
            resp.getWriter().close();
            my.close();
            return ;
        }else{
            user.setPeopleType(radio);
        }

        if(password.equals(user.getPassword())){
            if(remember){
                //设置Cookie
                Cookie c_username = new Cookie("username",user.getUsername());
                Cookie c_password = new Cookie("password", user.getPassword());
                Cookie c_radio = new Cookie("radio", user.getPeopleType());
                Cookie c_remember = new Cookie("remember", String.valueOf(count.getRemember()));

                //设置Cookie的存储时间
                c_username.setMaxAge(60*10);
                c_password.setMaxAge(60*10);
                c_radio.setMaxAge(60*10);
                c_remember.setMaxAge(60*10);

                //将Cookie添加到resp中
                resp.addCookie(c_username);
                resp.addCookie(c_password);
                resp.addCookie(c_radio);
                resp.addCookie(c_remember);

            }

            //设置session
            HttpSession session = req.getSession();
            session.setAttribute("user",user);

            if("stu".equals(radio))
                msg = "successStu";
            else
                msg = "successTea";
            //req.getRequestDispatcher("/main.jsp").forward(req,resp);
        }else{
            msg = "用户密码错误";
        }

        System.out.println(msg);

        resp.getWriter().write(msg);
        resp.getWriter().close();
        //关闭工具类
        my.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
