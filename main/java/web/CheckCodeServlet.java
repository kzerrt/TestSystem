package web; /**
 * @auther:Florence
 * @date:2022/03/28/22:04
 */

import util.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得response的输出流
        ServletOutputStream outputStream = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);
        //System.out.println( checkCode+"checkCodejsp");
        //获取会话
        HttpSession session = request.getSession();
        //设置会话参数将会话转到register.jsp中
        session.setAttribute("checkCode",checkCode);

        /**
         * 为什么同样的语句使用不同方式造成session有值而request就没有值
         * 但在jsp中使用el表达式就可以将request中设置的参数拿出来
         * */
       /* String code = request.getParameter("checkCode");
        //判断用户是否填写正确验证码
        if(checkCode.equals(code)){
            //填写正确
            
        }else{
            //填写失败

        }*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request,response);
    }
}
