<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<a href="/main_page">Main Page</a>
<a href="/logout">Logout</a>
<a href="/search">Search</a>

<br>
<h1>User info</h1>
<br>
<h4>${error}</h4>
<br>
${user.fullName}[${user.email}]
<br>