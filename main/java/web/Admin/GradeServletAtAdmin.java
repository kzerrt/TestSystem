package web.Admin; /**
 * @auther:Florence
 * @date:2022/04/27/13:16
 */

import Service.Imp.AdminImp;
import com.alibaba.fastjson.JSON;
import pojo.StuInfo_Tea;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/a/gradeServletAtAdmin")
public class GradeServletAtAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        AdminImp adminImp = new AdminImp();
        //将学生成绩信息查询
        List<StuInfo_Tea> stu = adminImp.showStuGrade();
        //将结果返回
        String s = JSON.toJSONString(stu);
        adminImp.close();
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
