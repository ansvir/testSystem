<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date"/>
<head>
    <title>Test system: delete user</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <link href="../../css/actionForm.css" rel="stylesheet"/>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="../../css/actionForm.css" rel="stylesheet"/>
</head>
<body>
    <nav class="navbar navbar-expand navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Test system</a>
        <div class="collapse navbar-collapse" id="navbarsExample02">
            <ul class="navbar-nav mr-auto">
            </ul>
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="../login.jsp">logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="actionMainForm">
        <form name="menu" action="testSystem" method="POST">
            <input type="hidden" name="command" value="delete_user"/>
            <div class="container">
                <div class="row">
                    <div class="col-lg-5 text-center">
                        <h1 class="h2">Delete user</h1>
                    </div>
                    <div class="col-lg-7 card card-body bg-light">
                        <div class="mb-2">
                            Users
                            <select class="custom-select" name="deleteUserId" data-width="auto" size="10" title="Choose user">
                                <c:forEach items="${sessionScope.users}" var="item">
                                    <option value="${item.id}">${item.username}</option>
                                </c:forEach>
                            </select>
                            <br/>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="submit">submit</button>
                        <br/>
                        <a class="btn btn-lg btn-primary btn-block" href="../main.jsp">back</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>





