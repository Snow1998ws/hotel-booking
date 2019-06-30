jQuery(document).ready(function($){
    $op_button = $('.booking_item_op_button');
    $op_button.on("click",readOrder);
});

function readOrder() {
    $.ajax(
        {
            type: "POST",
            url: "/viewOrder_info",
            dataType: 'json',
            data: {infoss:($(this).attr("name"))},

            success: function (hotel_id) {//ajax请求成功后触发的方法
                window.location.href="/hotelinfo/"+hotel_id;
                //$("#order_id").empty();
                //alert(order_id)
            }
        }
    );
}