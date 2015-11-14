<h2> Friends </h2>
<div class = " scrollable col-md-offset-1" style="height:100">
	<c:forEach items="${friends}" var="friend">
		<div id ="lasthunt" class="row">
			<a href="${root}/user/profile?id=${friend.friends.agent.id}">
				<h3 id="friendUName"><img style="margin-right:10px"
										  src="${root}/Resources/Images/${friend.friends.agent.avatar}"/>
										  ${friend.friends.agent.username}
				</h3>
			</a>
		</div>
	</c:forEach>
</div>