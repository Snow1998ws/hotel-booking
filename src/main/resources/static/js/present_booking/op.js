$(document).ready(function(){
    $op_button = $('.booking_item_op_button');
    $op_button.on("click",deleRoom);
    $cancel_button = $('.booking_item_right_tab');
    $cancel_button.on("click",deleRoom);
});

function deleRoom() {
    var cancel = confirm("是否确定退房")
    if(cancel)
    {
        $.ajax(
            {
                type: "POST",
                url: "/deleteRoom",
                dataType: 'json',
                data: {infoss:($(this).parents('.booking_item_content').attr("id"))},
                //
                success: function (order_id) {//ajax请求成功后触发的方法
                    var $order = $('#' + order_id);
                    $order.animate({left:'-80vw', opacity: 0},1000);
                    setTimeout(function(){ $order.remove(); }, 1000);
                },
                error:function ()
                {
                    
                }

            }
        );
    }
}