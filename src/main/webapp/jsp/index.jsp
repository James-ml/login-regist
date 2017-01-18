<%--
  Created by IntelliJ IDEA.
  User: Jims
  Date: 2017/1/17
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
    <body>
    <h1>欢迎登录本系统</h1>
        <c:choose>
            <c:when test="${empty sessionScope.sessionUser }">
                你还没有登录
            </c:when>
            <c:otherwise>
                用户名：${sessionScope.sessionUser.username }
                <a href="<c:url value='/QuitServlet'/>">退出</a>
            </c:otherwise>
        </c:choose>
    </body>
</html>
