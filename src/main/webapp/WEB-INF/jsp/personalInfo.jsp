<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="privateHeader.jsp" %>

<div class="container">
    <div class="page-header" align="center">
        <h3> Its your personal information.</h3>
    </div>
<form:form action="/personal/info" method="POST" modelAttribute="infoForm">
  <table>
  <tr>
    <td><label for="fulName">Full name:</label></td><td><form:input path="fullName" id="fullName"/></td>
  </tr>
  <tr>
    <td><label for="studyPlaces">Study places:</label></td><td><form:input path="studyPlaces" id="studyPlaces"/></td>
  </tr>
  <tr>
    <td><label for="workPlaces">Work places:</label></td><td><form:input path="workPlaces" id="workPlaces"/></td>
  </tr>
  <tr>
    <td><label for="someInformation">Some information:</label></td><td><form:input path="someInformation" id="someInformation"/></td>
  </tr>
  <tr>
    <td/><td><input type="submit" value="Submit"/></td>
  </tr>
  </table>
</form:form>
</div>
</body>
</html>