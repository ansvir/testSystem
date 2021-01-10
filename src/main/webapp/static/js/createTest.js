$(document).ready(function () {
    msgIsVisible(false);
    let rowsAmount = 0;
    let totalModalRows = 0;
    let questionTableRows = 0;
    let questionsArray = [];
    let idQuestions = [];
    let idAnswers = [];
    let idRightAnswers = [];

    $('#answersTableModal tbody').on("click", '.deleteAnswer', function () {
        if (rowsAmount === 0) return;
        if (rowsAmount <= 10) {
            msgIsVisible(false);
        }
        $(this).closest("tr").remove();
        rowsAmount--;
    });


    $('#addAnswer').on("click", function () {
        msgIsVisible(false);
        if (rowsAmount >= 10) {
            setMsg('modalMsg', 'alert-danger', 'No more than 10 answers are available');
            msgIsVisible(true);
            return;
        }
        totalModalRows++;
        rowsAmount++;
        let tr = `
                <tr id="answer${totalModalRows}">
                    <td>
                    
                        <div class="input-group-text">
                         
                                <input type="button" class="deleteAnswer" value="-">
                   
                        </div>
                    </td>
                    <td>
                        <div class="input-group-text">
                 
                                <input type="radio" data-select="rightAnswerNumber" name="rightAnswerNumber" id="rightAnswerNumber">
  
                        </div>
                    </td>
                    <td>
                        <textarea id="answerText" name="answerText" class="form-control" rows="1"></textarea>
                    </td>
                </tr>
            `
        $('#answersTableModal tbody').append(tr);
    });

    $('#addQuestionToTable').on('click', function () {
        msgIsVisible(false);
        let question = $('#question').val();
        idQuestions.push([questionTableRows, question]);
        let answersRadioTable = [];
        $("#answersTableModal tbody tr").each(function() {
            let answer = $(this).find('#answerText').val();
            idAnswers.push([questionTableRows, answer]);
            let radioChecked = $(this).find('#rightAnswerNumber').is(':checked') ? 1 : 0;
            idRightAnswers.push([questionTableRows, radioChecked === 1]);
            answersRadioTable.push([questionTableRows, answer, radioChecked]);
        });
        questionsArray.push(answersRadioTable);
        let firstTr = ``;
        if (answersRadioTable[0][2] === 1) {
            firstTr = `
                    <tr class="question${questionTableRows}">
                        <td rowspan="${answersRadioTable.length}"><input type="button" class="deleteQuestion" value="-"></td>
                        <td rowspan="${answersRadioTable.length}">${question}</td>
                        <td>${answersRadioTable[0][1]}</td>
                        <td class="fa fa-check"></td>
                    </tr>
            `;
        } else {
            firstTr = `
                    <tr class="question${questionTableRows}">
                        <td rowspan="${answersRadioTable.length}"><input class="deleteQuestion" type="button" value="-"></td>
                        <td rowspan="${answersRadioTable.length}">${question}</td>
                        <td>${answersRadioTable[0][1]}</td>
                        <td></td>
                    </tr>
            `;
        }
        let lastTrs = ``;
        for (let i=1; i<answersRadioTable.length; i++) {
            if (answersRadioTable[i][2] === 1) {
                lastTrs += `
                    <tr class="question${questionTableRows}">
                        <td>${answersRadioTable[i][1]}</td>
                        <td class="fa fa-check"></td>
                    </tr>
                `;
            } else {
                lastTrs += `
                    <tr class="question${questionTableRows}">
                        <td>${answersRadioTable[i][1]}</td>
                        <td></td>
                    </tr>
                `;
            }
        }
        let finalTrs = firstTr + lastTrs;
        questionTableRows++;
        clearModalData();
        $("#questionsTable tbody").append(finalTrs);
    });

    $('#questionsTable tbody').on('click', '.deleteQuestion', function() {
        msgIsVisible(false);
        let questionRow = $(this).closest("tr").attr("class");
        $(`#questionsTable tbody .${questionRow}`).remove();
        let idToDelete = parseInt(questionRow.substring(8));
        idQuestions = idQuestions.filter(item => {return item[0] !== idToDelete} );
        idAnswers = idAnswers.filter(item => {return item[0] !== idToDelete} );
        idRightAnswers = idRightAnswers.filter(item => {return item[0] !== idToDelete} );
    });

    $('#closeModalTimes').on('click', function() {
        clearModalData();
    });

    $('#closeModalButton').on('click', function() {
        clearModalData();
    });

    function clearModalData() {
        rowsAmount = 0;
        $("#question").val('');
        $('#answersTableModal tbody').children().remove();
    }

    function setMsg(messageId, className, message) {
        $(`${messageId}`).text(message);
        $(`${messageId}`).removeClass('alert-danger', 'alert-success');
        $(`${messageId}`).addClass(`${className}`);
    }

    function msgIsVisible(visibility) {
        if (visibility) {
            $('#modalMsg').css('visibility', 'visible');
        } else {
            $('#modalMsg').css('visibility', 'hidden');
        }
    }
});
