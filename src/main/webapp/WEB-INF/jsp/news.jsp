<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<h2><a href="index.html">Home</a></h2>
<h2>News at Klops.ru</h2>

<table>
    <tr>
        <th width="100">Icon</th>
        <th width="100">Title</th>
        <th width="400">News</th>
        <th width="400">Link</th>
    </tr>
    <c:forEach items="${news}" var="n">
        <tr>
            <td><img src="${n.icon}"/></td>
            <td>${n.title}</td>
            <td>${n.text}</td>
            <td>${n.url}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
