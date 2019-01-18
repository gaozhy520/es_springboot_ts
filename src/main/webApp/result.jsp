<%@ page pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        em {
            color: red;
            font-size: large;
            font-weight: bold;
        }
    </style>
</head>
<body>
<center>
    <h1>基于ES SpringBoot构建的唐诗检索系统</h1>
</center>

<c:forEach items="${map}" var="map">
    <c:if test="${map.key == 'pageCount'}">
        符合条件的结果的总页数: ${map.value}
    </c:if>
    <hr>
    <c:if test="${map.key == 'poetries'}">
        <c:forEach items="${map.value}" var="poetry">
            编号: ${poetry.id}  &nbsp; 诗名: ${poetry.title} &nbsp; 诗人: ${poetry.author} &nbsp; 内容: ${poetry.content}
            <br>
        </c:forEach>
    </c:if>
</c:forEach>
页码:
    1,2,3,4
</body>
</html>
