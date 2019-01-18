<%@ page pageEncoding="utf-8" isELIgnored="false" %>
<html>
<head>

</head>
<body>
<center>
    <h1>基于ES SpringBoot构建的唐诗检索系统</h1>
    <form action="${pageContext.request.contextPath}/search" method="post">
        <input type="text" name="keyword">
        <input type="submit" value="输入关键字搜索">
    </form>
</center>
</body>
</html>
