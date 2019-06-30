jQuery(document).ready(function($){
    var $btn_booking_check = $('.list_content_btn'),
        $part_booking_check = $('.booking_check'),
        $cancel = $('.booking_check_cancel');
	  $btn_booking_check.on('click', function(event){
		  $part_booking_check.animate({left:'0'},1000);
    });
    $cancel.on('click', function(event){
		  $part_booking_check.animate({left:'-100vw'},1000);
    });
});