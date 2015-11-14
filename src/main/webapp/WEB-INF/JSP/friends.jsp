<h2> Friends </h2>
<div class = " scrollable col-md-offset-1" style="height:100%">
	<c:forEach items="${friends}" var="friend">
		<div id ="lasthunt" class="row">
			<a href="${root}/user/profile?id=${friend.friends.follower.id}">
				<h3 id="friendUName"><img src="${root}/Resources/Images/${friend.friends.follower.avatar}"/>
										  ${friend.friends.follower.username}
				</h3>
			</a>
		</div>
	</c:forEach>
</div>