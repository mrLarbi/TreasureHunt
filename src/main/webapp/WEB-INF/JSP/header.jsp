<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<head>
    <title>
      ${title}
    </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href='https://fonts.googleapis.com/css?family=Dancing+Script:700' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="${root}/Resources/Bootstrap/css/bootstrap.css">
  <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
  <link rel="stylesheet" href="${root}/Resources/CSS/style.css">
  <link rel="stylesheet" href="${root}/Resources/CSS/login.css">
  <link rel="stylesheet" href="${root}/Resources/CSS/register.css">
  <link rel="stylesheet" href="${root}/Resources/CSS/createHunt.css">
  <link rel="stylesheet" href="${root}/Resources/CSS/displayHunt.css">
  <link rel="stylesheet" href="${root}/Resources/CSS/profile.css">
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
  <script type="text/javascript" src="${root}/Resources/JavaScript/register.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js"></script>
  <script type="text/javascript" src="${root}/Resources/JavaScript/createHunt.js"></script>
  <script type="text/javascript" src="${root}/Resources/JavaScript/displayHunt.js"></script>
  <script type="text/javascript" src="${root}/Resources/JavaScript/profile.js"></script>
</head>