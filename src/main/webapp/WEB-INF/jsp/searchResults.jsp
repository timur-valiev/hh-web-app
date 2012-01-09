<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<a href="/main_page">Main Page</a>
<a href="/logout">Logout</a>
<br>
<h1>Search results</h1>
<ul>
  <c:forEach var="user" items="${result}">
    <li>${user.fullName}[${user.email}] <a href="/users/${user.id}">Users page.</a> </li>
  </c:forEach>
</ul>

<br>