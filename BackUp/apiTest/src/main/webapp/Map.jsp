<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
    <form action="SaveMap.do" method="get">
        경도 <input type="text" name="x" id="x" readonly><br>
        위도 <input type="text" name="y" id="y" readonly><br>
        특징 <input type="text" name="title" id="title"><br>
        <input type="submit">
    </form>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=36e76508dc234f0595cefddb47215aac"></script>

    <script>
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
            mapOption = {
                center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        // 마커를 표시할 위치와 title 객체 배열입니다 
        var positions = [
            {
                title: '카카오',
                latlng: new kakao.maps.LatLng(33.450705, 126.570677)
            },
            {
                title: '생태연못',
                latlng: new kakao.maps.LatLng(33.450936, 126.569477)
            },
            {
                title: '텃밭',
                latlng: new kakao.maps.LatLng(33.450879, 126.569940)
            },
            {
                title: '근린공원',
                latlng: new kakao.maps.LatLng(33.451393, 126.570738)
            }
        ];

        // 마커 이미지의 이미지 주소입니다
        var imageSrc = 'mark1.png';

        for (var i = 0; i < positions.length; i++) {

            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(64, 69),
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption), // 마커 이미지를 생성합니다    
                imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: positions[i].latlng, // 마커를 표시할 위치
                title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                image: markerImage // 마커 이미지 
            });
        }

        var imageSrc2 = 'mark2.png';
            var imageSize2 = new kakao.maps.Size(64, 69),
                markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize2); // 마커 이미지를 생성합니다    
          
            var marker2 = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                image: markerImage2 // 마커 이미지 
            });
        //지도에 클릭 이벤트를 등록합니다
        //지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
        kakao.maps.event.addListener(map, 'click', function (mouseEvent) {

            // 클릭한 위도, 경도 정보를 가져옵니다 
            var latlng = mouseEvent.latLng;
            
            // 마커 위치를 클릭한 위치로 옮깁니다
            marker2.setPosition(latlng);

            var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
            message += '경도는 ' + latlng.getLng() + ' 입니다';

            var x = document.getElementById("x");
            x.value =  latlng.getLng();
            var y = document.getElementById("y");
            y.value = latlng.getLat();
            var resultDiv = document.getElementById('result');
            resultDiv.innerHTML = message;

        });
    </script>
</body>

</html>