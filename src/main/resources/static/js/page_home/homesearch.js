jQuery(document).ready(function($){
    hotel_page(1);
    var current_page, total_page,  search_url;
    var search_flag = 0;
    var $btn_jump_last = $('#jump_last');
    var $btn_jump_next = $('#jump_next');

    $titleSearch=$('.title_search');
    $titleSearch.keydown(function (event) {
        if(event.keyCode == 13){
            var pageNum=1;
            HomeSearch(pageNum);
        }
    });
    $btn_jump_last.on('click',jump_last);
    $btn_jump_next.on('click', jump_next);
});
function hotel_page(pageNum) {
    search_flag = 0;
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
    search_flag = 1;
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
    if (search_flag==1)
        searchHotel(current_page - 1);
    else if(search_flag==0)
        hotel_page(current_page - 1);
    else if(search_flag==2)
        HomeSearch(current_page-1)
    $(".jump_num").attr("value", current_page - 1);
}
function jump_next() {
    if (current_page >= total_page)
        return false;
    else
    {
        if (search_flag==1)
            searchHotel(current_page + 1);
        else if(search_flag==0)
            hotel_page(current_page + 1);
        else if(search_flag==2)
            HomeSearch(current_page+1);
        $(".jump_num").attr("value", current_page + 1);
    }
}

function jump_page() {
    if (total_page < $(".jump_num").val()) {
        alert("超过跳转的最大页数!");
        $(".jump_num").attr("value", 1)
        return false;
    }
    else
    {
        if (search_flag==1)
            searchHotel($(".jump_num").val());
        else if(search_flag==0)
            hotel_page($(".jump_num").val());
        else if(search_flag==2)
        {
            var tmp=HomeSearch($(".jump_num").val());
        }
    }
}

//上面搜索框的内容
function HomeSearch(pageNum)
{

    search_flag=2;
    $(".jump_num").attr("value", pageNum);
    var res=$(".title_search").val();
    var result=true;
    $.ajax({
        type: "post",
        url: "/homeSearch",
        data: {foss:res,page:pageNum},
        dataType: 'json',
        async:false,
        success: function (hotels) {
            window.location.href="/homesearchhref";
            if(hotels.length<=1)
            {
                alert("超过跳转页数范围")
                result=false;
            }
            else
            {
                alert(res);
                $('.item_block_small').remove();
                var add_htmls = "";
                var i=0;
                for (i=0;i<hotels.length-1;i++) {
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
                total_page=hotels[hotels.length-1].hScore;
            }
        }
    });

    return result;
}