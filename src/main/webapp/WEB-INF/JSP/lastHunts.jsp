<h2 class="row" id="lasthuntTitle"> Latest Hunts </h2>
<div>
	<c:forEach items="${hunts}" var="hunt">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/defaultAvatar.png"></img></span>
			<a href="${root}/hunt/?id=${hunt.id}">
				<span id="title">${hunt.name}</span> <span id="creator">by <c:out value="${hunt.creator.username}"/></span>
			</a>
		</div>
	</c:forEach>
</div>