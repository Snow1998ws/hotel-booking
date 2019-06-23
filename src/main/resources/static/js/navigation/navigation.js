$(document).ready(function(){
    // 初始已隐藏
    var flag_hided= 1;
    $(".hidebar").click(function(){
        if(flag_hided){
            $(".page").animate({left:'0'});
            flag_hided= 0}
        else{
            $(".page").animate({left:'-255px'});
            flag_hided= 1}
    });
});