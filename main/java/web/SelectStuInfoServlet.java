package web; /**
 * @auther:Florence
 * @date:2022/04/19/14:12
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pojo.StuInfo_Tea;
import pojo.TeacherHelp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectStuInfoServlet")
public class SelectStuInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StuInfo_Tea> students = new ArrayList<>();
        //获取用户搜索条件
        request.setCharacterEncoding("utf-8");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        JSONObject jsonObject = JSON.parseObject(s);
        int code = jsonObject.getObject("code", int.class);
        String name = jsonObject.getObject("name", String.class);
        String dept = jsonObject.getObject("dept", String.class);
        String sex = jsonObject.getObject("sex", String.class);
        String[] si = {name,dept,sex};

        int a = 0;

        for (int i = 0; i < 3; i++) {
            if(!"".equals(si[i])){
                a++;
            }
        }
        //通过条件筛选学生
        for (StuInfo_Tea stu :
                TeacherHelp.getStudents()) {

            if(stu.getCode() == code){

            }
            if(stu.getSex().equals(sex)){

            }
            if(stu.getName().equals(name)){

            }
            if(stu.getDept().equals(dept)){

            }
        }

        //将筛选后的学生返回

    }

}
