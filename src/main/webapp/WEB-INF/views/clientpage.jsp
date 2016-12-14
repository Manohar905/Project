<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-sortable.css" />" >
<link rel="stylesheet" href="<c:url value="/resources/css/clientpage.css" />" >
<script src="<c:url value="/resources/js/bootstrap-sortable.js" />"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title>Online Shopping</title>
</head>
<body>
 <springForm:form action="clientpage" method="POST" oninput="priceDisplay.value=priceUpto.value">
  <div id="total">
   <!-- Title and search area -->
   <div id="search">
    <div id="name_logout">${ name }
     <input type="submit" name="logout" value="Logout" class="btn btn-info btn-md" >
    </div>
    <img src="resources/onlineShopLogo.jpg" align="left" height="25%" />
    <div id="title_main">
     <h1>
      <Strong>Client Page</Strong>
     </h1>
     <br /> 
     <div class="input-group custom-search-form">
            <input type="text" name="clientsearch" value="${clientsearch}" placeholder="Search Item" class="form-control" />
            <span class="input-group-btn">
             <button class="btn btn-default" type="submit" name="search">
              <i class="fa fa-search"></i>
             </button>
            </span>
           </div>
     <br /> <label style="color: red">${ search_error }</label>
    </div>
   </div>
   <br />
   <div id="main_area">
    <!-- Conditional area -->
    <div id="condition_area">
     <h2 align="left">Conditions</h2>
     <br />
     <!--   Condition on Category -->
     <h5 align="left">
      <Strong>Category</Strong>
     </h5>
     <c:forEach items="${categorieslist}" var="category">
      <input type="checkbox" name="categoryname"
       <c:forEach var="selected" items="${selectedCategories}" >
       <c:if test="${selected == category}">checked="cheked"</c:if>
       </c:forEach>
       value="${ category }">${ category }<br />
     </c:forEach>
     <p align="right">
      <input type="submit" name="categoriescondition" value="Apply" class="btn btn-default btn-sm">
     </p>
     <!--   Condition on company -->
     <h5 align="left">
      <Strong>Company</Strong>
     </h5>
     <c:forEach items="${nameslist}" var="names">
      <input type="checkbox" name="itemname"
       <c:forEach var="selected" items="${selectedNames}" >
						 <c:if test="${selected == names}">checked="cheked"</c:if>
						 </c:forEach>
       value="${ names }">${ names }<br />
     </c:forEach>
     <p align="right">
      <input type="submit" name="namescondition" value="Apply" class="btn btn-default btn-sm">
     </p>
     <!--   Condition on price -->
     <h5 align="left">
      <Strong>Price</Strong>
     </h5>
     <p>
      <input type="range" name="priceUpto" value="${priceUpto}" min="0" max="${maxPrice}" step="50" style="width: 100%;" />
      0
      <output name="priceDisplay" for="priceUpto" style="float: right;">${priceUpto }</output>
     </p>
     <p align="right">
      <input type="submit" name="pricecondition" value="Apply" class="btn btn-default btn-sm">
     </p>
    </div>
    <!-- Results area -->
    <div id="results">
     <h1 align="center">Results table</h1>
     <p align="left">
      Search Results for: ${ clientsearch } <input type="submit" name="clearsearch" value="Clear"
       class="btn btn-default">
     </p>
     <p align="center" style="color: red;">${ msg }</p>
     <!-- View cart button -->
     <div id="viewcart">
      <input type="submit" name="viewcart" value="View Cart (${cartcount})" class="btn btn-primary">
     </div>
     <br />
     <table class="table table-bordered table-hover sortable">
      <thead>
       <tr class="info">
        <th data-defaultsort="disabled">Image</th>
        <th class="az" data-firstsort="desc">Item Name</th>
        <th class="az">Item Code</th>
        <th data-defaultsort="disabled">Description</th>
        <th data-defaultsort="desc" class="_19">Price</th>
        <th data-defaultsort="disabled">Add to Cart</th>
       </tr>
      </thead>
      <tbody>
      <c:forEach items="${items_list}" var="item">
       <tr >
        <td><img src="<c:url value="resources/"/>${item.image_location}" height="100" /></td>
        <td style="vertical-align: middle">${item.name}</td>
        <td style="vertical-align: middle">${item.code}</td>
        <td style="vertical-align: middle">${item.description}</td>
        <td style="vertical-align: middle">${item.price}</td>
        <td style="vertical-align: middle"><button name="itemcode" type="submit" value="${ item.code }" class="btn btn-default">Add To
          Cart</button></td>
       </tr>
      </c:forEach>
      </tbody>
     </table>
    </div>
   </div>
  </div>
 </springForm:form>
</body>
</html>