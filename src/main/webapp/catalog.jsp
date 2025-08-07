<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Каталог товаров</title>
</head>
<body>
<h1>Каталог</h1>

<c:forEach var="product" items="${products}">
    <div>
        <h3>${product.name}</h3>
        <p>${product.description}</p>
        <p>Цена: ${product.price} руб.</p>

        <form method="post" action="addToCart">
            <input type="hidden" name="id" value="${product.id}" />
            <input type="submit" value="Добавить в корзину" />
        </form>
    </div>
</c:forEach>

<form action="cart" method="get" style="margin-top: 30px;">
    <button type="submit">В корзину</button>
</form>

</body>
</html>
