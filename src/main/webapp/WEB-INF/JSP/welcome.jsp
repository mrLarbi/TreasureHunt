<html>
    <%@include file="header.jsp" %>    
    <body>
    	<%@include file="/Resources/HTML/pageHeader.html"%>
    	<div id="welcome" class="container-fluid">
    		<div class="row">
				<div id="lastestHuntsContainer" class="container col-xs-offset-1 col-md-5 blackTransbg rounded">
					<%@include file="lastHunts.jsp"%>
				</div>
				<div id="loginFormContainer" class="container col-xs-offset-1 col-md-3">
					<%@include file="/Resources/HTML/login.html"%>
				</div>
			</div>
		</div>
    </body>
</html>