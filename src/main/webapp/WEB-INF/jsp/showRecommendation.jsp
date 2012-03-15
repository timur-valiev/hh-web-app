<%@ include file="privateHeader.jsp" %>
<div class="container"  align="center">
    <div class="page-header">
        <h3> Recommendation about ${rec.subject.fullName}[${rec.subject.email}] </h3>
    </div>
    ${rec.message}
    <br>
    <a href="${back}">Return back.</a>
</div>
</body>
</html>