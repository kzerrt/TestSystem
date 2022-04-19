package web; /**
 * @auther:Florence
 * @date:2022/04/19/9:11
 */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.Count;
import pojo.StuInfo_Tea;
import pojo.Teacher;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/showStuInfoByTeachServlet")
public class ShowStuInfoByTeachServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mybatisImp mybatisImp = new mybatisImp();
        //获取教师账号id
        HttpSession session = request.getSession();
        Count user = (Count) session.getAttribute("user");
        //得到教师id
        Teacher teacher = mybatisImp.selectTeachInfoByCountId(user.getId());
        //通过教师id查询学生信息
        List<StuInfo_Tea> stuInfos = mybatisImp.selectStuInfo(teacher.getId());
        //打包学生信息
        response.setContentType("text/json;charset=utf-8");
        String s = JSON.toJSONString(stuInfos);
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
