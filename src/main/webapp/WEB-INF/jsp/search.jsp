<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<a href="/main_page">Main Page</a>
<a href="/logout">Logout</a>
<br>

<p>${error}</p>
<p> ${message}</p>
<h3> Write e-mail ou userId.</h3>

<form:form action="/search" method="POST" modelAttribute="searchForm">
  <table>
  <tr>
    <td><label for="query">Query:</label></td><td><form:input path="query" id="query"/></td>
  </tr>
  <tr>
    <td/><td><input type="submit" value="Search"/></td>
  </tr>
  </table>
</form:form>