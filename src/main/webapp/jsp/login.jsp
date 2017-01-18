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
    <title>登录</title>
</head>
    <body>
    <p style="color: red;font-weight: 900">${msg }</p>
        <form action="/LoginServlet" METHOD="post">
            用户名：<input type="text" name="username" value="${user.username }"><br>
            密&nbsp;码：<input type="password" name="password" value="${user.password }"><br>
            验证码：<input type="text" name="verifyCode" size="2" value="${user.verifyCode }">
            <img id="vCode" src="<c:url value='/VerifyCodeServlet'/>" border="2"/>
            <a href="javascript:_change()" style="font-size:14px;">看不清，换一张</a><br>
            <input type="submit" value="登录">
    </form>

</body>
    <script type="text/javascript">
        function _change() {
            var img = document.getElementById("vCode");
            //保证每次验证码一样，加一个日期
            img.src = "<c:url value='/VerifyCodeServlet'/>"+new Date().getTime();
        }
    </script>
</html>
