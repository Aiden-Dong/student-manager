$(document).ready(function(){
    initEnv();
    initEvent();
});

function initEnv(){
    $("#student-stime-input").datepicker({
        locale: 'zh-cn',
        format: 'yyyy-mm-dd',
        weekStartDay: 1
    });
}


function initEvent(){
    $("#new-student-btn").click(newStudnetContainer);
    $("#submit-newst-bth").click(submitNewStudent);
    $(".del-student-btn").click(deleteStudent);

    $('.stu-political-select-span').click(stuPoliticalSelectAction)
    $('.stu-political-select').change(submitStudentBySelect)
}

function stuPoliticalSelectAction(event){
    var container = $(event.target).parent()
    container.children("span").css("display", "none")
    container.children("select").css("display", "inline")
}

function submitStudentBySelect(event){
    var studentContainer = $(event.target).parent().parent();

    var clsId = studentContainer.attr("clsId");
    var stId = Number($("td:eq(0)", studentContainer).text())
    var stName = $("td:eq(1)", studentContainer).text()
    var stSex = $("td:eq(2)", studentContainer).text()
    var stStartTime = Number($("td:eq(3)", studentContainer).attr('stTime'))
    var stCity = $("td:eq(4)", studentContainer).text()
    var stPolitical = $(event.target).val();


    var stuMessage = {
        stId : stId,
        stName : stName,
        stStartTime : new Date(stStartTime).getTime(),
        clsId : clsId,
        stCity : stCity, 
        stSex : stSex,
        stPolitical : stPolitical
    }

    console.log(JSON.stringify(stuMessage))

    httpPostMethod(
        String.format("/stm/api/v1/students/{0}", clsId), 
        stuMessage,
        function (){
            $("#new-student-container").modal('hide');
        },
        function(data){
            var status = data["status"];
            if(status == "success"){
                infoMessage(data["body"]);
                setTimeout(function (){
                    window.location.reload();
                }, 1000)
            }else if(status == "error"){
                errorMessage(data["body"])
                setTimeout(function (){
                    window.location.reload();
                }, 1000)
            }
        });
}

function newStudnetContainer(){
    $("#student-name-input").val("");
    $("#student-sex-input").val("");
    $("#student-stime-input").val("");
    $("#student-city-input").val("");
    $("#student-political-input").val("");
}

function submitNewStudent(event){
    var stName =  $("#student-name-input").val()
    var stStartTime = $("#student-stime-input").val()
    var clsId = $(event.target).attr("clsId");
    var stCity = $("#student-city-input").val()
    var stSex = $("#student-sex-input").val()
    var stPolitical = $("#student-political-input").val()

    if(checkNull(stName)){
        continerMessage($("#student-name-input"), "请输入学生信息");
        return;
    }

    if(checkNull(stSex)){
        continerMessage($("#student-sex-input"), "请输入性别信息");
        return;
    }

    if(checkNull(stStartTime)){
        continerMessage($("#student-stime-input"), "请输入出生日期")
        return ;
    }

    if(checkNull(stCity)){
        continerMessage($("#student-city-input"), "请输入城市信息")
        return;
    }

    if(checkNull(stPolitical)){
        continerMessage($("#student-political-input"), "请输入政治背景")
        return;
    }

    var stuMessage = {
        stName : stName,
        stStartTime : new Date(stStartTime).getTime(),
        clsId : clsId,
        stCity : stCity, 
        stSex : stSex,
        stPolitical : stPolitical
    }

    httpPostMethod(
        String.format("/stm/api/v1/students/{0}", clsId), 
        stuMessage,
        function (){
            $("#new-student-container").modal('hide');
        },
        function(data){
            var status = data["status"];
            if(status == "success"){
                infoMessage(data["body"]);
                setTimeout(function (){
                    window.location.reload();
                }, 1000)
            }else if(status == "error"){
                errorMessage(data["body"])
            }
        });
}

function deleteStudent(event){
    var stuId = $(event.target).attr("stId")

    httpDeleteMethod(String.format("/stm/api/v1/students/{0}", stuId), function(data){
        var status = data["status"];
        if(status == "success"){
            infoMessage(data["body"]);
            setTimeout(function (){
                window.location.reload();
            }, 1000)
        }else if(status == "error"){
            errorMessage(data["body"])
        }
    })

}