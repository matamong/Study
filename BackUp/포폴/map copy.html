<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Animal Bridge Map</title>
</head>
<body>
    <div id="map" style="width:100%;height:350px;"></div>
    <p><em>지도를 클릭해주세요!</em></p> 
    <p id="result"></p>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=36e76508dc234f0595cefddb47215aac"></script>

    <script>
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = { 
                center: new kakao.maps.LatLng(37.54699, 127.09598), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };
        
        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        
        var imageSrc = 'mark1.png', // 마커이미지의 주소입니다    
            imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
            imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
              
        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
            markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다
        
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: map.getCenter(), 
            image: markerImage // 마커이미지 설정 
        });
        
        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);  
        
        //지도에 클릭 이벤트를 등록합니다
        //지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
        kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
         
         // 클릭한 위도, 경도 정보를 가져옵니다 
         var latlng = mouseEvent.latLng; 
         
         // 마커 위치를 클릭한 위치로 옮깁니다
         marker.setPosition(latlng);
         
         var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
         message += '경도는 ' + latlng.getLng() + ' 입니다';
         
         var resultDiv = document.getElementById('result'); 
         resultDiv.innerHTML = message;
         
        });
        </script>

</body>
</html>