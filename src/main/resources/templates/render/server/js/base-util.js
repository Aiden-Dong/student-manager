$(document).ready(function() {
});

function showLog(data){
    alert(data)
}

function parseNull(data){
  if (!data && typeof(data)!="undefined" && data!=0){
    return "";
  }else{
    return data
  }
}

function checkNull(data){
  if(data == null || data == undefined || data == ''){
    return true
  }else{
    return false
  }
}

function continerMessage(continer, message){
  continer.tooltip({
    placement:'right',
    title:message,
    html:true
  }).tooltip('show')
}

function infoMessage(message){
  $("#info-container").text(message);
  $("#info-msg-container").toast('show');
}

function errorMessage(message){
  $("#error-container").text(message);
  $("#error-msg-container").toast('show');
}

function getUrlVars(){
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

function getUrlVar(name){
    return getUrlVars()[name];
}

function httpGetMethod(getUrl, callback){
  $.ajax({
    url : getUrl,
    contentType:"application/json",
    type : "GET",
    timeout : 50000,
    async: false,
    dataType: "json",
    success : function(data) {
      callback(data)
    },
    error: function(data){
      errorMessage("error request")
    }
  });
}

function httpPostMethod(postUrl, postData, preAction, callback){
  var jsonVal = JSON.stringify(postData)

  $.ajax({
    url : postUrl,
    contentType:"application/json",
    data : jsonVal,
    type : "POST",
    timeout : 50000,
    async: true,
    dataType: "json",
    beforeSend: preAction,
    success : function(data) {
      callback(data)
    },
    error: function(data){
      errorMessage(data);
    }
  });
}

function httpPutMethod(getUrl, callback){
  $.ajax({
    url : getUrl,
    contentType:"application/json",
    type : "PUT",
    timeout : 50000,
    async: true,
    dataType: "json",
    beforeSend: function(){
      $('#wait-modal').modal({
        backdrop:'static'
      });
      $('#wait-modal').modal('show');
    },
    complete: function(){
      $('#wait-modal').modal('hide');
    },
    success : function(data) {
      callback(data['message'])
    },
    error: function(data){
      errorMessage("error request")
    }
  });
}

function httpDeleteMethod(getUrl, callback){
  $.ajax({
    url : getUrl,
    contentType:"application/json",
    type : "DELETE",
    timeout : 50000,
    async: true,
    dataType: "json",
    success : function(data) {
      callback(data)
    },
    error: function(data){
      errorMessage(data);
    }
  });
}


String.format = function() {
    if (arguments.length == 0)
        return null;
    var str = arguments[0];
    for ( var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
};
