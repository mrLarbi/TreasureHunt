<h2> Created Hunts </h2>
<div class = "col-md-offset-1 scrollable">
	<c:forEach items="${createdhunts}" var="hunt">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/${hunt.creator.avatar}"></img></span>
			<a href="${root}/hunt?id=${hunt.id}">
				<span id="title">${hunt.name}</span>
				<span id="creator">by <c:out value="${hunt.creator.username}"/>
				</span>
			</a>
		</div>
	</c:forEach>
</div>
<div style="margin-top:50px" class="container">
<a href="${root}/user/createhunt">
	<button id="createHunt" class="btn btn-primary">Create a new Hunt</button>
</a>
</div>