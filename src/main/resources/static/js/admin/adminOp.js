jQuery(document).ready(function($){
    $op_button = $('#clear');
    $op_button.on("click", clear);
});

function clear() {
    $('.check_input').val("");
}