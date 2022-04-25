package web.Student; /**
 * @auther:Florence
 * @date:2022/04/25/12:49
 */

/**
 *
 * *****通过学生id获取到学生相关成绩信息
 * */
import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.Count;
import pojo.StuGrade;
import pojo.StudentInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/a/showGradeServlet")
public class ShowGradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mybatisImp mybatisImp = new mybatisImp();
        response.setContentType("text/json;charset=utf-8");
        String msg = "fail";
        //获取学生账号信息
        HttpSession session = request.getSession();
        Count user = (Count) session.getAttribute("user");
        if(!"stu".equals(user.getPeopleType())){
            mybatisImp.close();
            response.getWriter().write(msg);
            return ;
        }

        //根据账号信息得到学生id
        StudentInfo stuInfo = mybatisImp.selectStuInfoByCountId(user.getId());

        //通过学生id获得相关课程的信息
        List<StuGrade> stuGrades = mybatisImp.showGradeByStuId(stuInfo.getId());
        //将这些信息返回
        if(stuGrades == null){
            mybatisImp.close();
            response.getWriter().write(msg);
            return ;
        }
        mybatisImp.close();
        msg = JSON.toJSONString(stuGrades);
        response.getWriter().write(msg);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
