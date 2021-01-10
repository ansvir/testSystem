<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date"/>
<head>
    <title>Test system: create a test</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="../../static/js/createTest.js" type="text/javascript" ></script>
    <link href="../../static/css/actionForm.css" rel="stylesheet"/>
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
        <input type="hidden" name="command" value="create_test_add_question"/>
        <div class="container">
            <div class="row">
                <div class="col-lg-5 text-center">
                    <h1 class="h2">Create test:add question</h1>
                    <br/>
                    <h5 class="h5">${requestScope.createTestName}</h5>
                    <br/>
                    <h5 class="h5">${requestScope.createTestSubjectName}</h5>
                    <br>
                </div>
                <div class="col-lg-7 card card-body bg-light">
                    <div>
                        <div class="form-group">
                            <label for="question">Question</label>
                            <textarea name="question" class="form-control" id="question" rows="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="answersTable">Answers</label>
                            <table class="table table-bordered" id="answersTable">
                                <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Right answer</th>
                                    <th scope="col">Text</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr id="answer1">
                                    <td>
                                        <div class="input-group-text">
                                            <input type="button" class="deleteAnswer" value="-">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group-text">
                                            <input type="radio" name="rightAnswerNumber1">
                                        </div>
                                    </td>
                                    <td>
                                        <textarea name="answerText1" class="form-control" rows="1"></textarea>
                                    </td>
                                </tr>
                                <tr id="answer2">
                                    <td>
                                        <div class="input-group-text">
                                            <input type="button" class="deleteAnswer" value="-">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group-text">
                                            <input type="radio" name="rightAnswerNumber2">
                                        </div>
                                    </td>
                                    <td>
                                        <textarea name="answerText2" class="form-control" rows="1"></textarea>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <br/>
                            <input type="button" class="btn btn-lg btn-primary btn-block" id="addAnswer" value="+">
                        </div>
                        <br/>
                        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="submit">add question</button>
                        <br/>
                        <a class="btn btn-lg btn-primary btn-block" href="../main.jsp">cancel</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>





