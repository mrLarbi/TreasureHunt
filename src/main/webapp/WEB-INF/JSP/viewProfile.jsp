<html>
	<%@include file="header.jsp" %>
	<body class="slightlycentered">
		<ul class="nav nav-pills blackTransbg">
		  <li class="active"><a data-toggle="pill" href="#profile">Profile</a></li>
		  <li ><a data-toggle="pill" href="#current">Current Hunts</a></li>
		  <li ><a data-toggle="pill" href="#created">Created Hunts</a></li>
		  <li ><a data-toggle="pill" href="#messages">Messages</a></li>
		  <li ><a data-toggle="pill" href="#friends">Friends</a></li>
		</ul>

		<div class="tab-content blackTransbg">
			<div id="profile" class="tab-pane fade in active">
				<%@include file="profile.jsp" %>
		  	</div>
		  	<div id="current" class="tab-pane fade">
		    	<%@include file="currentHunts.jsp" %>
		  	</div>
		  	<div id="created" class="tab-pane fade">
		    	<%@include file="createdHunts.jsp" %>
		  	</div>
		  	<div id="messages" class="tab-pane fade">
		    	<%@include file="messages.jsp" %>
		  	</div>
		  	<div id="friends" class="tab-pane fade">
		    	<%@include file="friends.jsp" %>
		  	</div>
		</div>
	</body>
</html>