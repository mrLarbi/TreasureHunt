	<h2> Messages </h2>
<div id="sndMsgButton" class="container">
<c:choose>
	<c:when test="${isSameUser eq false}">
		<div class="container-fluid">
			<textarea class="form-control" rows="5"></textarea>
			<a style="margin:20px" class="btn btn-primary"> Send Message</a>
		</div>
	</c:when>
	<c:otherwise>
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
	</c:otherwise>
</c:choose>
</div>