<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="<c:url value="/resources/css/viewcart.css" />" rel="stylesheet">
<title>Online shopping</title>
</head>
<body>
 <div id="total">
  <div id="heading">
   <div id="name_logout">
    <springForm:form action="viewcart" method="POST">
					${ name }<input type="submit" name="logout" value="Logout" class="btn btn-info btn-md">
    </springForm:form>
   </div>
   <img src="resources/onlineShopLogo.jpg" align="left" height="18%" />
   <div id="title_main">
    <h1>Cart Page</h1>
    <br />
    <p style="color: red">${msg}</p>
   </div>
  </div>
  <br />
  <div id="cart">
   <h1 align="center">Your Cart Items</h1>
   <!-- Continue Shopping Button -->
   <springForm:form action="continue_shopping" method="POST">
    <div id="backtocart">
     <input type="submit" name="continueShopping" value="Continue Shopping" class="btn btn-primary">
    </div>
   </springForm:form>
   <br /> <br />
   <springForm:form action="modify_cart" method="POST">
    <table class="table">
     <thead>
      <tr>
       <th>Item Code</th>
       <th>Item Price</th>
       <th>Quantity</th>
       <th>total</th>
       <th>Edit/Delete</th>
      </tr>
     </thead>
     <c:forEach items="${mycartList}" var="mycart">
      <tr>
       <td>${mycart.codes}</td>
       <td>${mycart.price}</td>
       <td><input type="text" name="quantity" size="5" maxlength="2" value="${mycart.quantity}"
        class="form-control" /></td>
       <td>${mycart.total}</td>
       <td>
        <button name="edit" type="submit" value="${ mycart.codes }" class="btn btn-success btn-md">Update</button>
        <button name="delete" type="submit" value="${ mycart.codes }" class="btn btn-warning btn-md">Delete</button>
       </td>
      </tr>
     </c:forEach>
     <tr>
      <td></td>
      <td></td>
      <td><Strong>Total Amount</Strong></td>
      <td><Strong>${totalamount}</Strong></td>
      <td><input type="submit" name="checkout" value="CheckOut" class="btn btn-danger" data-toggle="tooltip"
       data-placement="bottom" title="Check out Items" /></td>
     </tr>
    </table>
   </springForm:form>
  </div>
 </div>
</body>
</html>