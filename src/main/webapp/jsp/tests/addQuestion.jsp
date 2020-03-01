<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date"/>
<head>
    <title>Test system: create test</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
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
                <a class="nav-link" href="../../login.jsp">logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="actionMainForm">
    <form name="menu" action="testSystem" method="POST">
        <input type="hidden" name="command" value="create_test_add_question"/>
        <div class="container">
            <div class="row">
                <div class="col-lg-5 text-center">
                    <h1 class="h2">Create test:add question</h1>
                    <br/>
                    <h5 class="h5">${requestScope.testName}</h5>
                    <br/>
                    <h5 class="h5">${requestScope.subjectName}</h5>
                </div>
                <div class="col-lg-7 card card-body bg-light">
                    <div>
                        <div class="form-group">
                            <label for="question">Question</label>
                            <textarea name="question" class="form-control" id="question" rows="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="answer">Answer</label>
                            <textarea name="question" class="form-control" id="answer" rows="3"></textarea>
                        </div>
                        <br/>
                        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="submit">submit</button>
                        <br/>
                        <a class="btn btn-lg btn-primary btn-block" href="../../main.jsp">back</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>





