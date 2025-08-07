<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form method="POST" action="/shop2/login">

    Логин: <input type="text" name="user" />
    <br />
    Пароль: <input type="password" name="password" />
    <input type="submit" value="Войти" />
</form>
</body>
</html>