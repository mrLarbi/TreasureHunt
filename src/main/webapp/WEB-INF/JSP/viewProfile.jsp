<html>
	<%@include file="header.jsp" %>
	<body class="slightlycentered">

	<header>
	<div id="utitle" class="blackTransbg text-center container-fluid">
		<h2 id="username"> <img id="avatar" src="${root}/Resources/Images/defaultAvatar.png" class="userimage"/> ${username}'s profile </h2>
	</div>
	</header>

	<div id="tabs" class = "container-fluid col-md-2 blackTransbg">
		<ul class="nav nav-pills">
			<li class="active"><a data-toggle="pill" href="#profile">Profile</a></li>
			<li><a data-toggle="pill" href="#current">Current Hunts</a></li>
			<li><a data-toggle="pill" href="#created">Created Hunts</a></li>
		   	<li><a data-toggle="pill" href="#messages">Messages</a></li>
			<li><a data-toggle="pill" href="#friends">Friends</a></li>
		  	<li><a href="${root}/logout">Log out</a></li>
		</ul>
	</div>

	<div id="tabc" class = "container-fluid col-md-10 blackTransbg">

		<div class="tab-content">
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
	</div>
	</body>
</html>