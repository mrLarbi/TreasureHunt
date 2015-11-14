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
		${ name }
	</h4>
</div>


<div>
	<h3>
		Phone
	</h3>
	<h4 class="col-md-offset-1">
		${ phone }
	</h4>
</div>

<div>
	<h3>
		ZIP Code
	</h3>
	<h4 class="col-md-offset-1">
		${ zipcode }
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
		<div class="container-fluid">
			<a style="margin-right:20px" id="changeAvatar" class="btn btn-primary"> Change Avatar</a>
			<a id="editProfile" class="btn btn-primary"> Edit Profile</a>
		</div>
	</c:when>
	<c:otherwise>
		<a class="btn-primary btn" href="${root}/user/follow?username=${username}">Follow User</a>
	</c:otherwise>
</c:choose>
</div>
</div>
