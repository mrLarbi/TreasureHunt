	<h2> Messages </h2>
<div id="sndMsgButton" class="container">
<c:choose>
	<c:when test="${isSameUser eq false}">
		<div class="container-fluid">
			<form id="messageForm" class="rounded blackTransbg"
				  action="${root}/user/message_to" method="post">
				<div class="form-group">
					<label for="to">Receiver:</label>
					<input required class="form-control" name="to" id="to" placeholder="Enter friend's username"/>
				</div>
				<div class="form-group">
					<label for="content">Message:</label>
            <textarea required form="messageForm" rows="3" cols="40"
					  class="form-control" name="content" id="content"/>
					Enter your content.
					</textarea>
				</div>
				<div class="text-center">
					<input type="submit" class="btn btn-default" value="Send"/>
					<a class="btn btn-primary btn-default" href="${root}/user/profile">Cancel</a>
				</div>
			</form>
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