<%--
  Created by IntelliJ IDEA.
  User: kzertt。
  Date: 2022/3/23
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">

</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form  method="post" id="form">
        <h1 id="loginMsg">登 录</h1>
        <%--显示登录失败或注册成功的消息--%>
        <div id="errorMsg">${register_msg} ${login_msg}</div>
        <p>账号 :<input id="username" name="username" type="text" value= "${cookie.username.value}" ></p>

        <p>密码 :<input id="password" name="password" type="password" value = "${cookie.password.value}" ></p>
        <p>Remember:<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input id="submitbtn" type="button" class="button" value="登录">
            <%--<input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;--%>
        </div>
        <div class="textright">
            <a href="register.jsp" class="al">没有账号？点击注册</a>
        </div>
    </form>
</div>

<%--导入axios文件--%>
<script src = "js/axios-0.18.0.js"></script>

<script>
    document.getElementById("submitbtn").onclick = function () {
        //获取用户填入的数据
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
        let remembercheck = document.getElementById("remember");
        //判断用户是否填选记住
        if(remembercheck.checked){
            var remember = "1";
        }else{
            remember = "0";
        }
        //将用户信息设置为json
        var count = {
            username : "",
            password : "",
            remember : "",
        };
        count.username = username;
        count.password = password;
        count.remember = remember;
        axios({
            method:"post",
            url:"/web/loginServlet",
            data: {
                count:count
            }
        }).then(function (resp) {
            if(resp.data == "success"){
                location.href = "http://localhost:8080/web/main.jsp";
            }else{
                document.getElementById("errorMsg").innerHTML = resp.data;
            }
        })
    }
</script>
<%--<script src = "lib/jquery-1.11.3.min.js"></script>
<script>

    $(document).ready(function (){

        $("#submitbtn").bind("click",function (){

            let username =  $.trim($("#username").val());
            let password =  $.trim($("#password").val());

            if(username.length == 0 || password.length == 0){
                alert("用户名或者密码为空");
                $("#username").focus();
                return ;
            }
        });

    });


    document.getElementById("submitbtn").onclick = function (){
        //获取用户填入的数据
        let username = document.getElementById("username").value.trim();
        let password = document.getElementById("password").value.trim();
        let remember = document.getElementById("remember").value;
        //将用户信息设置为json
        let count = {
            username : "",
            password : "",
            remember : "",
        };
        count.username = username;
        count.password = password;
        count.remember = remember;

        $.ajax({
            url:"http://localhost:8080/web/loginServlet",
            method:"post",
            dataType:"json",
            data:count,
            success :function (data) {
                if("success" == data.msg){
                    location.href = "http://localhost:8080/web/main.jsp";
                }else{
                    document.getElementById("errorMsg").innerHTML = data.msg;
                }
            },
            error:function () {

            }
        });
    }
</script>--%>
</body>
</html>
