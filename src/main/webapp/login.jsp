<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(function () { //页面加载完毕后执行

            if (window.top != window) {
                window.top.location = window.location;
            }

            //页面加载完毕时清空 文本框中的值 （input标签中的 value 属性）
            $("loginAct").val("")

            //页面加载完毕后自动获取焦点
            $("#loginAct").focus();

            //为submitBtn绑定事件
            $("#submitBtn").click(function () {
                login();
            })

            //为窗口绑定键盘敲击事件
            //敲回车键也能登录
            $(window).keydown(function (event) {
                // alert(event.keyCode)
                if (event.keyCode == 13) {
                    login();
                }
            })
        })

        //登录验证方法
        //注意！函数推荐写在 $(function () 外面
        function login() {
            //获取输入的 用户名与密码 的值
            // var loginAct = $("#loginAct").val();
            // var loginPwd = $("#loginPwd").val();

            //问题一：输入时 前后可能会带 空格 需要使用trim（）方法去除
            var loginAct = $.trim($("#loginAct").val());
            var loginPwd = $.trim($("#loginPwd").val());

            //判断账号密码不为空 就注入消息到msg中
            if (loginAct == "" || loginPwd == "") {
                $("#msg").html("账号与密码不能为空")
            }

            $.ajax({

                url: "settings/user/login.do", //
                data: {
                    "loginAct": loginAct,
                    "loginPwd": loginPwd
                },
                type: "post",
                dataType: "json",
                success: function (data) {

                    //首先需要验证 是否登录成功
                    if (data.success) {//sucess 判断输入的用户名与密码是否正确
                        //跳转到欢迎页面
                        window.location.href = "workbench/index.jsp";
                    } else {
                        //登录失败，给相应内容框中 提示相关错误
                        $("#msg").html(data.msg);// msg 登录失败后 后端传回来的错误信息
                    }


                }
            })
        }

    </script>

</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form action="workbench/index.jsp" class="form-horizontal" role="form">
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input class="form-control" type="text" placeholder="用户名" id="loginAct">
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input class="form-control" type="password" placeholder="密码" id="loginPwd">
                </div>
                <div class="checkbox" style="position: relative;top: 30px; left: 10px;">

                    <span id="msg" style="color: red"></span>

                </div>
                <%--				按钮在form表单中默认（不写类型） type 就是submit  --%>
                <button type="button" id="submitBtn" class="btn btn-primary btn-lg btn-block"
                        style="width: 350px; position: relative;top: 45px;">登录
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>