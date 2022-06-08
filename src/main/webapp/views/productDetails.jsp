<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/bootstrap.min.css">
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/styleInput.css">
<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col-4">
				<div class="card text-center">
					<div class="card-body">
						<img alt=""
							src="/ASSIGNMENT_JAVA5/image/d44d38acb91580fe22727182034d26df_tn.jfif">
					</div>
					<c:choose>
						<c:when test="${favorite.isLike==0}">
							<a class="btn btn-danger" type="button"
								href="/ASSIGNMENT_JAVA5/favoriteupdate/${product.id}/${favorite.isLike}"> <i
								class="fa fa-heart"> </i> Yêu thích
							</a>
						</c:when>
						<c:when test="${favorite.isLike==1}">
							<a class="btn btn-danger" type="button"
								href="/ASSIGNMENT_JAVA5/favoriteupdate/${product.id}/${favorite.isLike}"> <i
								class="fa fa-heart"> </i> Unlike
							</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-danger" type="button"
								href="/ASSIGNMENT_JAVA5/favorite/${product.id}"> <i
								class="fa fa-heart"> </i> Yêu thích
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-8">
				<div class="card">
					<div class="card-body ms-2">
						<form
							action="/ASSIGNMENT_JAVA5/store/cart/${product.id}/${product.price}"
							method="post">
							<h5 class="fw-bold fs-3 text-danger">
								<c:set var="string1" value="${product.name}" />
								<c:set var="string2" value="${fn:toUpperCase(string1)}" />
								<p>${string2}</p>
							</h5>
							<h5 class="fw-bold fs-4 text-warning">Giá tiền :
								${product.price } $</h5>
							<p>${product.description }</p>

							<label class="fw-bold mt-2 fs-6">Số lượng:</label>
							<div style="width: 200px" class=" mt-2">
								<input type="number" name="quantity" value="1" min="1"
									class="form-control">
							</div>
							<p class="fs-5 fw-bold mt-2">Màu sắc: ${product.color }</p>
							<p class="fs-5 fw-bold">Size: ${product.size }</p>

							<button class="btn btn-warning">Thêm vào giỏ hàng</button>
						</form>


					</div>
				</div>

			</div>
		</div>
	</div>
	<script src="/ASSIGNMENT_JAVA5/js/jquery.min.js"></script>
	<script src="/ASSIGNMENT_JAVA5/js/popper.min.js"></script>
	<script src="/ASSIGNMENT_JAVA5/js/bootstrap.min.js"></script>
</body>
</html>