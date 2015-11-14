<div class="row">
		<span> <img src="${root}/Resources/Images/defaultAvatar.png" class="userimage"></img></span>
		<span> ${ username } </span> 
	</div>


<div>
	<h2>
		Email
	</h2>
	<p>
		${ email }
	</p>
</div>

<div>
	<h2>
		Name
	</h2>
	<p>
		${ name }
	</p>
</div>


<div>
	<h2>
		Phone
	</h2>
	<p>
		${ phone }
	</p>
</div>

<div>
	<h2>
		Zipcode
	</h2>
	<p>
		${ zipcode }
	</p>
</div>

<div>
	<h2>
		Gender
	</h2>
	<p>
		${ gender }
	</p>
</div>

<c:choose>
	<c:when test="${isSameUser eq true}">
		<div class="tab-pane fade">
			<a class="btn btn-primary" href="${root}/user/profile/modification"> Modify my profile</a>
		</div>
	</c:when>
	<c:otherwise>
		<a class="btn-primary btn" href="${root}/user/follow?username=${username}">Follow</a>
	</c:otherwise>
</c:choose>

