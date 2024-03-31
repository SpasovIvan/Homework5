<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 27.03.2024
  Time: 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Text</title>
</head>
<body>
<h1>${count}</h1>
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            String cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
            out.println(cookieName + ":\"" + cookieValue + "\"<br>");
        }
    }
%>
<h1>${URLDecoder.decode(cookie.cookie12.value, "UTF-8")}</h1>
</body>
</html>
