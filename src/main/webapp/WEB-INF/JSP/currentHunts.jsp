<h1> Current Hunts </h1>
<div>
	<c:forEach items="${currenthunts}" var="hunter">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/defaultAvatar.png"></img></span>
			<a href="${root}/hunt/?id=${hunter.hunting.hunt.id}">
				<span id="title">${hunter.hunting.hunt.id.name}</span> <span id="creator">by <c:out value="${hunter.hunting.hunt.id.creator.username}"/></span>
			</a>
		</div>
	</c:forEach>
</div>