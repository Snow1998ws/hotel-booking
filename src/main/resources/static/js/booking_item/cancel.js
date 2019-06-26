jQuery(document).ready(function($){
    $cancel_button = $('.booking_item_right_tab');
    $cancel_button.on("click",function(){
        // console.log($(this).parents('.booking_item_content'));s
        var cancel = confirm("是否删除当前信息")
        if(cancel){
            $(this).parents('.booking_item_content').remove();
        }else{
            
        }
    });
});