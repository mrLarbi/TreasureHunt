<h1> Followers </h1>
<div>
	<c:forEach items="${friends}" var="friend">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/{friend.friends.follower.avatar}"></img></span>
			<a href="${root}/user/profile?id=${friend.friends.follower.id}">
				<span id="title">${friend.friends.follower.username}</span>
			</a>
		</div>
	</c:forEach>
</div>
<h1> Followed </h1>
<div>
	<c:forEach items="${friends}" var="friend">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/{friend.friends.agent.avatar}"></img></span>
			<a href="${root}/user/profile?id=${friend.friends.agent.id}">
				<span id="title">${friend.friends.agent.username}</span>
			</a>
		</div>
	</c:forEach>
</div>