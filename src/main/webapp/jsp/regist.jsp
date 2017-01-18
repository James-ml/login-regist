<%--
  Created by IntelliJ IDEA.
  User: Jims
  Date: 2017/1/17
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册</title>
</head>
    <body>
        <p style="color: red;font-weight: 900">${msg }</p>
        <form action="/RegistServlet" method="post">
            用户名：<input type="text" name="username" value="${user.username }">${errors.username }<br>
            密&nbsp;码：<input type="password" name="password" value="${user.password }">${errors.password }<br>
            确认密码：<input type="password" name="rePassword" value="${user.rePassword }">${errors.rePassword }<br>
            验证码：<input type="text" name="verifyCode"size="3" value="${user.verifyCode }">
            <img id="vCode" src="<c:url value='/VerifyCodeServlet?name=rCode'/>" border="2"/>
            <a href="javascript:_change()" style="font-size: 14px;">看不清，换一张</a>${errors.verifyCode }
            <%--<p style="color: red;font-weight: 900">${mag }</p>--%>
            <br/>
            <input type="submit" value="注册">
        </form>
    </body>
    <script type="text/javascript">
        function _change() {
            var img = document.getElementById("vCode");
            img.src = "<c:url value='/VerifyCodeServlet?name=rCode&'/>" + new Date().getTime();
        }
    </script>
</html>
