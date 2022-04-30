package web; /**
 * @auther:Florence
 * @date:2022/04/28/20:17
 */
/**
 * 返回用户对话列表
 * */

import Service.Imp.mybatisImp;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pojo.ConverP;
import pojo.Count;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/a/conversationServlet")
public class ConversationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");

        mybatisImp mybatisImp = new mybatisImp();
        String msg = "fail";
        List<ConverP> names = null;
        HttpSession session = request.getSession();
        Count user = (Count) session.getAttribute("user");
        // 获取用户信息
        // 获取用户将有所关联的用户
        if("stu".equals(user.getPeopleType())){
            names = mybatisImp.selectConversationByStuId(user.getId());
        }else{
            names = mybatisImp.selectConversationByTeaId(user.getId());
        }

        if(names == null){
            mybatisImp.close();
            response.getWriter().write(msg);
        }
        mybatisImp.close();
        //将用户返会

        msg = JSON.toJSONString(names);
        response.getWriter().write(msg);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
