$.fn.editable.defaults.mode = 'inline';

var map;
var addedPoints = [];
var markers = [];

$(document).ready(function () {

	$('#create').prop('disabled', true);
	$('head').append('<link />');

	$('#htitle').replaceWith( "<a id=\"huntTitle\" href=\"#\">New Hunt</a>" );
	$('#huntTitle').addClass("text-center");

   	$('#huntTitle').editable({
   		type: 'text',
       	title: 'Enter hunt name',
       	placeholder: 'New Hunt',
       	placement: 'right',
       	inputclass: 'editableInput'
    });

	$( "#create" ).click(function() {
  		var create = {};
  		var points = {};
  		var i;
  		for(i = 0; i < addedPoints.length; i++) {
  			points[i] = {}
  			points[i].name = addedPoints[i].id;

  			var lat = addedPoints[i].id.split(",")[0];
  			var lng = addedPoints[i].id.split(",")[1];

  			points[i].lat = lat;
  			points[i].lng = lng;
  		}

		create["name"] = $('#huntTitle').text();
		console.log($('#huntTitle').text());
  		create["points"] = points;

  		$.post("/TreasureHunt/user/createhunt", create, function(response) {});
  		console.log(JSON.stringify(create));
	});
});

function initialize() {
        var mapCanvas = document.getElementById('map');
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
		
		google.maps.event.addListener(map, 'click', function(event) {
   			confirmPoint(event.latLng);
		});
}

google.maps.event.addDomListener(window, 'load', initialize);

function confirmPoint(location) {

	var geocoder = new google.maps.Geocoder();
	geocoder.geocode( {
	    "latLng": location
	 }, 
	 function (results, status) {
	     if (status == google.maps.GeocoderStatus.OK) {
	         var lat = results[0].geometry.location.lat(),
	             lng = results[0].geometry.location.lng(),
	             placeName = results[0].address_components[0].long_name,
	             latlng = new google.maps.LatLng(lat, lng);
	             if(window.confirm("Do you want to add the point " + results[0].formatted_address + "?")) {
	             	var newPoint = {id:lat+","+lng, name:results[0].formatted_address};
	             	addedPoints.push(newPoint);
	             	placeMarker(location);
	             	printPoints();
	             }
	     }
	 });
};

function placeMarker(location) {
    var marker = new google.maps.Marker({
        position: location, 
        map: map
    });

    markers.push(marker);
}

function printPoints() {
	$("#plist").empty();
	var i;
	for(i = 0; i < addedPoints.length; i++) {
		newPoint = document.createElement("li");
		newPoint.setAttribute('id', 'point' + i);

		var name = addedPoints[i].name;
		newPoint.addEventListener("click", function(event){
        		var index = event.target.id.slice(-1);
        		deletePoint(index);
		});
		newPoint.innerHTML = name;
		$("#plist").append(newPoint);
	}

	if(addedPoints.length === 0) {
		$('button').prop('disabled', true);
		notice = document.createElement('h2');
		notice.innerHTML = 'Insert your points here by clicking on the map...';
		$("#plist").append(notice);
	} else {
		$('button').prop('disabled', false);
	}
}

function deletePoint(p) {
	if(window.confirm("Are you sure you want to delete this point?")) {
    		addedPoints.splice(p, 1);
    		markers[p].setMap(null);
    		markers.splice(p, 1);
	}
	printPoints();
}