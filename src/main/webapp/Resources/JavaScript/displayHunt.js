var map;
var addresses = [];

$( document ).ready(function() {
    initPointsDisplay();
});

function initPointsDisplay() {
	
	$("li").each(function() {
		var adr = $(this).text();
		addresses.push(adr);
	});

}

function initializeDisplay() {

        var mapCanvas = document.getElementById('mapdisplay');
        var mapOptions = {
          center: new google.maps.LatLng(48.8557, 2.3290),
          zoom: 12,
          minZoom: 12,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        map = new google.maps.Map(mapCanvas, mapOptions)

        var strictBounds = new google.maps.LatLngBounds(
	     new google.maps.LatLng(48.815352, 2.244400), 
	     new google.maps.LatLng(48.904310, 2.418034)
	   );

		// Listen for the dragend event
		google.maps.event.addListener(map, 'dragend', function() {
		 if (strictBounds.contains(map.getCenter())) return;

		 // We're out of bounds - Move the map back within the bounds

		 var c = map.getCenter(),
		     x = c.lng(),
		     y = c.lat(),
		     maxX = strictBounds.getNorthEast().lng(),
		     maxY = strictBounds.getNorthEast().lat(),
		     minX = strictBounds.getSouthWest().lng(),
		     minY = strictBounds.getSouthWest().lat();

		 if (x < minX) x = minX;
		 if (x > maxX) x = maxX;
		 if (y < minY) y = minY;
		 if (y > maxY) y = maxY;

		 map.setCenter(new google.maps.LatLng(y, x));
		});

		var geocoder = new google.maps.Geocoder();
		var i;
		for(i = 0; i < addresses.length; i++) {
			geocoder.geocode( { 'address': addresses[i]}, function(results, status) {
  				if (status == google.maps.GeocoderStatus.OK) {
    				var marker = new google.maps.Marker({
        				map: map,
        				position: results[0].geometry.location
    				});
  				}
			});
		}
}
google.maps.event.addDomListener(window, 'load', initializeDisplay);

function printPointsDisplay() {
	$("#plist").empty();
	var i;
	for(i = 0; i < addedPoints.length; i++) {
		newPoint = document.createElement("li");
		newPoint.setAttribute('id', 'point' + i);

		var name = addedPoints[i].name;
		newPoint.addEventListener("click", function(event){
        		var index = event.target.id.slice(-1);
        		checkPointDisplay(index);
		});
		newPoint.innerHTML = name;
		$("#plist").append(newPoint);
	}
}

function checkPointDisplay(p) {

}