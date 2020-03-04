$(document).ready(function(){
    initEvent();
})


function initEvent(){
    $("#signin-submit").click(authAction)
}

function authAction(){
    var name = $("#input-username").val()
    var password = $("#input-password").val()

    var authObj = {
        name : name,
        password : password
    }

    httpPostMethod(
        '/stm/api/v1/signin', 
        authObj, 
        function(){},
        function(data){
            var status = data['status']
            if(status == "success"){
                infoMessage(data['body'])
                setTimeout(function (){
                    window.location.reload()
                }, 1000)
            }else{
                errorMessage(data['body'])
            }
        });
}