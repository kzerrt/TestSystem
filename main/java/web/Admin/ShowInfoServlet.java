package web.Admin; /**
 * @auther:Florence
 * @date:2022/04/26/13:39
 */

import Service.Imp.AdminImp;
import com.alibaba.fastjson.JSON;
import pojo.StudentInfo;
import pojo.Teacher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/a/showInfoServlet")
public class ShowInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminImp admin = new AdminImp();
        List<StudentInfo> studentInfos = null;
        List<Teacher> teachers = null;
        String s = "fail";
        //设置编码格式
        response.setContentType("text/json;charset=utf-8");
        //获取信息类型
        String name = request.getParameter("name");

        try{
            if("student".equals(name)){
                //从数据库中获得学生信息
                studentInfos = admin.showStuInfo();
                if(studentInfos == null ){
                    response.getWriter().write("fail");
                    admin.close();
                    return ;
                }
                //将信息打包发送到页面
                s = JSON.toJSONString(studentInfos);
            }else{
                teachers = admin.showTeaInfo();
                if(teachers == null ){
                    response.getWriter().write("fail");
                    admin.close();
                    return ;
                }
                //将信息打包发送到页面
                s = JSON.toJSONString(teachers);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.getWriter().write(s);
        admin.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doGet(request,response);
    }
}
