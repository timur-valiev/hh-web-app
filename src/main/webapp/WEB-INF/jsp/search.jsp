<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="privateHeader.jsp" %>

<div class="container"  align="center">
    <div class="page-header">
        <h3> Search.</h3>
    </div>

<form:form action="/${action}" method="POST" modelAttribute="searchForm">
  <table>
  <tr>
      <td><label for="name">Part of name:</label></td><td><form:input path="name" id="name"/></td>
  </tr>
  <tr>
      <td><label for="mail">E-mail:</label></td><td><form:input path="mail" id="mail"/></td>
  </tr>
  <tr>
      <td><label for="study">Study places:</label></td><td><form:input path="study" id="study"/></td>
  </tr>
  <tr>
      <td><label for="work">Work places:</label></td><td><form:input path="work" id="work"/></td>
  </tr>
  <tr>
    <td/><td><input type="submit" value="Search"/></td>
  </tr>
  </table>
</form:form>
</div>
</body>
</html>