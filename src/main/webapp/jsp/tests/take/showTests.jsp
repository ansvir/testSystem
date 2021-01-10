<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dao.TestDAO"%>
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
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
    <script>
        let tests;
        let testsTableRowIndex;
        let currentTestName;
        let currentTestDescription;
        let currentTestTime;

        $(document).ready(function() {

            let testsTable = "#testsTable";

            $.get("/data/tests", function(data) {
                tests = data;
            });

            $(testsTable).on('click', '#testsTableRow', function(event) {
                if($(this).hasClass('table-active')){
                    $(this).removeClass('table-active');
                } else {
                    $(this).addClass('table-active').siblings().removeClass('table-active');
                    testsTableRowIndex = $(this).index();
                }
            });

            $("#showModalButton").on('click', function() {
                let counter = 0;
                let found = false;
                for (let test in tests) {
                    if (tests.hasOwnProperty(test)) {
                        if (counter === testsTableRowIndex) {
                            currentTestName = tests[test].name;
                            currentTestDescription = tests[test].description;
                            currentTestTime = tests[test].time;
                            found = true;
                            break;
                        }
                    }
                    if (found === true) {
                        break;
                    }
                    counter++;
                }
                $("#chosenTestName").text(currentTestName);
                $("#chosenTestDescription").text(currentTestDescription);
                $("#chosenTestTime").text(currentTestTime);
            });
        });
    </script>
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
    <form action="testSystem" method="post" id="form">
        <input type="hidden" name="command" value="choose_test"/>
        <div class="container">
            <div class="row">
                <div class="col-lg-5 text-center">
                    <h1 class="h2">Tests in ${requestScope.subject.name}</h1>
                </div>
                <div class="col-lg-7 card card-body bg-light">
                    <div class="mb-2">
                        Tests
                        <table class="table table-hover table-responsive" id="testsTable">
                            <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.tests}" var="test">
                                    <tr id="testsTableRow">
                                        <td>${test.name}</td>
                                        <td>${test.description}</td>
                                        <td>${test.time}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <br/>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="button" id="showModalButton" data-toggle="modal" data-target=".confirmModal">submit</button>
                    <br/>
                    <a class="btn btn-lg btn-primary btn-block" href="chooseCriteria.jsp">back</a>
                    <br/>
                    <a class="btn btn-lg btn-primary btn-block" href="../../main.jsp">to menu</a>
                </div>
            </div>
        </div>
        <div class="modal fade confirmModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="gridSystemModalLabel">Are you sure you want to take <span id="chosenTestName">choose the test</span> test?</h4>
                    </div>
                    <div class="modal-body">
                        <div class="body-message">
                            <p>Time: <span id="chosenTestTime">00:00:00</span></p>
                            <p>Description: <span id="chosenTestDescription">choose the test</span></p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" name="submit" class="btn btn-primary" data-dismiss="modal">submit</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>





