<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Советы</title>
</head>
<body>
<h1>Создать совет</h1>
<form action="/help-service/v1/support" method="post">
    <input type="text" name="description" placeholder="Введите совет" required>
    <br>
    <br>
    <button type="submit">Добавить</button>
</form>
</body>
</html>
