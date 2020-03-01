<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date"/>
<head>
    <title>Test system: view users</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <link href="../../css/actionForm.css" rel="stylesheet"/>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="../../../css/actionForm.css" rel="stylesheet"/>
    <link href="../../../css/questions.css" rel="stylesheet"/>
</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Test system</a>
    <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
        </ul>
        <ul class="nav navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="../../../login.jsp">logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="actionMainForm">
    <form name="menu" action="testSystem" method="POST">
        <input type="hidden" name="command" value="choose_test"/>
        <div class="container">
            <div class="row">
                <div class="col-lg-5 text-center">
                    <h2 class="h2">${requestScope.currentTest.name} test</h2>
                    <br/>
                    <h2 class="h2">${requestScope.currentSubject.name}</h2>
                    <br/>
                    <h2 class="h2">Question ${requestScope.questionNumber}</h2>
                    <br/>
                    <h2 class="h2">Time left: ${requestScope.timeLeft}</h2>
                </div>
                <div class="col-lg-7 card card-body bg-light">
                    <div class="mb-2">
                        <div class="form-group">
                            <label for="exampleFormControlTextarea1">Question</label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                            <br/>
                            Answers
                            <select class="custom-select" name="chosenAnswerId" data-width="auto" size="10" title="Choose answer">
                                <c:forEach items="${requestScope.currentAnswers}" var="item">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" name="submitAnswer" value="submit" id="submit">submit</button>
                    <br/>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" name="endTest" value="end" id="end">end</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>





