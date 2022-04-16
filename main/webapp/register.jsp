<%--
  Created by IntelliJ IDEA.
  User: kzertt。
  Date: 2022/3/24
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
    <script src="lib/jquery-1.11.3.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#username").bind("blur", function () {
                var username = $.trim($("#username").val());
                var code_err = $.trim($("code_err").val());
                //ajax("registcheckServlet","post",username,checkusername(data))
                $.ajax({
                    url: "registcheckServlet",//该页面的数据提交到后台的url进行处理
                    type: "post",//发送的方法
                    dataType: "json",//发送数据的具体类型
                    data: { //给服务端需要传递的数据
                        username: username,
                    },
                    success: function (data) {//data我服务端返回的数据，名称可随意命名
                        if(data.code < 0){
                            $("#username_alert").css("color", "red");
                        }else if(data.code == 0){

                        }
                        else{
                            $("#username_alert").css("color", "green");
                            usernameflag = data.flag
                        }
                        $("#username_alert").html(data.msg);

                    },
                    error:function (){
                        alert("error");
                    }
                });
            });
        });
    </script>
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/web/registerServlet" method="post">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <%--显示注册失败后的信息--%>
                    <span id="username_alert" class="err_msg" >${register_msg}</span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none ">密码格式有误</span>
                </td>
            </tr>

            <tr>
                <td>验证码</td>
                <td class = "inputs">
                    <input name = "checkCode" type="text" id = "checkCode" >
                    <img id="checkCodeImg" src="/web/checkCodeServlet">
                    <a href = "#" id="changeImg">看不清?</a>
                    <br>
                    <%--显示验证码错误信息--%>
                    <span id="code_err" class="err_msg"  >${checkCode_err}</span>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script>

    var usernameflag = false;
    var passwordflag = false;
    /*var usernameinput = document.getElementById("username");
    usernameinput.onblur = function () {
        var username = usernameinput.value.trim();
        /!*if (username.length >=6 && username.length<=12){
            usernameflag = true;
            document.getElementById("username_err").style.display = 'none';
        }else
        {
            document.getElementById("username_err").style.display = '';
        }*!/
    }*/


    var passwordinput = document.getElementById("password");
    passwordinput.onblur = function () {
        var password = passwordinput.value.trim();

        if (password.length >=6 && password.length<=12){
            passwordflag = true;
            document.getElementById("password_err").style.display = 'none';
        }else if(""===password) {
            document.getElementById("password_err").style.display = 'none';
        }else
        {
            document.getElementById("password_err").style.display = '';
        }

    }
    //表单验证，当所以条件都符合是可以点击注册按钮
    var regfrom = document.getElementById("reg-form");
    regfrom.onsubmit = function () {

        var flag = usernameflag && passwordflag;
        return flag;
    }

    var changeImg = document.getElementById("changeImg");
    changeImg.onclick= function(){

        //浏览器？服务器？会缓存图片路径，当路径相同时，不会做出更改
        //通过在链接后加时间戳的方式，使链接不存在重复的情况，实现可以重复点击更换图片
        document.getElementById("checkCodeImg").src = "/web/checkCodeServlet?" + new Date().getMilliseconds();
    }

</script>
</body>
</html>