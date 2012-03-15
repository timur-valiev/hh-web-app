<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="privateHeader.jsp" %>
<div class="container"  align="center">
    <div class="page-header">
        <h3> Users recommendations </h3>
    </div>
<ul>
  <c:forEach var="r" items="${rec}">
    <li>From ${r.writer.fullName}[${r.writer.email}] about ${r.subject.fullName}[${r.subject.email}]
    <br>
    <a href="/users/${id}/recommendations/show/${r.id}">Show.</a>
    </li>
  </c:forEach>
</ul>
</div>

</body>
</html>

