<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/bootstrap.min.css">
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container mt-3" style="width: 700px">
		<div class="card ">
			<div class="card-body">
				<div class="bg-danger text-center">
					<img class="mt-3 mb-3   " src="/ASSIGNMENT_JAVA5/image/logo.svg">
				</div>
				<div class="text-center">
					<img class="mt-5 " src="/ASSIGNMENT_JAVA5/image/check1.png">
					<h2 class=" fw-blold text-danger">Cảm ơn bạn đã đặt hàng!</h2>
					<p>Cảm ơn bạn đã tin tưởng sử dụng sản phẩm của SIXDO.Vui lòng
						kiểm tra lại hoá đơn của bạn.Nếu bạn có thắc mắc gì vui lòng liên
						hệ 100991008 để được hỗ trợ.Cảm ơn !</p>
				</div>

				<div class="bg-warning row">
					<p class="fw-bold fs-5 ms-1 mt-2 mb-2">SẢN PHẨM ĐÃ MUA#</p>
				</div>
				<c:forEach var="item" items="${listOrderDetail}">
					<div class="row mt-2">
						<div class="col-9 ">
							<p>${item.product.name}(${item.quantity})</p>
						</div>
						<div class="col-3 ">
							<p> : ${item.price}</p>
						</div>
						
					</div>
					
				</c:forEach>
				<hr>
				<div class="row">
					
						<p class="text-danger fw-bold fs-5">TỔNG TIỀN:</p>
					
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