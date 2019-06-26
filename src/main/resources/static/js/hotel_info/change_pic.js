jQuery(document).ready(function($){
    var $right_arrow = $('.right'),
        $left_arrow = $('.left'),
        $hotel_info_pic0 = $('.hotel_info_pic').eq(0),
        $hotel_info_pic1 = $('.hotel_info_pic').eq(1),
        $hotel_info_pic2 = $('.hotel_info_pic').eq(2),
        $hotel_info_pic3 = $('.hotel_info_pic').eq(3),
        $hotel_info_pic4 = $('.hotel_info_pic').eq(4),
        Timer;
        hotel_info_picArr = new Array($hotel_info_pic0,$hotel_info_pic1,$hotel_info_pic2,$hotel_info_pic3,$hotel_info_pic4)
        pos = 4;
    $right_arrow.on('click', function(event){
      hotel_info_picArr[pos].animate({opacity: '0'},1000);
      hotel_info_picArr[pos].css('z-index','0');
      pos = (pos+1)%5;
      hotel_info_picArr[pos].animate({opacity: '1'},1000);
      hotel_info_picArr[pos].css('z-index','1');
    });
    $left_arrow.on('click', function(event){
      hotel_info_picArr[pos].animate({opacity: '0'},1000);
      hotel_info_picArr[pos].css('z-index','0');
      pos = (pos-1+5)%5;
      hotel_info_picArr[pos].animate({opacity: '1'},1000);
      hotel_info_picArr[pos].css('z-index','1');
    });
});