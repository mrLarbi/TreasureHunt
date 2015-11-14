<h2> Messages </h2>
<div class = " scrollable col-md-offset-1">
	<c:forEach items="${messages}" var="message">
		<div id ="lasthunt" class="row">
			<a href="${root}/user/profile?id=${message.users.sender.id}">
				<h3 id="msgTitle"><img style="margin-right:10px" src="${root}/Resources/Images/${message.users.sender.avatar}"/>${message.users.sender.username}</h3>
			</a>
		</div>
		<div id="msgContent" class = "col-md-offset-3 row">
			${message.content}
		</div>
	</c:forEach>
</div>