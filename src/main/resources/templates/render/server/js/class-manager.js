$(document).ready(function (){
    initEnv();
    initEvent();
})

function initEnv(){
    $("#class-ctime-input").datepicker({
        locale: 'zh-cn',
        format: 'yyyy-mm-dd',
        weekStartDay: 1
    });
}

function initEvent(){
    $("#new-class-btn").click(newClassEvent);
    $("#submit-newcls-bth").click(submitClS);
    $(".class-message-btn").click(showClassMessage);
    $(".del-class-btn").click(deleteClass);
}

function newClassEvent(){
    $("#class-name-input").val("");
    $("#class-owner-input").val("");
    $("#class-ctime-input").val("");
}

function submitClS(){
    var clsName = $("#class-name-input").val();
    var clsCreateTime = $("#class-ctime-input").val();
    var clsManager = $("#class-owner-input").val();

    if(checkNull(clsName)){
        continerMessage($("#class-name-input"), "班级名称不能为空");
        return;
    }

    if(checkNull(clsManager)){
        continerMessage($("#class-owner-input"), "班主任信息不能为空");
        return;
    }

    if(checkNull(clsCreateTime)){
        continerMessage($("#class-ctime-input"), "班级创建时间不能为空");
        return;
    }

    var newCls = {
        clsName : clsName,
        clsCreateTime : new Date(clsCreateTime).getTime(),
        clsManager : clsManager
    }

    httpPostMethod(
        "/stm/api/v1/class", 
        newCls, 
        function (){
            $("#new-class-container").modal('hide');
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

function showClassMessage(event){
    var clsId = $(event.target).attr("clsId");
    $(window).attr("location", String.format('/server/class-manager/{0}', clsId))
}

function deleteClass(event){
    var clsId = $(event.target).attr("clsId");
    httpDeleteMethod(String.format('/stm/api/v1/class/{0}', clsId), function (data){
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