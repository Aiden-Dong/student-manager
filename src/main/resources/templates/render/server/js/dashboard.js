activeContainer=null

$(document).ready(function(){
    class_board_event();
    eventReady();
})

function eventReady(){
    $("#class-board-btn").click(class_board_event);
    $("#class-manager-btn").click(class_manager_event);
}


function class_board_event(){
   swapActiveContainer($("#class-board-btn"));
   $("#container-frame").attr("src", "/server/class-board");
}

function class_manager_event(){
    swapActiveContainer($("#class-manager-btn"));
    $("#container-frame").attr("src", "/server/class-manager");
}

function swapActiveContainer(newContainer){
    if(activeContainer != null){
         activeContainer.removeClass("active"); 
    }
    activeContainer=newContainer
    activeContainer.addClass("active");
}