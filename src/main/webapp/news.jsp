<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>News at Klops.ru</title>
    <style type="text/css">
        table {
            border: 4px double black; /* Рамка вокруг таблицы */
            border-collapse: collapse; /* Отображать только одинарные линии */
        }
        th {
            text-align: left; /* Выравнивание по левому краю */
            background: #ccc; /* Цвет фона ячеек */
            padding: 5px; /* Поля вокруг содержимого ячеек */
            border: 1px solid black; /* Граница вокруг ячеек */
        }
        td {
            padding: 5px; /* Поля вокруг содержимого ячеек */
            border: 1px solid black; /* Граница вокруг ячеек */
        }
    </style>
</head>

<body>
<h2><a href="index.html">Home</a></h2>
<h2>News at Klops.ru</h2>

<table>
    <tr>
        <th width="100">Title</th>
        <th width="400">News</th>
    </tr>
    <c:forEach items="${news}" var="n">
        <tr>
            <td>${n.name}</td>
            <td>${n.text}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
