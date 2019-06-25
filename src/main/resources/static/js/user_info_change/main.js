jQuery(document).ready(function($){
	var $form_change = $('#change_form'),
        $input_nick = $("input[name='nick']"),
        $input_gender = $("input[name='gender']"),
        $input_birth = $("input[name='birth']"),
        $input_city = $("input[name='city']"),
        $input_tel = $("input[name='tel']"),
        $input_mail = $("input[name='mail']"),
        $input_newpw1 = $("input[name='newpw1']"),
        $input_newpw2 = $("input[name='newpw2']"),
        $input_oldpw = $("input[name='oldpw']");

    //正则验证
    function check_newpw1(){
        if($input_newpw1.val().search(/^.{6,20}$/) == -1){
            $input_newpw1.addClass("input-error");
            $input_newpw1.next().removeClass("hidden");
        }
        else{
            $input_newpw1.removeClass("input-error");
            $input_newpw1.next().addClass("hidden")
        }
    }
    function check_newpw2(){
        if($input_newpw2.val() != $input_newpw1.val()){
            $input_newpw2.addClass("input-error");
            $input_newpw2.next().removeClass("hidden");
        }
        else{
            $input_newpw2.removeClass("input-error");
            $input_newpw2.next().addClass("hidden")
        }
    }
    function check_mail(){
        if($input_mail.val().search(/^.+@.+$/) == -1){
            $input_mail.addClass("input-error");
            $input_mail.next().removeClass("hidden");
        }
        else{
            $input_mail.removeClass("input-error");
            $input_mail.next().addClass("hidden")
        }
    }

    $input_newpw1.on('blur',check_newpw1);
    $input_newpw2.on('blur',check_newpw2);
    $input_mail.on('blur',check_mail);
});
