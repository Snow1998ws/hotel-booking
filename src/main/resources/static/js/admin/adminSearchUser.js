jQuery(document).ready(function($){
    $op_button =$('.admin_search_bar_btn');
    $op_button.on("click",admin_search);

    $op_button1=$('.admin_search_bar_add_btn');
    $op_button1.on("click",adminAdd);
});

function admin_search() {
    $.ajax(
        {
            type: "POST",
            url: "/admin_search",
            dataType: 'json',
            data: {list: $(".admin_search_bar_select  option:selected").attr('value'),content:$('.admin_search_bar_input').val()},
            success: function (res)
            {
                var add_htmls="";
                for(i=0;i<res.length;i+=2)
                {
                    add_htmls +="<div class='result_item'><div class='result_info'>\n";
                    add_htmls +="<span class='result_info_name'>ID:</span><div class='result_info_item result_id'>"+res[i]+"</div>";
                    add_htmls +="<span class='result_info_name'>名称:</span><div class='result_info_item' >"+res[i+1]+"</div>";
                    add_htmls +="<span class='result_info_name' style='display: none;'>ID:</span><div class='result_info_item' style='display: none;'>123</div>";
                    add_htmls +="</div><div class='result_op manage'  ><div class='result_btn' >管理</div></div>";
                    add_htmls += "<div class='result_op delete'><div class='result_btn'>删除</div></div></div>"
                }
                $(".admin_search_result").html(add_htmls);
            },
            error:function (msg) {
                alert("搜索不能为空！");
            }
        }
    );
}
function adminAdd()
{
    var tmp=$(".admin_search_bar_select  option:selected").attr('value');
    window.location.href="/adminadd/"+tmp;
}