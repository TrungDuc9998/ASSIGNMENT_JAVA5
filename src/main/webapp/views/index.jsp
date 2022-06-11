<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Controller</title>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
.has-search .form-control {
	padding-left: 2.375rem;
}

.has-search .form-control-feedback {
	position: absolute;
	z-index: 2;
	display: block;
	width: 2.375rem;
	height: 2.375rem;
	line-height: 2.375rem;
	text-align: center;
	pointer-events: none;
	color: #aaa;
}

body {
	width: 98.8999999999999999999%;
}
</style>
</head>
<body>
	<div>
		<div class="row">
			<div class="col-1"></div>
			<div class="col-3 ">
				<img class="mt-3 mb-3" src="/ASSIGNMENT_JAVA5/image/logo.svg">
			</div>

			<div class="col-4 text-center mt-4">
				<div class="form-group has-search">
					<span class="fa fa-search form-control-feedback"> </span> <input
						type="text" class="form-control" placeholder="Tìm kiếm ...">
				</div>
			</div>

			<div class="col-3 mt-4 text-center">
				<img class="" src="/ASSIGNMENT_JAVA5/image/login.png">


			</div>
			<div class="col-1 "></div>
		</div>
		<nav class="navbar navbar-expand-lg "
			style="background-color: rgb(61, 70, 77); width: 100% !important">
			<div class="container-fluid">

				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse " id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0"
						style="margin-left: 100px">
						<li class="nav-item bg-warning"><a
							class="nav-link active text-light  fs-6" aria-current="page"
							href="/ASSIGNMENT_JAVA5/home">Home</a></li>
						<li class="nav-item"><a class="nav-link text-light  fs-6"
							href="/ASSIGNMENT_JAVA5/orderedProduct">Sản phẩm đặt</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-light  fs-6" href="#"
							id="navbarDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"> Tài khoản </a>
							<ul class="dropdown-menu bg-warning"
								aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="#">Đăng nhập</a></li>
								<li><a class="dropdown-item"
									href="/ASSIGNMENT_JAVA5/register">Đăng ký</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item"
									href="/ASSIGNMENT_JAVA5/logout">Đăng xuất</a></li>
							</ul></li>
						
						<li></li>
					</ul>
					<form class="d-flex me-5 ">
						<a type="button" href="/ASSIGNMENT_JAVA5/cart"> <i
							class="fa fa-shopping-cart" style="font-size: 36px; color: white"></i>
						</a>

					</form>
					<form class="d-flex me-5">

						<a type="button" href="/ASSIGNMENT_JAVA5/listfavoritebyaccountid">
							<i class="fa fa-heart" style="font-size: 36px; color: white"></i>
						</a>
					</form>
				</div>
			</div>
		</nav>
	</div>



	<div class="container">
		<c:if test="${account.getAdmin()==1}">
			<a class="btn btn-primary mt-2" data-bs-toggle="offcanvas"
				href="#offcanvasExample" role="button"
				aria-controls="offcanvasExample"> Menu </a>


			<div class="offcanvas offcanvas-start" tabindex="-1"
				id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
				<div class="offcanvas-header">
					<h5 class="offcanvas-title fs-4 text-warning fw-bold text-center"
						id="offcanvasExampleLabel">FPT POLYTECHNIC</h5>
					<button type="button" class="btn-close text-reset"
						data-bs-dismiss="offcanvas" aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<div class="bg-primary rounded text-center">
						<i class="text-white mt-2 mb-2  fs-4 ">Admin</i>
					</div>
					<div class="mt-3">
						<a href="/ASSIGNMENT_JAVA5/admin/order" class=""><i
							class="fa fa-check"></i> Đặt hàng người dùng</a>
					</div>
					<div class="mt-3">
						<a href="/ASSIGNMENT_JAVA5/cartManagement" class=""><i
							class="fa fa-check"></i> Quản lý sản phẩm đặt</a>
					</div>
					<div class="mt-3">
						<a href="/ASSIGNMENT_JAVA5/admin/ReturnRequest" class=""><i
							class="fa fa-check"></i> Quản lý yêu cầu trả hàng/hoàn tiền</a>
					</div>
					<div class="mt-3">
						<a href="/ASSIGNMENT_JAVA5/admin/orderIsReturned" class=""><i
							class="fa fa-check"></i> Đơn hàng đã bị trả</a>
					</div>
					<div class="dropdown mt-3">
						<button class="btn btn-primary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-bs-toggle="dropdown">
							Account</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<li><a class="dropdown-item"
								href="/ASSIGNMENT_JAVA5/admin/accounts/show">Hiển thị danh
									sách tài khoản</a></li>
							<li><a class="dropdown-item"
								href="/ASSIGNMENT_JAVA5/admin/accounts/create">Thêm tài
									khoản</a></li>

						</ul>
					</div>


					<div class="dropdown mt-3">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton" data-bs-toggle="dropdown">
							Product</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<li><a class="dropdown-item"
								href="/ASSIGNMENT_JAVA5/admin/products/show">Hiển thị danh
									sách sản phẩm</a></li>
							<li><a class="dropdown-item"
								href="/ASSIGNMENT_JAVA5/admin/products/create">Thêm sản phẩm</a></li>
						</ul>
					</div>
					<div class="dropdown mt-3">
						<button class="btn btn-success dropdown-toggle" type="button"
							id="dropdownMenuButton" data-bs-toggle="dropdown">
							Category</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<li><a class="dropdown-item"
								href="/ASSIGNMENT_JAVA5/admin/categories/show">Hiển thị danh
									sách loại sản phẩm</a></li>
							<li><a class="dropdown-item"
								href="/ASSIGNMENT_JAVA5/admin/categories/create">Thêm loại
									sản phẩm</a></li>

						</ul>
					</div>
				</div>
			</div>
		</c:if>

		<jsp:include page="${views}"></jsp:include>
	</div>





	<div class="row bg-dark mt-4 text-light">
		<div class="col-2 mt-4">
			<img alt="" src="/ASSIGNMENT_JAVA5/image/Store.svg">
		</div>
		<div class="col-2 mt-4">
			<h4>SẢN PHẨM</h4>
			<p>Thời trang và phụ kiện</p>
			<p>Sale-off</p>
		</div>
		<div class="col-2 mt-4">
			<h4>VỀ CÔNG TY</h4>
			<p>Lứa tuyển dụng</p>
			<p>Liên hệ nhượng quyền</p>

		</div>
		<div class="col-2 mt-4">
			<h4>HỖ TRỢ</h4>
			<p>FAQS</p>
			<p>Bảo mật thông tin</p>
			<p>Chính sách chung</p>
			<p>Tra cứu đơn hàng</p>
		</div>
		<div class="col-2 mt-4">
			<h4>LIÊN HỆ</h4>
			<p>Email góp ý</p>
			<p>Hotline :0963 429 749</p>
		</div>
		<div class="col-2 mt-4">
			<h4>ANANAS SOCIAL</h4>
			<img alt="" src="/ASSIGNMENT_JAVA5/image/icon_facebook.svg"> <img
				alt="" src="/ASSIGNMENT_JAVA5/image/icon_instagram.svg"> <img
				alt="" src="/ASSIGNMENT_JAVA5/image/icon_youtube.svg">
		</div>
	</div>

	<script src="/ASSIGNMENT_JAVA5/js/jquery.min.js"></script>
	<script src="/ASSIGNMENT_JAVA5/js/popper.min.js"></script>
	<script src="/ASSIGNMENT_JAVA5/js/bootstrap.min.js"></script>
</body>
</html>