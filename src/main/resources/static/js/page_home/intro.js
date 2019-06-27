jQuery(document).ready(function($){
    var $right_arrow = $('.right'),
        $left_arrow = $('.left'),
        $intro_pic1 = $('.intro_pic').eq(0),
        $intro_pic2 = $('.intro_pic').eq(1),
        $intro_pic3 = $('.intro_pic').eq(2),
        Timer;
        intro_picArr = new Array($intro_pic1,$intro_pic2,$intro_pic3)
        pos = 2;
    $right_arrow.on('click', function(event){
      intro_picArr[pos].animate({opacity: '0'},1000);
      intro_picArr[pos].css('z-index','0');
      pos = (pos+1)%3;
      intro_picArr[pos].animate({opacity: '1'},1000);
      intro_picArr[pos].css('z-index','1');
    });
    $left_arrow.on('click', function(event){
      intro_picArr[pos].animate({opacity: '0'},1000);
      intro_picArr[pos].css('z-index','0');
      pos = (pos-1+3)%3;
      intro_picArr[pos].animate({opacity: '1'},1000);
      intro_picArr[pos].css('z-index','1');
    });

    t = setInterval("change()", 5000);
});

function change(){
  intro_picArr[pos].animate({opacity: '0'},1000);
  intro_picArr[pos].css('z-index','0');
  pos = (pos-1+3)%3;
  intro_picArr[pos].animate({opacity: '1'},1000);
  intro_picArr[pos].css('z-index','1');
}