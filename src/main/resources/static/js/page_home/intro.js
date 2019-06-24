jQuery(document).ready(function($){
    var $right_arrow = $('.triangle').eq(0),
        $left_arrow = $('.triangle').eq(1);
        $intro_pic1 = $('.intro_pic').eq(0);
        $intro_pic2 = $('.intro_pic').eq(1);
        $intro_pic3 = $('.intro_pic').eq(2);
        intro_picArr = new Array($intro_pic1,$intro_pic2,$intro_pic3)
        pos = 2;
    $right_arrow.on('click', function(event){
      console.log((pos+1)%3);
      intro_picArr[pos].animate({opacity: '0'},1000);
      pos = (pos+1)%3;
      intro_picArr[pos].animate({opacity: '1'},1000);
    });
    $left_arrow.on('click', function(event){
      intro_picArr[pos].animate({opacity: '0'},1000);
      pos = (pos-1+3)%3;
      intro_picArr[pos].animate({opacity: '1'},1000);
    });
});