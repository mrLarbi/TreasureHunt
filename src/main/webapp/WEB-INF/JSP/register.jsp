<html>
    <%@include file="header.jsp" %>
    <body>
        <%@include file="/Resources/HTML/pageHeader.html"%>

        <c:set var ="url" value="${root}/register"/>

        <div class = "container-fluid">
            <form id="registerForm" class="form-horizontal" method="POST"
                action="<c:out value="${pageContext.response.encodeURL(url)}"/>">
                <div id="infoBlock" class="row">
                    <div id="mandatory" class = "blackTransbg rounded container-fluid col-xs-offset-3 col-md-6">
                        <h2>Account Information</h2>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="username">Username</label>
                            <div class="col-xs-3 col-md-6">
                                <input type="text" class="form-control" name="username" id="username">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="password">Password</label>
                            <div class="col-xs-3 col-md-6">
                                <input type="password" class="form-control" name="password" id="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="confirmation">Confirmation</label>
                            <div class="col-xs-3 col-md-6">
                                <input type="password" class="form-control" name="confirmation" id="confirmation">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="email">Email</label>
                            <div class="col-xs-3 col-md-6">
                                <input type="email" class="form-control" name="email" id="email">
                            </div>
                        </div>
                    </div>
                </div>
                <div id="infoBlock" class="row">
                    <div id="optional" class = "blackTransbg rounded container-fluid col-xs-offset-3 col-md-6">
                        <h2>Personal Information</h2>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="realname">Name</label>
                            <div class="col-xs-3 col-md-6">
                                <input type="text" class="form-control" name="realname" id="realname">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="gender">Gender</label>
                            <div id="genderBoxes" class="col-xs-3 col-md-6">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name = "gender" id="genderbox" value="M"> Male
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="genderbox" name = "gender" value="F"> Female
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name ="gender" id="genderbox" checked="true" value="N"> Other
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="phone">Phone</label>
                            <div class="col-xs-3 col-md-6">
                                <input type="text" class="form-control" name="phone" id="phone">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-2" for="zcode">ZIP Code</label>
                            <div class="col-xs-3 col-md-6">
                                <input type="text" class="form-control" name="zcode" id="zcode">
                            </div>
                        </div>
                    </div>
                </div>
                <div id="buttonsBlock" class="row">
                    <div id="buttons" class="text-center container-fluid col-xs-offset-7 col-md-2">
                        <a class="btn btn-default" href="${root}/home">Cancel</a>
                        <button id="regButton" type="sumbit" class="btn btn-primary">Register</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
