<html>

    <%@include file="header.jsp" %>    
    <body>
        <%
                            String attribut = (String) request.getAttribute("test");
                            out.println( attribut );
        %>
    	<%@include file="/Resources/HTML/pageHeader.html"%>
    	<%@include file="/Resources/HTML/login.html"%>

    </body>
</html>