<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="publicHeader.jsp" %>
<div class="container">
    <div class="page-header" align="center">
          <h3> Registration.</h3>
    </div>
<form:form action="/register" method="POST" modelAttribute="registerForm">
  <table>
  <tr>
    <td><label for="email">Email:</label></td><td><form:input path="email" id="email"/></td>
  </tr>
  <tr>
    <td><label for="password">Password:</label></td><td><form:input path="password" type="password" id="password"/></td>
  </tr>
  <tr>
    <td><label for="fullName">Full Name:</label></td><td><form:input path="fullName" id="fullName"/></td>
  </tr>
  <tr>
    <td/><td><input type="submit" value="Register"/></td>
  </tr>
  </table>
</form:form>
</div>
</body>
</html>