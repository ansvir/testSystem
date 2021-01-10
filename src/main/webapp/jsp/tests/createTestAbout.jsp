<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date"/>
<head>
    <title>Test system: create a test</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link href="../../static/css/actionForm.css" rel="stylesheet"/>
    <link href="../../static/css/questionsTable.css" rel="stylesheet"/>
    <script src="../../static/js/createTest.js" type="text/javascript"></script>
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
            <input type="hidden" name="command" value="create_test_main"/>
            <div class="container">
                <div class="row">
                    <div class="col-lg-5 text-center">
                        <h1 class="h2">Create test:about</h1>
                    </div>
                    <div class="col-lg-7 card card-body bg-light">
                        <div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-sizing-default2">Test name</span>
                                </div>
                                <input type="text" name="createTestName" class="form-control" aria-label="Default"
                                       aria-describedby="inputGroup-sizing-default">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="subjectSelect">Subject</label>
                                </div>
                                <select name="createTestSubjectId" class="custom-select" id="subjectselect">
                                    <c:forEach items="${sessionScope.subjects}" var="item">
                                        <option class="dropdown-item" value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-sizing-default4">Description</span>
                                </div>
                                <textarea type="text" name="createTestDescription" class="form-control" aria-label="Description"
                                          aria-describedby="inputGroup-sizing-default"></textarea>
                            </div>
                            <div class="container">
                                <div class="row mb-2">
                                    <div class="col-lg-2"></div>
                                    <div class="col-lg-8">
                                        <input type="button" class="btn btn-lg btn-primary w-100" data-toggle="modal" data-target="#addQuestionModal" value="add question">
                                    </div>
                                    <div class="col-lg-2"></div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col-lg-2"></div>
                                    <div class="col-lg-8">
                                        <button class="btn btn-lg btn-primary w-100" type="submit" name="submit" value="submit">
                                            finish test
                                        </button>
                                    </div>
                                    <div class="col-lg-2"></div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-2"></div>
                                    <div class="col-lg-8">
                                        <a class="btn btn-lg btn-primary w-100" href="../main.jsp">cancel</a>
                                    </div>
                                    <div class="col-lg-2"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <table id="questionsTable" class="table table-bordered mt-4">
                        <thead>
                            <tr>
                                <th scope="col" class="delete-column"></th>
                                <th scope="col" class="questions-column">Question</th>
                                <th scope="col" class="answers-column">Answers</th>
                                <th scope="col" class="check-column">Right answer</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="addQuestionModal" class="modal fade" role="dialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Add question</h4>
                            <button id="closeModalTimes" type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container login-fields">
<%--                                <div class="row mb-4">--%>
<%--                                    <div id="modalMsg" class="h5 alert" role="alert"></div>--%>
<%--                                </div>--%>
                                <div class="row mb-4 field">
                                    <label for="question">Question</label>
                                    <textarea name="question" class="form-control" id="question" rows="3"></textarea>
                                </div>
                                <div class="row mb-4">
                                    <div class="col-lg-2"></div>
                                    <div class="col-lg-8">
                                        <input type="button" class="btn btn-lg btn-primary w-100" id="addAnswer" value="new answer">
                                    </div>
                                    <div class="col-lg-2"></div>
                                </div>
                                <div class="row">
                                    <table class="table table-bordered" id="answersTableModal">
                                        <thead>
                                        <tr>
                                            <th scope="col"></th>
                                            <th scope="col">Right answer</th>
                                            <th scope="col">Text</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="closeModalButton" type="button" class="btn btn-secondary" data-dismiss="modal">close</button>
                            <button id="addQuestionToTable" type="button" class="btn btn-primary">submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>





