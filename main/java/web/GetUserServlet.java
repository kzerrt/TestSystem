package web; /**
 * @auther:Florence
 * @date:2022/04/10/16:37
 */

import com.alibaba.fastjson.JSON;
import pojo.Count_Json;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/getUserServlet")
public class GetUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Cookie[] cookies = request.getCookies();
        Count_Json count = new Count_Json();
        for (Cookie c :
                cookies) {
            if("username".equals(c.getName())){
                count.setUsername(c.getValue());
            }else if("password".equals(c.getName())){
                count.setPassword(c.getValue());
            }else if("radio".equals(c.getName())){
                count.setRadio(c.getValue());
            }else if("remember".equals(c.getName())){
                count.setRemember(Boolean.parseBoolean(c.getValue()));
            }

        }
        response.setContentType("application/json;charset=utf-8");
        String s = JSON.toJSONString(count);

        response.getWriter().write(s);
        response.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request,response);
    }
}
