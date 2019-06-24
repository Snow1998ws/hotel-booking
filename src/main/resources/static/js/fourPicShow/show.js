$(document).ready(function() {
    $('.inbox').each(function(x) {
        $(this).children('li:first').show();
		$(this).children('li').each(function(y) {
            $(this).css('background-position', (4 - x) * 250 + 'px 0px');
        })
    });
	$('.text:first').css('color','#fff');
    $('.text').hover(function() {
        i = $('.text').index($(this));
		$('.text').css('color','#666');
		$('.text').eq(i).css('color','#fff');
        $('.inbox').each(function(x) {
			setTimeout(function(){
				$('.inbox').eq(x).children('li').each(function(y) {
					if (y == i) {
						$(this).fadeIn();
					} else {
						$(this).hide();
					}
				})	
			},400-x*100);            
        });
    })
})