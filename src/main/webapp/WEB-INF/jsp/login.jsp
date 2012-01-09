<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<br>
<a href="/">Start Page</a>
<br>
<p>${error}</p>
<p> ${message}</p>
<p> ${counter}</p>
<h3> Please log in.</h3>

<form:form action="/login" method="POST" modelAttribute="loginForm">
  <table>
  <tr>
    <td><label for="email">Email:</label></td><td><form:input path="email" id="email"/></td>
  </tr>
  <tr>
    <td><label for="password">Password:</label></td><td><form:input path="password" type="password" id="password"/></td>
  </tr>
  <tr>
    <td/><td><input type="submit" value="Login"/></td>
  </tr>
  </table>
</form:form>