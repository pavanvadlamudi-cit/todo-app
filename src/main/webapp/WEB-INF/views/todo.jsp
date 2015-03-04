<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<h1>TODO items:</h1>
<c:forEach items="${todos}" var="todo" varStatus="row">
${row.index}. ${todo.text} <br />
</c:forEach>
</html>