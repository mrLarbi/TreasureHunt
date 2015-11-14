<html>
<%@include file="header.jsp" %>
<body>
<div class="container col-xs-offset-1 col-md-3">
    <form id="messageForm" class="rounded blackTransbg"
          action="${root}/user/message_to" method="post">
        <div class="form-group">
            <label for="to">Receiver:</label>
            <input required class="form-control" name="to" id="to" placeholder="Enter friend's username"/>
        </div>
        <div class="form-group">
            <label for="content">Message:</label>
            <textarea required form="messageForm" rows="3" cols="40"
                      class="form-control" name="content" id="content"/>
            Enter your content.
            </textarea>
        </div>
        <div class="text-center">
            <input type="submit" class="btn btn-default" value="Send"/>
            <a class="btn btn-primary btn-default" href="${root}/user/profile">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>