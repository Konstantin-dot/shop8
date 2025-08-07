<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Добавить товар</title>
</head>
<body>
<h1>Добавить новый товар</h1>
<form method="post" action="addProduct">
  Название: <input type="text" name="name" required /><br/>
  Описание: <input type="text" name="description" required /><br/>
  Цена: <input type="number" name="price" step="0.01" required /><br/>
  <input type="submit" value="Добавить" />
</form>
</body>
</html>
