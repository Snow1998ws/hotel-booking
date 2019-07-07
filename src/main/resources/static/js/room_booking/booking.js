jQuery(document).ready(function($) {
    var room_id;
    var $btn_booking_check = $('.list_content_btn');
    var $btn_booking_ensure = $('.booking_check_btn');

    $btn_booking_check.on('click',book_btn);
    $btn_booking_ensure.on('click', book_ensure);
});

function book_ensure() {
    $.ajax({
        type: "POST",
        url: "/addOrder",
        data: {
            oUserId: $("#userId").val(),
            checkinTime: $("#checkin_time").val(),
            leaveTime: $("#leave_time").val(),
            totalprice: $("#booking_price").val(),
            oRoomId: room_id,
            Ispay: 'n',
            arrive: $("#arrive_time").val() + ":00",
            people: $("#booking_people").val(),
            discount: 10
        },
        dataType: "json",
        success: function (data) {
            alert("22");
            window.location.href = "/Order_Not_pay";
        },
        error:function (data) {
            alert("33");
            window.location.href = "/Order_Not_pay";
        }
    })
}

function book_btn() {
    var roomtype = $(this).parents('.room_booking_item').children('.room_booking_item_title').text();
    var price = $(this).parents('.room_booking_item').find('.room_price').children('span').eq(1).text();
    // var room_id = $(this).parents('.room_booking_item').attr('id');
    $("#booking_room").attr("value", roomtype);
    $("#booking_price").attr("value", price);
    room_id = $(this).parents('.room_booking_item').attr('id');
}

