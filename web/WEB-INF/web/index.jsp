<%--
  Created by IntelliJ IDEA.
  User: kic
  Date: 2022/1/14
  Time: 下午7:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Ablog</title>
  </head>
  <body>
  <h1>${hello}</h1>
  <form action="./SaveMd" method="post">
    <input type="text" name="ArtName" placeholder="artname">
    <input type="text" name="ArtText" placeholder="ArtText">
    <button type="submit" value="tijiao"></button>
  </form>
  </body>
</html>
