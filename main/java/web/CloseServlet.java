package web; /**
 * @auther:Florence
 * @date:2022/04/21/15:44
 */
/****
 * ****将用户移除
 * */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/a/closeServlet")
public class CloseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户账号信息
        HttpSession session = request.getSession();

        if(session.getAttribute("user") != null) {
            //将用户移除
            session.removeAttribute("user");
            request.getRequestDispatcher("/web/login.html").forward(request,response);
        }else
            return ;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
