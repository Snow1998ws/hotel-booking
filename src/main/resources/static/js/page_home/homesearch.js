$(document).ready(function()
{
    $titleSearch=$('.title_search');
    $titleSearch.keydown(function (event) {
        if(event.keyCode == 13){
            var res=$(".title_search").val();
            alert(res);
            $.ajax(
                {
                    type:"POST",
                    url:"/homeSearch",
                    dataType:'json',
                    data:{foss:res},
                    success:function (result) {

                    }
                }
            );
        }
    })
});