$(document).ready(function(){
    $('body').on('click','.result_op' ,adminManage);
    // $op_button.on("click",adminManage);
});
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
                else if (res == "1") {
                    window.location.href = "/adminHotel/" + res;
                }
                else {
                    window.location.href = "/adminOrder/" + res;
                }
            },
            error: function (msg) {
                var tmp = $(".admin_search_bar_select  option:selected").attr('value');
                if (tmp == "0") {
                    window.location.href = "adminUser/" + tmp1;
                }
            }
        }
    );
}

