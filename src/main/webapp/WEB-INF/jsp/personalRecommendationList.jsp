<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="privateHeader.jsp" %>
<div class="container">
    <div class="page-header" align="center">
        <h3> Its your ${part}.</h3>
    </div>
<ul>
  <c:forEach var="r" items="${rec}">
    <li>From ${r.writer.fullName}[${r.writer.email}] about ${r.subject.fullName}[${r.subject.email}]
    <br>
    <a href="/personal/${part}/${act1}/${r.id}">${act1}.</a>
    <br>
    <a href="/personal/${part}/${act2}/${r.id}">${act2}.</a>
    </li>
  </c:forEach>
</ul>
</div>
</body>
</html>

