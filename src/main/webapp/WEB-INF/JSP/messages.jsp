<h1> Messages </h1>
<div>
	<c:forEach items="${messages}" var="message">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/${message.users.sender.avatar}"></img></span>
			<a href="${root}/user/profile?id=${message.users.sender.id}">
				<span id="title">${message.users.sender.username}</span>
			</a>
			<span> 

			</span>
		</div>
	</c:forEach>
</div>