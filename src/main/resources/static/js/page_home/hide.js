jQuery(document).ready(function($){
    var $tab_log = $('.Titem2').children('a'),
        $part_log = $('.log');
        $cancel = $('.cancel');
	  $tab_log.on('click', function(event){
		  $part_log.animate({left:'0'},1000);
    });
    $cancel.on('click', function(event){
		  $part_log.animate({left:'-100vw'},1000);
    });
});