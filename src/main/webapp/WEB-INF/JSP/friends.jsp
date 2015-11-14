<h1> Friends </h1>
<div>
	<c:forEach items="${friends}" var="friend">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/${friend.friends.follower.avatar}"></img></span>
			<a href="${root}/user/profile?id=${friend.friends.follower.id}">
				<span id="title">${friend.friends.follower.username}</span>
			</a>
		</div>
	</c:forEach>
</div>