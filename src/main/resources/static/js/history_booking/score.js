$(document).ready(function(){
    var $score_page = $('.score_page'),
        $open_score = $('.open_score'),
        $score_cancel = $('.score_cancel');

    function open_score() {   
        $score_page.animate({right:'0px'},500);
    }
    
    function close_score() {   
        $score_page.animate({right:'-250px'},300);
    }

    $open_score.on('click',open_score);
    $score_cancel.on('click',close_score);
    

    $('.star1').on('click',function(){
        alert("1星");
        close_score();
    });
    $('.star2').on('click',function(){
        alert("2星");
        close_score();
    });
    $('.star3').on('click',function(){
        alert("3星");
        close_score();
    });
    $('.star4').on('click',function(){
        alert("4星");
        close_score();
    });
    $('.star5').on('click',function(){
        alert("5星");
        close_score();
    });
});

