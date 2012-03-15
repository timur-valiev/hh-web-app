<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="privateHeader.jsp" %>
<div class="container"  align="center">
    <div class="page-header">
        <h3> User info</h3>
    </div>
${user.fullName}[${user.email}]
<br>
Study places: ${user.studyPlaces}
<br>
Work places: ${user.workPlaces}
<br>
Information: ${user.someInformation}
<br>


<a href="/users/request/${user.id}/about_me">Request recommendation about me.</a>
<br>
<a href="/users/request/${user.id}/about_another">Request recommendation about another user.</a>
<br>
<a href="/users/${user.id}/recommendations">Users recommendations.</a>
</div>
</body>
</html>
