jQuery(document).ready(function($){
	var $accept_terms = $('#accept-terms'),
		$form_modal = $('.cd-user-modal'),
		$form_login = $form_modal.find('#cd-login'),
		$form_signup = $form_modal.find('#cd-signup'),
		$form_modal_tab = $('.cd-switcher'),
		$tab_forget = $('.cd-forget')
		$tab_login = $form_modal_tab.children('li').eq(0).children('a'),
		$tab_signup = $form_modal_tab.children('li').eq(1).children('a'),
		$main_nav = $('.main_nav'),

		$input_login_username = $form_login.find('#signin-username'),
		$input_login_password = $form_login.find('#signin-password'),

		$input_signup_username = $form_signup.find('#signup-username'),
		$input_signup_password1 = $form_signup.find('#signup-password1'),
		$input_signup_password2 = $form_signup.find('#signup-password2'),
		$input_signup_email = $form_signup.find('#signup-email');

		//正则验证
		function check_sinup_username(){
			if($input_signup_username.val().search(/[\u4e00-\u9fa5]/) != -1 || $input_signup_username.val() == ""){
				$input_signup_username.addClass("input-error");
				$input_signup_username.parent().next().removeClass("hidden");
			}
			else{
				$input_signup_username.removeClass("input-error");
				$input_signup_username.parent().next().addClass("hidden")
			}
		}
		function check_sinup_password1(){
			if($input_signup_password1.val().search(/^.{6,20}$/) == -1 || $input_signup_username.val() == ""){
				$input_signup_password1.addClass("input-error");
				$input_signup_password1.parent().next().removeClass("hidden");
			}
			else{
				$input_signup_password1.removeClass("input-error");
				$input_signup_password1.parent().next().addClass("hidden")
			}
		}
		function check_sinup_password2(){
			if($input_signup_password2.val() != $input_signup_password1.val()){
				$input_signup_password2.addClass("input-error");
				$input_signup_password2.parent().next().removeClass("hidden");
			}
			else if($input_signup_password2.val() == ""){
				$input_signup_password2.addClass("input-error");
			}
			else{
				$input_signup_password2.removeClass("input-error");
				$input_signup_password2.parent().next().addClass("hidden")
			}
		}
		function check_sinup_email(){
			if($input_signup_email.val().search(/^.+@.+$/) == -1 || $input_signup_email.val() == ""){
				$input_signup_email.addClass("input-error");
				$input_signup_email.parent().next().removeClass("hidden");
			}
			else{
				$input_signup_email.removeClass("input-error");
				$input_signup_email.parent().next().addClass("hidden")
			}
		}
	
		$input_signup_username.on('blur',check_sinup_username);
		$input_signup_password1.on('blur',check_sinup_password1);
		$input_signup_password2.on('blur',check_sinup_password2);
		$input_signup_email.on('blur',check_sinup_email);


		function signup_confirm(){
			var form_confirm = confirm("是否确认提交注册信息")
			if(form_confirm){
				check_sinup_username();
				check_sinup_password1();
				check_sinup_password2();
				check_sinup_email();
				if(!$accept_terms.is(":checked")){
					alert("请同意用户协议");
					return false;
				}
				else if($input_signup_username.hasClass("input-error") || $input_signup_password1.hasClass("input-error") || $input_signup_password2.hasClass("input-error") || $input_signup_email.hasClass("input-error")){
					alert("注册信息不正确");
					return false;
				}
				else return true;
			}
			else{
				return false;
			}
		}

		$form_signup.find('.cd-form').on('submit',signup_confirm);

	//弹出窗口
	$tab_forget.on('click', function(event){
		alert("待完成");
		console.log($input_signup_password1.html());
		return false;
	});

	$main_nav.on('click', function(event){

		if( $(event.target).is($main_nav) ) {
			// on mobile open the submenu
			$(this).children('ul').toggleClass('is-visible');
		} else {
			// on mobile close submenu
			$main_nav.children('ul').removeClass('is-visible');
			//show modal layer
			$form_modal.addClass('is-visible');	
			//show the selected form
			( $(event.target).is('.cd-signup') ) ? signup_selected() : login_selected();
		}

	});

	//关闭弹出窗口
	$('.cd-user-modal').on('click', function(event){
		if( $(event.target).is($form_modal) || $(event.target).is('.cd-close-form') ) {
			$form_modal.removeClass('is-visible');
		}	
	});
	//使用Esc键关闭弹出窗口
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		$form_modal.removeClass('is-visible');
	    }
    });

	//切换表单
	$form_modal_tab.on('click', function(event) {
		event.preventDefault();
		( $(event.target).is( $tab_login ) ) ? login_selected() : signup_selected();
	});

	function login_selected(){
		$form_login.addClass('is-selected');
		$form_signup.removeClass('is-selected');
		$tab_login.addClass('selected');
		$tab_signup.removeClass('selected');
	}

	function signup_selected(){
		$form_login.removeClass('is-selected');
		$form_signup.addClass('is-selected');
		$tab_login.removeClass('selected');
		$tab_signup.addClass('selected');
	}

});


//credits http://css-tricks.com/snippets/jquery/move-cursor-to-end-of-textarea-or-input/
jQuery.fn.putCursorAtEnd = function() {
	return this.each(function() {
    	// If this function exists...
    	if (this.setSelectionRange) {
      		// ... then use it (Doesn't work in IE)
      		// Double the length because Opera is inconsistent about whether a carriage return is one character or two. Sigh.
      		var len = $(this).val().length * 2;
      		this.setSelectionRange(len, len);
    	} else {
    		// ... otherwise replace the contents with itself
    		// (Doesn't work in Google Chrome)
      		$(this).val($(this).val());
    	}
	});
};