package web.Teach; /**
 * @auther:Florence
 * @date:2022/04/19/14:12
 */
/****
 *
 *******************条件查询学生信息
 *
 * */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.Count;
import pojo.StuInfo_Tea;
import pojo.Teacher;
import pojo.TeacherHelp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/a/selectStuInfoServlet")
public class SelectStuInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mybatisImp mybatisImp = new mybatisImp();
        String result = "fail";
        //获取用户搜索条件
        request.setCharacterEncoding("utf-8");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        StuInfo_Tea stuInfo = JSON.parseObject(s, StuInfo_Tea.class);

        HttpSession session = request.getSession();
        Count user = (Count) session.getAttribute("user");
        //得到教师id
        Teacher teacher = mybatisImp.selectTeachInfoByCountId(user.getId());
        List<StuInfo_Tea> stus = mybatisImp.selectInfoByCondition(stuInfo, teacher.getId());
        //将筛选后的学生返回
        if(stus == null){
            result = "fail";
        }else{
            result = JSON.toJSONString(stus);
        }
        response.getWriter().write(result);
        mybatisImp.close();
    }


}
