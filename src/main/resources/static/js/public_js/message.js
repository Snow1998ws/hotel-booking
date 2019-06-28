jQuery(document).ready(function($){
    new Vue({
    el: '#left_navigation',
    data: {
        msg_a1: '/userinfo',
        msg_a2: '/order_pre_info',
        msg_a3: 'wait_for_pay_booking.html',
        msg_a4: 'history_booking.html',
        msg_a5: 'user_info_change.html'
    }
    });
    new Vue({
        el: 'head',
        data: {
            path_1: '../',
        }
    })
});