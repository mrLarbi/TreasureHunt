<h2 class="row" id="lasthuntTitle"> Latest Hunts </h2>
<div>
	<c:forEach items="${hunts}" var="hunt">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/defaultAvatar.png"></img></span>
			<a href="nothingyet">
				<span id="title">Paris</span> <span id="creator">by <c:out value="${hunt.creator.username}"/></span>
			</a>
		</div>
	</c:forEach>
</div>