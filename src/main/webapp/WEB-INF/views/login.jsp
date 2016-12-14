<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

<title>Online Shopping</title>
</head>
<body>
 <img src="resources/onlineShopLogo.jpg" />
 <h1 align="center">
  <Strong>Mobile World</Strong>
 </h1>
 <springForm:form action="login" method="POST" commandName="login">
  <table class="table" style="max-width: 500px;">
   <thead>
    <tr>
     <th style="font-weight: bold; font-size: 150%">Login</th>
     <th style="color: red; font-size: 100%">${error}</th>
     <th></th>
    </tr>
   </thead>
   <tbody>
    <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
    </tr>
    <tr>
     <td style="vertical-align: middle">UserName</td>
     <td>
      <div class="input-group">
       <span class="input-group-addon" id="basic-addon1"><i class="fa fa-user"></i></span>
       <springForm:input path="username" placeholder="Username" class="form-control" aria-describedby="basic-addon1" />
      </div>
     </td>
     <td><springForm:errors path="username" cssClass="error" /></td>
    </tr>
    <tr>
     <td style="vertical-align: middle">Password</td>
     <td>
      <div class="input-group">
       <span class="input-group-addon" id="basic-addon2"><i class="fa fa-key"></i></span>
       <springForm:password path="password" placeholder="Password" class="form-control" aria-describedby="basic-addon2" />
      </div>
     </td>
     <td><springForm:errors path="password" cssClass="error" /></td>
    </tr>
    <tr>
     <td>&nbsp;</td>
    </tr>
    <tr>
     <td align="center"><input type="submit" name="signin" value="SignIn" class="btn btn-default"
      style="width: 150px;"></td>
     <td align="center"><input type="submit" name="register" value="Register" class="btn btn-default"
      style="width: 150px;"></td>
     <td></td>
    </tr>
   </tbody>
  </table>
 </springForm:form>
</body>
</html>