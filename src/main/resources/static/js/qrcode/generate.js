$(document).ready(function(){
    function makeCode(url) {
        $("#qrcode").qrcode({
            render: "canvas",
            text: url,
            width : "300",               //二维码的宽度
            height : "300",              //二维码的高度
            background : "#ffffff",      //二维码的后景色
            foreground : "#000000",      //二维码的前景色
            src: 'img/qrcode/logo.png'   //二维码中间的图片
        });
    }
    function createQrcode () {
        var url = 'HTTPS://QR.ALIPAY.COM/FKX02471VWUBQCUNFJ3AFA?t=1561082817322';//$('#url').val();
        makeCode(url);
    }
    createQrcode();
});