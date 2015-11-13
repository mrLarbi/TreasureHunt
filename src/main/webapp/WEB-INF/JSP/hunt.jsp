<html>
    <%@include file="header.jsp" %>
    
    <div id="islogged" data="${logged}"></div>

    <header>
	<div class="blackTransbg text-center container-fluid">
		<h1 id="htitledisplay" data="${hunt.id}">
			<span><c:out value="${hunt.name}"/>, by <c:out value="${hunt.creator.username}"/></span>
		</h1>
	</div>
	</header>

    <body>
        <div class = "container-fluid">
        	<div class="row">
				<div id="mapdisplay" class="col-xs-offset-1 col-md-5 rounded"></div>
				<div id="list" class="col-xs-offset-1 col-md-5 container-fluid blackTransbg rounded">
					<div>
		 				<ol id="plistdisplay">
		 					<c:forEach items="${hunt.coordinates}" var="point">
		 					<li><c:out value="${point.name}"/></li>    
 							</c:forEach>
						</ol> 
					</div>
				</div>
			</div>
			<div id="btngrp" class="row">

				<c:choose>
  					<c:when test="${logged=='true'}">
  						<div class="text-center">
							<a href="${root}/user/profile">
								<button class="btn btn-default btnclass">Return to profile</button>
							</a>
						</div>
  					</c:when>
  					<c:otherwise>
  						<div class="text-center">
							<a href="${root}/home" class="btn btn-default btnclass">
								Return to home
							</a>
						</div>
  					</c:otherwise>
				</c:choose>
			</div>
        </div>
    </body>
</html>