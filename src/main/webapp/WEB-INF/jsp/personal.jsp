<%@ include file="privateHeader.jsp" %>

<div class="container">
    <div class="page-header" align="center">
          <h3> Personal page.</h3>
    </div>
    [${user.email}]${user.fullName}
    <br>
    <a href="/personal/info">Look/change info.</a>
    <br>
    <a href="/personal/inbox">Inbox</a>
    <br>
    <a href="/personal/queries">Queries</a>
    <br>
    <a href="/personal/recommendations">Your recommendations</a>
    <br>
</div>
</body>
</html>
