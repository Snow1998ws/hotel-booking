$(document).ready(function(){
    $('body').on('click','.manage' ,adminManage);
    $('body').on('click', '.delete', userDelete);
    // $op_button.on("click",adminManage);
});
function userDelete() {

    var status=$(".admin_search_bar_select  option:selected").attr('value');

    var tmp = $(this).parents('.result_item').find('.result_id').text();
    if(status=="0")
    {
        alert("user");
        $(this).parents('.result_item').remove();
        $.ajax({
            type: "POST",
            url: "/delete_user?userId=" + tmp,
            dataType: 'json',
            data: {},
            success: function (data) {
                alert(data);
            }
        })
    }
    else if(status=="1")
    {
        alert("hotel")
        $(this).parents('.result_item').remove();
        $.ajax({
            type: "POST",
            url: "/delete_hotel?hotelId=" + tmp,
            dataType: 'json',
            data: {},
            success: function (data) {
                alert(data);
            }
        })
    }
    else if(status=="2")
    {
        alert("order");
        alert(tmp);
        $(this).parents('.result_item').remove();
        $.ajax({
            type: "POST",
            url: "/delete_order?orderId=" + tmp,
            dataType: 'json',
            data: {},
            success: function (data) {
                alert(data);
            }
        })
    }

}
function adminManage() {
    var tmp1=($(this).prev()).children('.result_info_item').eq(0).text();
    $.ajax(
        {
            type: "POST",
            url: "/adminManage",
            dataType: 'json',
            data: {"foss": $(this).prev().children('.result_info_item').eq(0).text()},
            success: function (res) {
                var tmp = $(".admin_search_bar_select  option:selected").attr('value');
                if (tmp == "0") {
                    window.location.href = "/adminUser/" + res;
                }
                else if (tmp == "1") {
                    window.location.href = "/adminHotel/" + res;
                }
                else {
                    window.location.href = "/adminOrder/" + res;
                }
            },
            error: function (msg) {
                var tmp = $(".admin_search_bar_select  option:selected").attr('value');
                if (tmp == "0") {
                    window.location.href = "/adminUser/" + tmp1;
                }

            }
        }
    );
}

