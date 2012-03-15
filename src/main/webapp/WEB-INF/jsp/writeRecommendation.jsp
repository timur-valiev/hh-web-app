<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="privateHeader.jsp" %>
<div class="container"  align="center">
    <div class="page-header">
        <h3> Write recommendation about ${subject.subject.fullName}[${subject.subject.email}].</h3>
    </div>

<form:form action="/personal/queries/answer/${subject.id}" method="POST" modelAttribute="recForm">
  <table>
  <tr>
    <td><label for="message">Message:</label></td><td><form:input path="message" id="message"/></td>
  </tr>
  <tr>
    <td/><td><input type="submit" value="Submit"/></td>
  </tr>
  </table>
</form:form>

</div>
</body>
</html>