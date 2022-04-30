package web; /**
 * @auther:Florence
 * @date:2022/04/29/8:56
 */

/**
 * 将某个人的聊天记录记录
 * */
import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import pojo.ConverP;
import pojo.Count;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/a/conTalkServlet")
public class ConverTalkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");

        mybatisImp mybatisImp = new mybatisImp();
        String msg = "fail";
        List<ConverP> Talks = null;
        ConverP po = new ConverP();
        HttpSession session = request.getSession();
        Count user = (Count) session.getAttribute("user");
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        //判断用户类型
        if("stu".equals(user.getPeopleType())){
            Talks = mybatisImp.showTalk(Integer.parseInt(s), mybatisImp.selectStuIdByCountId(user.getId()));
        }else{
            Talks = mybatisImp.showTalk(mybatisImp.selectTeaIdByCountId(user.getId()), Integer.parseInt(s));
        }
        if(Talks == null){
            mybatisImp.close();
            response.getWriter().write(msg);
            return ;
        }
        for (ConverP p :
                Talks) {
            System.out.println(p.getWord());
        }
        String json = JSON.toJSONString(Talks);
        mybatisImp.close();
        response.getWriter().write(json);
    }
}
