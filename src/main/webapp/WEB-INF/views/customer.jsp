<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/customer.css" />" >
<title>Online Shopping</title>
</head>
<body>
 <div id="total">
  <div id="heading">
   <br /> <img src="resources/onlineShopLogo.jpg" align="left" height="90" />
   <h1><Strong>Registration Page</Strong></h1>
   <br /> <br />
  </div>
  <br />
  <div id="main_area">
   <springForm:form action="customer" method="POST" commandName="customer">
    <table class="table" style="width: 800px;">
     <tbody>
      <tr>
       <td></td>
       <td style="color: red;">${ msg }</td>
       <td></td>
      </tr>
      <tr>
       <td style="vertical-align: middle">User Name:</td>
       <td><springForm:input path="username" class="form-control" /></td>
       <td><springForm:errors path="username" cssClass="error" /></td>
      </tr>
      <tr>
       <td style="vertical-align: middle">Password</td>
       <td><springForm:password path="password" class="form-control" /></td>
       <td><springForm:errors path="password" cssClass="error" /></td>
      </tr>
      <tr>
       <td style="vertical-align: middle">First Name</td>
       <td><springForm:input path="firstname" class="form-control" /></td>
       <td><springForm:errors path="firstname" cssClass="error" /></td>
      </tr>
      <tr>
       <td style="vertical-align: middle">Last Name</td>
       <td><springForm:input path="lastname" class="form-control" /></td>
       <td><springForm:errors path="lastname" cssClass="error" /></td>
      </tr>
      <tr>
       <td style="vertical-align: middle">Email Id</td>
       <td><springForm:input path="email_id" class="form-control" /></td>
       <td><springForm:errors path="email_id" cssClass="error" /></td>
      </tr>
      <tr>
       <td style="vertical-align: middle">Address</td>
       <td><springForm:textarea path="address" class="form-control" /></td>
       <td><springForm:errors path="address" cssClass="error" /></td>
      </tr>
      <tr>
       <td style="vertical-align: middle">pin code</td>
       <td><springForm:input type="number" path="pincode" class="form-control" /></td>
       <td><springForm:errors path="pincode" cssClass="error" /></td>
      </tr>
      <tr>
       <td style="vertical-align: middle">Contact</td>
       <td><springForm:input path="contact" class="form-control" /></td>
       <td><springForm:errors path="contact" cssClass="error" /></td>
      </tr>
      <tr>
       <td align="center"><input type="submit" value="Register" name="register" class="btn btn-default"
        style="width: 150px;"></td>
       <td align="center"><input type="submit" value="Sign In" name="cancel" class="btn btn-default"
        style="width: 150px;"></td>
       <td align="center"><input type="submit" value="Clear" name="clear" class="btn btn-default"
        style="width: 150px;"></td>
      </tr>
     </tbody>
    </table>
   </springForm:form>
  </div>
 </div>
</body>
</html>