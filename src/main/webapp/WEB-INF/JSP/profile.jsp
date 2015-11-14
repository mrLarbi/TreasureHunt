<div class = "container-fluid">
<div>
	<h3>
		Email
	</h3>
	<h4 class="col-md-offset-1">
		${ email }
	</h4>
</div>

<div>
	<h3>
		Name
	</h3>
	<h4 class="col-md-offset-1">
		<a href="#" id="pUsername" data-type="text">${name}</a>
	</h4>
</div>

<div>
	<h3>
		Phone
	</h3>
	<h4 class="col-md-offset-1">
		<a href="#" id="pPhone" data-type="text">${phone}</a>
	</h4>
</div>

<div>
	<h3>
		ZIP Code
	</h3>
	<h4 class="col-md-offset-1">
		<a href="#" id="pZIP" data-type="text">${zipcode}</a>
	</h4>
</div>

<div>
	<h3>
		Gender
	</h3>
	<h4 class="col-md-offset-1">
		${ gender }
	</h4>
</div>

<div id="profileButton" class="container">
<c:choose>
	<c:when test="${isSameUser eq true}">
		<div class="row">
			<div class="container-fluid">
			<form action="/user/editProfile" method="post" enctype="multipart/form-data">
			<button style="margin-right:20px" id="changeAvatar" class="btn btn-primary" type="sumbit"> Change Avatar</button>
			<div style="display: inline-block" class="col-md-offset-1 form-group">
    			<input type="file" name="inputAvatar" id="inputAvatar" accept="image/*">
  			</div>
  			</form>
  			</div>
  			<div class="container-fluid">
			<a id="editProfile" class="btn btn-primary"> Edit Profile</a>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<a class="btn-primary btn" href="${root}/user/follow?username=${username}">Follow User</a>
	</c:otherwise>
</c:choose>
</div>
</div>
