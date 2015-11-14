<h2> Current Hunts </h2>
<div class = "col-md-offset-1 scrollable">
	<c:forEach items="${currenthunts}" var="hunter">
		<div id ="lasthunt" class="row">
			<span> <img src="${root}/Resources/Images/${hunter.hunting.hunt.creator.avatar}"></img></span>
			<a href="${root}/hunt?id=${hunter.hunting.hunt.id}">
				<span id="title">${hunter.hunting.hunt.name}</span>
				<span id="creator">by <c:out value="${hunter.hunting.hunt.creator.username}"/>
				</span>
			</a>
		</div>
	</c:forEach>
</div>
