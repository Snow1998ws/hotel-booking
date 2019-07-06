jQuery(document).ready(function($){
    hotel_page(1);
    var current_page, total_page, search_flag = false, search_url;
    var $btn_jump_last = $('#jump_last');
    var $btn_jump_next = $('#jump_next');

    $btn_jump_last.on('click',jump_last);
    $btn_jump_next.on('click', jump_next);
});
function hotel_page(pageNum) {
    search_flag = false;
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
                add_htmls += "<a alt='../../img/icon/web_logo.png' href='/hotelinfo/" + hotels[i].hId + "' ";
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

function searchHotel(pageNum) {
    search_flag = true;
    $(".jump_num").attr("value", pageNum);
    var val = parseInt($("#select_price").val()), checkin_time = $("#calendar").val(),
        leave_time = $("#calendar2").val(), url, low, high;
    if (val < 0) {
        low = 0; high = 10000;
    } else if (val < 4) {
        low = 200 * val; high = 200 * (val + 1);
    } else {
        low = 800; high = 10000;
    }
    if (checkin_time == "" || leave_time == "")
        url = "/hotelsearch?city=" + $('#search_city').val() + "&low=" + low + "&high=" + high + "&pageNum=" + pageNum;
    else if(Date.parse(checkin_time)>Date.parse(leave_time)){
        alert("退房时间早于订房时间，请重新确认信息")
        return false;
    }
    else
        url = "/hotelsearch?city=" + $('#search_city').val() + "&low=" + low + "&high=" + high +
               "&checkin_time=" + checkin_time + "&leave_time=" + leave_time + "&pageNum=" + pageNum;
    $.ajax({
        type: "get",
        url: url,
        data: {},
        dataType: 'json',
        success: function (data) {
            var add_htmls = "";
            hotels = data.pageinfo.list;
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
    });
    return false;
}

function jump_last() {
    if (current_page <= 1)
        return false;
    if (search_flag)
        searchHotel(current_page - 1);
    else
        hotel_page(current_page - 1);
    $(".jump_num").attr("value", current_page - 1);
}
function jump_next() {
    if (current_page >= total_page)
        return false;
    if (search_flag)
        searchHotel(current_page + 1);
    else
        hotel_page(current_page + 1);
    $(".jump_num").attr("value", current_page + 1);
}

function jump_page() {
    if (total_page < $(".jump_num").val()) {
        alert("超过跳转的最大页数!");
        $(".jump_num").attr("value", 1)
        return false;
    }
    if (search_flag)
        searchHotel($(".jump_num").val());
    else
        hotel_page($(".jump_num").val());
}