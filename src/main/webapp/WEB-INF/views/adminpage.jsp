<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link href="<c:url value="/resources/css/adminpage.css" />" rel="stylesheet">
<title>Online Shopping</title>
</head>
<body>
 <!-- Add items form -->
 <div id="total">
  <!-- Title Area -->
  <div id="title">
   <springForm:form action="adminpage_logout" method="POST">
    <div id="name_logout">${ name }
     <input type="submit" name="logout" value="Logout" class="btn btn-info">
    </div>
    <img src="resources/onlineShopLogo.jpg" align="left" height="120" />
    <div id="title_head">
     <h1>
      <Strong>Admin Page</Strong>
     </h1>
     <br /> <label style="color: red">${ msg }</label>
    </div>
   </springForm:form>
  </div>
  <br />
  <!-- Main Area -->
  <c:choose>
   <c:when test="${defaultTab_Selector=='#tabs-1'}">
    <c:set value="active" var="tab1"></c:set>
    <c:set value="" var="tab2"></c:set>
   </c:when>
   <c:otherwise>
    <c:set value="" var="tab1"></c:set>
    <c:set value="active" var="tab2"></c:set>
   </c:otherwise>
  </c:choose>
  <div id="main_area">
   <div class="container">
    <ul class="nav nav-tabs" id="myTab">
     <li class="${tab1}"><a data-toggle="tab" href="#tabs-1"> Add New Items</a></li>
     <li class="${tab2}"><a data-toggle="tab" href="#tabs-2">Edit Previous Items</a></li>
    </ul>
    <div class="tab-content">
     <div id="tabs-1" class="tab-pane ${tab1}">
      <h3>Add New Items</h3>
      <br> <br>
      <springForm:form action="adminpage_additem" method="POST" enctype="multipart/form-data" commandName="itemsImage">
       <table class="table" style="max-width: 700px;">
        <tbody>
         <tr>
          <td style="vertical-align: middle">Category:</td>
          <td>
           <div class="col-xs-6">
            <springForm:select path="category" class="form-control">
             <springForm:option value="--- Select Category ---" selected="selected" />
             <springForm:options items="${categorieslist}" />
            </springForm:select>
           </div>
           <div class="col-xs-6">
            <input name="newCat" value="" placeholder="For New Category" class="form-control" />
           </div>
          </td>
          <td><springForm:errors path="category" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Brand Name:</td>
          <td><springForm:input path="name" placeholder="Brand Name" class="form-control" /></td>
          <td><springForm:errors path="name" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Item Code:</td>
          <td><springForm:input path="code" placeholder="Item Code" class="form-control" /></td>
          <td><springForm:errors path="code" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Item Description:</td>
          <td><springForm:textarea path="description" placeholder="Item Details" class="form-control"
            style="height:100px;" /></td>
          <td><springForm:errors path="description" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Item Price:</td>
          <td><springForm:input path="price" type="number" class="form-control" /></td>
          <td><springForm:errors path="price" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Quantity:</td>
          <td><springForm:input path="quantity" type="number" class="form-control" /></td>
          <td><springForm:errors path="quantity" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">image_location:</td>
          <td><springForm:input path="image" type="file" class="form-control" /></td>
          <td><springForm:errors path="image" cssClass="error" /></td>
         </tr>
         <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
         </tr>
        </tbody>
       </table>
       <div style="margin-left: 50px;">
        <input class="btn btn-default" type="submit" value="Add Item" name="additem" style="width: 150px;" />&ensp;&emsp;&emsp;
        <input class="btn btn-default" type="submit" value="Clear" name="addcancel" style="width: 150px;" />
       </div>
      </springForm:form>
     </div>
     <div id="tabs-2" class="tab-pane ${tab2}">
      <h3>Edit Items</h3>
      <br> <br>
      <springForm:form action="adminpage_edititem" method="POST" commandName="items">
       <table class="table" style="max-width: 700px;">
        <tbody>
         <tr>
          <td style="vertical-align: middle">Item Code:</td>
          <td>
           <div class="input-group custom-search-form">
            <springForm:input path="code" placeholder="Item Code" class="form-control" />
            <span class="input-group-btn">
             <button class="btn btn-default" type="submit" name="search">
              <i class="fa fa-search"></i>
             </button>
            </span>
           </div>
          </td>
          <td><springForm:errors path="code" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Category:</td>
          <td><springForm:input path="category" placeholder="Category" class="form-control" /></td>
          <td><springForm:errors path="category" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Item Name:</td>
          <td><springForm:input path="name" placeholder="Item Name" class="form-control" /></td>
          <td><springForm:errors path="name" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Item Description:</td>
          <td><springForm:textarea path="description" placeholder="Item Details" class="form-control"
            style="height:100px;" /></td>
          <td><springForm:errors path="description" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Item Price:</td>
          <td><springForm:input path="price" type="number" class="form-control" /></td>
          <td><springForm:errors path="price" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle">Quantity:</td>
          <td><springForm:input path="quantity" type="number" class="form-control" /></td>
          <td><springForm:errors path="quantity" cssClass="error" /></td>
         </tr>
         <tr>
          <td style="vertical-align: middle"><p /></td>
          <td><springForm:hidden path="image_location" class="form-control" /></td>
          <td><springForm:errors path="image_location" hidden="true" cssClass="error" /></td>
         </tr>
        </tbody>
       </table>
       <div style="margin-left: 50px;">
        <input class="btn btn-default" type="submit" value="Update Item" name="updateitem" style="width: 150px;" />
        &ensp;&emsp;&emsp; <input class="btn btn-default" type="submit" value="Clear" name="editclear"
         style="width: 150px;" /> &ensp;&emsp;&emsp; <input class="btn btn-default" type="submit" value="Delete"
         name="editdelete" style="width: 150px;" />
       </div>
      </springForm:form>
     </div>
    </div>
   </div>
  </div>
 </div>
</body>
</html>