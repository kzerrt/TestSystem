package web; /**
 * @auther:Florence
 * @date:2022/04/29/10:35
 */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.ConverP;
import pojo.Count;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/a/sendMsgServlet")
public class SendMsgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        mybatisImp mybatisImp = new mybatisImp();
        HttpSession session = request.getSession();
        Count user = (Count) session.getAttribute("user");
        String msg = "fail";
        boolean flag = false;
        //获取用户输入的信息
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        ConverP peo = JSON.parseObject(s, ConverP.class);


        //将信息处理存入数据库
        try {
            if ("stu".equals(user.getPeopleType())) {
                flag = mybatisImp.sendMsg(mybatisImp.selectStuIdByCountId(user.getId()), peo.getId(), peo.getWord(),"stu");
            } else {
                flag = mybatisImp.sendMsg(peo.getId(), mybatisImp.selectStuIdByCountId(user.getId()), peo.getWord(),"tea");
            }
        }catch (Exception e){
            mybatisImp.roll();
            e.printStackTrace();
        }
        //将存入数据库的结果进行处理
        if (flag) {
            mybatisImp.commit();
            msg = "success";
        }
        //发送结果
        mybatisImp.close();
        response.getWriter().write(msg);
    }
}
