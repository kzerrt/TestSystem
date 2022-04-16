package Filter; /**
 * @auther:Florence
 * @date:2022/03/30/14:48
 */

import pojo.Count;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*@WebFilter("/*")*/
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        //转换request类型
        HttpServletRequest req = (HttpServletRequest) request;
        //获取session
        HttpSession session = req.getSession();

        //获取用户对象

        Count user = (Count)session.getAttribute("user");
        //拦截器会将页面有关的所有文件拦截
        String[] urls =
                {"/register.jsp","/login.html","/element-ui","/css","/js","/imgs","/loginServlet",
                        "/registcheckServlet","registerServlet","checkCodeServlet","getUserServlet","/Teacher"};
        String url = req.getRequestURL().toString();
        for(String u:urls){
            if(url.contains(u)){
                //放行
                chain.doFilter(request, response);

                return ;
            }
        }

        if(user == null)
        {
            req.getRequestDispatcher("/login.html").forward(req,response);
            //重定向 无法使用重定向，一单使用重定向网页将不能正常跳转
            /*HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("/web/login.jsp");*/
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


}
