jQuery(document).ready(function($){
    $op_button = $('.booking_item_op_button');
    $op_button.on("click",PayRoom);
});

function PayRoom() {
    $.ajax(
        {
            type: "POST",
            url: "/payRoom",
            dataType: 'json',
            data: {infoss:($(this).attr("name"))},

            success: function (url) {//ajax请求成功后触发的方法
                window.location.href=url;
            }
        }
    );
}