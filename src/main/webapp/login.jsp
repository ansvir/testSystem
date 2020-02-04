<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Test system: login</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet" />
    <link href="css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
    <div>
        <h1 class="h3 mb-3 font-weight-normal">Welcome!</h1>
        <form class="form-signin" name="authForm" action="testSystem" method="POST">
            <input type="hidden" name="command" value="login" />
            Username <input class="form-control" name="username" type="text"/>
            <br/>
            Password <input class="form-control" name="password" type="password"/>
            <br/>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="submit">submit</button>
        </form>
        <c:if test="${not empty requestScope.passedAuth and not requestScope.passedAuth}">
            <div class="alert alert-danger">Sorry, credentials are wrong</div>
        </c:if>
    </div>
</body>


