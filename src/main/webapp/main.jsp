<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>

<div style="text-align: center; margin-top: 100px; font-size: 20px">
    <div>Hello ${requestScope.username}</div>
        <form name="menu" action="" method="POST" style="margin-top: 20px">
            <input type="hidden" name="command" value="login" />
<%--            <c:if test="${not empty requestScope.currentRole}">--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${requestScope.currentRole}"></c:when>--%>
<%--                </c:choose>--%>
<%--            </c:if>--%>
    <div>
        <a href="login.jsp">logout</a>
    </div>
</div>


