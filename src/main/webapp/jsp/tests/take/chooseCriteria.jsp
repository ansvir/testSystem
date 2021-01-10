<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date"/>
<head>
    <title>Test system: show tests</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link href="../../../css/actionForm.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Test system</a>
    <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
        </ul>
        <ul class="nav navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="../../login.jsp">logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="actionMainForm">
    <form name="menu" action="testSystem" method="POST">
        <input type="hidden" name="command" value="choose_tests_criteria"/>
        <div class="container">
            <div class="row">
                <div class="col-lg-5 text-center">
                    <h1 class="h2">Tests: choose criteria</h1>
                </div>
                <div class="col-lg-7 card card-body bg-light">
                    <div>
                        <div class="form-group">
                            Choose subject
                            <select class="custom-select" name="subjectId" data-width="auto" size="10" title="Choose subject">
                                <c:forEach items="${sessionScope.subjects}" var="item">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <br/>
                        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="submit">show</button>
                        <br/>
                        <a class="btn btn-lg btn-primary btn-block" href="../../main.jsp">back</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>





