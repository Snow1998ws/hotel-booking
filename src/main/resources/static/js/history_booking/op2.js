$(document).ready(function(){
    $score = $('.booking_item_op_button')
});

function cancelOrder() {
    var cancel = confirm("是否删除本条历史记录")
    if(cancel)
    {
        $.ajax(
            {
                type: "POST",
                url: "/delehistory",
                dataType: 'json',
                data: {infoss:($(this).parents('.booking_item_content').attr("id"))},

                success: function (order_id) {//ajax请求成功后触发的方法
                    var $order = $('#' + order_id);
                    $order.animate({left:'-80vw', opacity: 0},1000);
                    setTimeout(function(){ $order.remove(); }, 1000);
                }
            }
        );
    }
}

function readOrder() {
    $.ajax(
        {
            type: "POST",
            url: "/viewOrder_info",
            dataType: 'json',
            data: {infoss:($(this).parents('.booking_item_content').attr("id"))},

            success: function (hotel_id) {//ajax请求成功后触发的方法
                window.location.href="/hotelinfo/"+hotel_id;
            }
        }
    );
}