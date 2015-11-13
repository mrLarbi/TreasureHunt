<html>
    <%@include file="header.jsp" %>    
    <body>
    	<%@include file="/Resources/HTML/pageHeader.html"%>
    	<div id="welcome" class="container-fluid">
    		<div class="row">
				<div id="lastestHuntsContainer" class="container col-xs-offset-1 col-md-5 blackTransbg rounded">
					<%@include file="lastHunts.jsp"%>
				</div>
				<div id="loginFormContainer" class="container col-xs-offset-1 col-md-3"
						<c:if test="${signed eq true}">
							style="visibility: hidden"
						</c:if>
						>
						<c:set var ="url" value="${root}/login"/>
					<div class="alert-danger">
						${error}
					</div>
					<form id="loginForm" class="rounded blackTransbg" action="<c:out value="${pageContext.response.encodeURL(url)}"/>" method="post">
						<div class="form-group">
							<label for="email">Login</label>
							<input class="form-control" name="login" id="email" placeholder="Username or email"/>
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" class="form-control" name="password"
								   id="password" placeholder="Enter password"/>
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-default">Sign In</button>
							<a class="btn btn-primary" href="register">Sign Up</a>
						</div>
					</form>
				</div>
			</div>
		</div>
    </body>
</html>