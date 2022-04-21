package web; /**
 * @auther:Florence
 * @date:2022/03/24/11:31
 */

import Service.Imp.mybatisImp;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/a/registcheckServlet")
public class RegistcheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         *
         * ajax技术
         */

        //1.设置字符编码格式，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //2.给游览器设置响应数据为何种类型，
        response.setContentType("text/html;charset=utf-8");

        mybatisImp mybatisImp = new mybatisImp();
        String username = request.getParameter("username");

        //获取session中code_err数值——图片中的值
        HttpSession session = request.getSession();
        String checkCode = (String)session.getAttribute("checkCode");


        //System.out.println(checkCode +" registerjsp " + request.getAttribute("checkCode")); 前者可以输出值而后者为null
        //String code_err = request.getParameter("code_err");

        //判断用户填写的验证码是否正确
        /*boolean codeflag = false;
        if(checkCode.equals(code_err))
            codeflag = true;*/

        int code = 0;
        String msg = "";
        //标记用户名是否可以被注册
        boolean flag = false;
        //判断用户填写的数据是否符合规范
        if(username.length() > 5 && username.length() < 13)
        {
            //判断用户填写的用户信息是否被占用
            if( mybatisImp.selectByStuUserId(username) != null ){
                msg = "用户名已被注册!!";
                code = -1;
            }else{
                msg = "用户名可以注册!!";
                flag = true;
                code = 1;
            }
        }else if("".equals(username)){
            //数据为空时的情况
            code = 0;
        }
        else{
            //用户填写不规范的情况
            code = -1;
            msg = "用户名不规范！";
        }
        //将信息打包成json
        JSONObject result = new JSONObject();
        result.put("code",code);
        result.put("msg",msg);
        result.put("flag",flag);
        //result.put("codeflag",codeflag);
        //发送到页面
        PrintWriter writer = response.getWriter();
        writer.write(result.toString());
        //关闭流
        writer.close();
        mybatisImp.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
