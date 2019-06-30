$(document).ready(function(){
    var map = new AMap.Map('container', {
    resizeEnable: true, //是否监控地图容器尺寸变化
    zoom: 100 //初始地图级别
    });

    var geocoder,marker,latlng;
    function regeoCode() {
        if(!geocoder){
            geocoder = new AMap.Geocoder({
                city: "010", //城市设为北京，默认：“全国”
                radius: 1000 //范围，默认：500
            });
        }
        if(!marker){
            marker = new AMap.Marker();
            map.add(marker);
        }
        marker.setPosition(latlng);
    }

    map.on('click',function(e){
        latlng = e.latlng;
        regeoCode();
    });

    function mapTo(){
        let flag_error = 0;
        let lat = $('.map_lat_input').val();
        let val_lat = parseFloat(lat);
        if(val_lat <= -180 || val_lat >= 180){
            flag_error = 1;
            $('.map_lat_input').val("非法经度");
        }
        let lng = $('.map_lng_input').val();
        let val_lng = parseFloat(lng);
        if(val_lng <= -90 || val_lng >= 90){
            flag_error = 1;
            $('.map_lat_input').val("非法纬度");
        }
        if(flag_error){
            flag_error = 0;
            return 0;
        }
        else{
            map.panTo([lat, lng]);
            latlng=[lat, lng];
            regeoCode();
        }
    }

    $(".map_btn").on("click", mapTo);

    $(".map_cancel").on("click", function(){
        $(".map_content").animate({top:'-100vh'},1000);
    });
    // $(".map_icon").on("click", function(){
    //     $(".map_content").animate({top:'0vh'},1000);
    // });

    function mapSet(lat, lng){
        $('.map_lat_input').val(lat);
        $('.map_lng_input').val(lng);
        mapTo();
    }
    
    //接口输入经纬度
    // mapSet(1,1);
});