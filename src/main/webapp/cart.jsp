<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Корзина покупок</title>
</head>
<body>
<h1>Ваша корзина</h1>
<c:choose>
  <c:when test="${empty sessionScope.cart}">
    <p>Корзина пуста</p>
  </c:when>
  <c:otherwise>
    <ul>
      <c:forEach var="product" items="${sessionScope.cart}">
        <li>${product.name} - ${product.price} руб.</li>
      </c:forEach>
    </ul>
  </c:otherwise>
</c:choose>

<form action="catalog" method="get">
  <button type="submit">В каталог</button>
</form>
</body>
</html>
