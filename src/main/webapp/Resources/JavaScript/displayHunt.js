var map;
var addresses = [];
var latlng = []

$( document ).ready(function() {
    initPointsDisplay();
});

function initPointsDisplay() {

	var i = 0;
	$("li").each(function() {
		$(this).attr('id', 'point' + i);
		var adr = $(this).text();
		addresses.push(adr);

		if($("#islogged").attr("data") == "true") {
			$(this).click(function(event){
        			var index = event.target.id;
        			checkPointDisplay(index);
			});
		}

		var geocoder = new google.maps.Geocoder();
		geocoder.geocode( { 'address': adr}, function(results, status) {
  			if (status == google.maps.GeocoderStatus.OK) {
        		latlng.push(results[0].geometry.location);
  			}
		});

		i++;
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
    		var marker = new google.maps.Marker({
        		map: map,
        		position: latlng[i],
        		title: addresses[i]
    		});
		}
}
google.maps.event.addDomListener(window, 'load', initializeDisplay);

function checkPointDisplay(p) {
	var ele = document.getElementById(p);

	var isStruck = (ele.style.getPropertyValue("text-decoration") == "line-through");

	var check = {};
	check["id"] = $("#htitledisplay").attr("data");

	check["lat"] = latlng[p.slice(-1)].lat();
	check["lng"] = latlng[p.slice(-1)].lng();

	var params = {};
	if(isStruck === false) {
		check["value"] = "true";
		params['param'] = JSON.stringify(check);
		$.post("/hunt",params, function(response) {
			if(response == "true") {
				ele.style.setProperty("text-decoration", "line-through");
			} else if ( response == "/home") {
        window.location.href = response;
      }
		});
  		console.log(JSON.stringify(check));
	} else {
		check["value"] = "false";
		params['param'] = JSON.stringify(check);
		$.post("/hunt",params, function(response) {
			if(response == "true") {
				ele.style.setProperty("text-decoration", "none");
			}
		});
  		console.log(JSON.stringify(check));
	}
}
