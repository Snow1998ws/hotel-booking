$(document).ready(function(){
    var $open_score = $('.open_score'),
        $score_cancel = $('.score_cancel');

    function open_score() {
        $(this).parents('.booking_item_content').find('.score_page').animate({right:'0px'},500);
    }

    function close_score() {
        $(this).parents('.score_page').animate({right:'-250px'},300);
    }

    var res=5;
    $open_score.on('click',open_score);
    $score_cancel.on('click',close_score);
    

    $('.star1').on('click',function(){
        res=1;
        Score(res,this);
        $(this).parents('.score_page').animate({right:'-250px'},300);
    });
    $('.star2').on('click',function(){
        res=2;
        Score(res,this);
        $(this).parents('.score_page').animate({right:'-250px'},300);
    });
    $('.star3').on('click',function(){
        res=3;
        Score(res,this);
        $(this).parents('.score_page').animate({right:'-250px'},300);
    });
    $('.star4').on('click',function(){
        res=4;
        Score(res,this);
        $(this).parents('.score_page').animate({right:'-250px'},300);
    });
    $('.star5').on('click',function(event){
        res=5;
        Score(res,this);
        $(this).parents('.score_page').animate({right:'-250px'},300);
    });


});

function Score(res,Pthis) {
    var oid=$(Pthis).parents('.booking_content_box').find('.booking_item_content').attr("id");
    $.ajax(
        {
            type:'POST',
            dataType:"json",
            url:"/setScore",
            data:{foss:res,orderid:oid},
            success:function (data) {
                window.location.href="/order_bef_info";
            },
            error:function (data) {
                window.location.href="/order_bef_info";
            }
        }
    )

}

