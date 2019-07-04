$(document).ready(function(){
    $op_button = $('.booking_item_op_button');
    $op_button.on("click",PayRoom);
    $cancel_button = $('.booking_item_right_tab');
    $cancel_button.on("click",cancelOrder);
});

function cancelOrder() {
    var cancel = confirm("是否删除本条订购记录")
    if(cancel)
    {
        $.ajax(
            {
                type: "POST",
                url: "/delehistory",
                dataType: 'json',
                data: {infoss:($(this).parents('.booking_item_content').attr("id"))},

                success: function (order_id) {//ajax请求成功后触发的方法
                    $('#' + order_id).remove();
                }
            }
        );
    }
}


function PayRoom() {
    $.ajax(
        {
            type: "POST",
            url: "/payRoom",
            dataType: 'json',
            data: {infoss:($(this).parents('.booking_item_content').attr("id"))},

            success: function (order_id) {//ajax请求成功后触发的方法
                alert("付款成功");
                $('#' + order_id).remove();

            }
        }
    );
}