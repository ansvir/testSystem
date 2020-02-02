<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="text-align: center; margin-top: 100px; font-size: 20px">
    <h1>Welcome!</h1>
    <form name="authForm" action="login" method="POST" style="margin-top: 20px">
        <input type="hidden" name="command" value="login" />
        Username <input name="username" type="text"/>
        <br/>
        Password <input name="password" type="password"/>
        <input type="submit" name="submit" value="submit"/>
    </form>
    <c:if test="${not empty requestScope.passedAuth and not requestScope.passedAuth}">
        <div style="color: red">Sorry, credentials are wrong</div>
    </c:if>
</div>

