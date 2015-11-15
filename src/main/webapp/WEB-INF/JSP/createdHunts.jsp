<h1> Created Hunts </h1>
<div>
	<c:forEach items="${createdhunts}" var="hunt">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/${hunt.creator.avatar}"></img></span>
			<a href="${root}/hunt?id=${hunt.id}">
				<span id="title">${hunt.name}</span> <span id="creator">by <c:out value="${hunt.creator.username}"/></span>
			</a>
		</div>
	</c:forEach>
</div>
<a href="${root}/user/createhunt">
	<button class="btn btn-default btnclass">Create</button>
</a>