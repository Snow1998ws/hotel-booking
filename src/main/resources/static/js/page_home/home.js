jQuery(document).ready(function($){
    hotel_page(1);
    var current_page, total_page;
    var $btn_jump_last = $('#jump_last');
    var $btn_jump_next = $('#jump_next');

    $btn_jump_last.on('click',jump_last);
    $btn_jump_next.on('click', jump_next);
});
function hotel_page(pageNum) {
    $.ajax({
        type: "get",
        url: "/hotellist/?pageNum=" + pageNum,
        data: {},
        dataType: 'json',
        success: function (data) {
            var add_htmls = "";
            var hotels = data.pageinfo.list;
            for (i in hotels) {
                add_htmls += "<div class='item_block_small item_row'>\n";
                add_htmls += "<a href='/hotelinfo/" + hotels[i].hId + "' ";
                add_htmls += "path='" + hotels[i].hPhoto1;
                add_htmls += "' style='background-image: url(" + hotels[i].hPhoto1 + ");' ";
                add_htmls += "class='preview'></a>\n";
                add_htmls += "<div class='item_info_1 item_row'>整套房子·2床</div>\n";
                add_htmls += "<div class='item_info_2 item_row'>" + hotels[i].hAddress + "</div>\n";
                add_htmls += "<div class='item_info_3 item_row'\n>";
                add_htmls += "<div class='xzw_starBox'><span class='show'>";
                add_htmls += "<span class='score score" + hotels[i].hScore + "'</span></span></div>\n";
                add_htmls += "<div><span class='price_now'>￥" + hotels[i].hRates + "</span>每晚</div>\n</div></div>";
            }
            $(".item_block_big").html(add_htmls);
            current_page = pageNum;
            total_page = data.pageinfo.pages;
        }
    })
}
function jump_last() {
    if (current_page <= 1)
        return false;
    hotel_page(current_page - 1);
    $(".jump_num").attr("value", current_page - 1);
}
function jump_next() {
    if (current_page >= total_page)
        return false;
    hotel_page(current_page + 1);
    $(".jump_num").attr("value", current_page + 1);

}
function searchHotel() {
    var val = parseInt($("#select_price").val()), low, high;
    if (val < 4) {
        low = 200 * val;
        high = 200 * (val + 1);
    } else {
        low = 800;
        high = 10000;
    }
    var checkin_time = $("#calendar").val(), leave_time = $("#calendar2").val(), url;
    if (checkin_time == "" || leave_time == "")
        url = "/hotelsearch/" + $('#search_city').val() + "/" + low + "/" + high;
    else
        url = "/hotelsearch/" + $('#search_city').val() + "/" + low + "/" + high +
            "/" + checkin_time + "/" + leave_time;
    $(".item_block_big").empty();
    $(".jump_content").empty();
    info = {
        "city": $('#search_city').val()
    };
    $.ajax({
        type: "post",
        url: url,
        data: info,
        dataType: 'json',
        success: function (hotels) {
            var add_htmls = "";
            for (i in hotels) {
                add_htmls += "<div class='item_block_small item_row'>\n";
                add_htmls += "<a href='/hotelinfo/" + hotels[i].hId + "' ";
                add_htmls += "path='" + hotels[i].hPhoto1;
                add_htmls += "' style='background-image: url(" + hotels[i].hPhoto1 + ");' ";
                add_htmls += "class='preview'></a>\n";
                add_htmls += "<div class='item_info_1 item_row'>整套房子·2床</div>\n";
                add_htmls += "<div class='item_info_2 item_row'>" + hotels[i].hAddress + "</div>\n";
                add_htmls += "<div class='item_info_3 item_row'\n>";
                add_htmls += "<div class='xzw_starBox'><span class='show'>";
                add_htmls += "<span class='score score" + hotels[i].hScore + "'</span></span></div>\n";
                add_htmls += "<div><span class='price_now'>￥" + hotels[i].hRates + "</span>每晚</div>\n</div></div>";
            }
            $(".item_block_big").html(add_htmls);
        }
    });
    return false;
};