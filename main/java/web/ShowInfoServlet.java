package web; /**
 * @auther:Florence
 * @date:2022/04/17/12:34
 */
/**
 * *****通过账号id获取用户的基本信息
 * */
import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.Count;
import pojo.Teacher;
import pojo.TeacherHelp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/a/showInfoServlet")
public class ShowInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mybatisImp mybatisImp = new mybatisImp();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Teacher teacher = null;
        //获取账号信息,得到账号id
        HttpSession session = request.getSession();
        Count user = (Count)session.getAttribute("user");

        //查找用户信息
        if("tea".equals(user.getPeopleType())){
            //用户为教师
            //通过账号id查询教师信息
            teacher = mybatisImp.selectTeachInfoByCountId(user.getId());
            if(teacher == null){
                writer.write("fail");
                return ;
            }else{
                TeacherHelp.setTeacher(teacher);
            }
        }else{
            //用户为学生
            //通过账号id查询学生信息
        }
        //将用户信息返回

        writer.write(JSON.toJSONString(teacher));
        writer.close();
        mybatisImp.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
