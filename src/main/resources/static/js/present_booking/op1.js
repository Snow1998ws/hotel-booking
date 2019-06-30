jQuery(document).ready(function($){
    $op_button = $('.booking_item_op_button');
    $op_button.on("click",deleRoom);
});

function deleRoom() {
    $.ajax(
        {
            type: "POST",
            url: "/deleteRoom",
            dataType: 'json',
            data: {infoss:($(this).attr("name"))},

            success: function (order_id) {//ajax请求成功后触发的方法
                var tmp=document.getElementById(order_id);
                // // alert("111");
                // //console.log(order_id);
                tmp.parentNode.removeChild(tmp);
                //$("#order_id").empty();
                //alert(order_id)
            }
        }
    );
}