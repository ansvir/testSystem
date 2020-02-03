<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>
<head>
    <title>Test system: main</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
        <div class="container">
            <a class="navbar-brand" href="#">Test system</a>
            <ul class="nav navbar-nav mr-auto">
            </ul>
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <div style="margin-top:150px">
        <form name="menu" action="login" method="POST">
            <input type="hidden" name="command" value="login"/>
            <div class="container">
                <div class="row">
                    <div class="col-lg-5 text-center">
                        <h1 class="h2">Hello, ${requestScope.username}</h1>
                    </div>
                    <div class="col-lg-7">
                        <div>
                            <button class="btn btn-primary btn-block">Dejstvie</button>
                            <button class="btn btn-primary btn-block">Dejstvie</button>
                            <button class="btn btn-primary btn-block">Dejstvie</button>
                        </div>
                    </div>
                </div>
            </div>
            <%--            <c:if test="${not empty requestScope.currentRole}">--%>
            <%--                <c:choose>--%>
            <%--                    <c:when test="${requestScope.currentRole}"></c:when>--%>
            <%--                </c:choose>--%>
            <%--            </c:if>--%>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>




