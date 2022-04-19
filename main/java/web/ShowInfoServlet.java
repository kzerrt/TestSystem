package web; /**
 * @auther:Florence
 * @date:2022/04/17/12:34
 */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.Count;
import pojo.Teacher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/showInfoServlet")
public class ShowInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mybatisImp mybatisImp = new mybatisImp();
        Teacher teacher = null;
        //获取账号信息,得到账号id
        HttpSession session = request.getSession();
        Count user = (Count)session.getAttribute("user");

        //查找用户信息
        if("tea".equals(user.getPeopleType())){
            //用户为教师
            //通过账号id查询教师信息
            teacher = mybatisImp.selectTeachInfoByCountId(user.getId());
        }else{
            //用户为学生
            //通过账号id查询学生信息
        }
        //将用户信息返回
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if(teacher == null){
            writer.write("fail");
        }
        writer.write(JSON.toJSONString(teacher));
        writer.close();
        mybatisImp.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
