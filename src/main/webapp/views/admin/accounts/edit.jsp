<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div class=" bg-white mt-2">
	<nav aria-label="breadcrumb ">
		<ol class="breadcrumb">
			<li class="breadcrumb-item mt-2 mb-2"><a href="#">Home</a></li>
			<li class="breadcrumb-item active mt-2 mb-2" aria-current="page">Library</li>
		</ol>
	</nav>
</div>
<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas"
	data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
	Menu</button>

<div class="offcanvas offcanvas-start" tabindex="-1"
	id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
	<div class="offcanvas-header">
		<h5 class="offcanvas-title" id="offcanvasExampleLabel">Quản trị</h5>
		<button type="button" class="btn-close text-reset"
			data-bs-dismiss="offcanvas" aria-label="Close"></button>
	</div>
	<div class="offcanvas-body">

		<ul>
			<li class="nav-item"><a class="nav-link active text-dark fs-6"
				href="#">Danh sách người dùng</a></li>
			<li class="nav-item"><a class="nav-link text-dark  fs-6"
				href="#">Thêm người dùng</a></li>
			<li class="nav-item"><a class="nav-link text-dark  fs-6"
				href="#">Danh sách loại sản phẩm</a></li>
			<li class="nav-item"><a class="nav-link text-dark  fs-6"
				href="#">Thêm loại sản phẩm</a></li>
			<li class="nav-item"><a class="nav-link text-dark  fs-6"
				href="#">Danh sách sản phẩm</a></li>
			<li class="nav-item"><a class="nav-link text-dark  fs-6"
				href="#">Thêm sản phẩm</a></li>
		</ul>
	</div>
</div>

<div class="bg-white ">
	<div class="row">

		<form:form action="/ASSIGNMENT_JAVA5/admin/accounts/update/${account.id}"
			method="post" modelAttribute="account" enctype="multipart/form-data">
			<h5 class="fw-bold ms-3 mt-3">CẬP NHẬT TÀI KHOẢN</h5>
			<hr class="ms-3 me-3" />
			<div class="row ms-2 mt-3 me-2 mb-3">
				<div class="col-6">
					<div class=" ms-5 me-5">
						<div class="card">
							<div class="card-body text-center">
								
								<img class="text-center" src="/ASSIGNMENT_JAVA5/storage/${account.photo }" width="400px" height="400px">
							</div>
						</div>
					</div>
				</div>
				<div class="col-6">
					<div class="form-group">
						<label>FullName:</label>
						<form:input path="fullname"  class="form-control" />
						
						
					</div>
					<div class="form-group">
						<label>Email:</label>
						<form:input path="email" class="form-control" />
						
					</div>
					<div class="form-group">
						<label>Username:</label>
						<form:input path="username" class="form-control" />
						
					</div>
					
					
					<div class="form-group">
						<label>Photo:</label>
						<form:input type="file" path=""  name="upload_file"  class="form-control" />
					</div>
					<div class="form-group">
						<label>Address:</label>
						<form:input path="address" class="form-control" />
						
					</div>
					<div class="form-group">
						<label>Phone Number:</label>
						<form:input path="phoneNumber" class="form-control" />
						
					</div>
					<div class="form-group">
						<label>Admin:</label>
						<form:select path="admin" class="form-select">
							<form:option value="0">Member</form:option>
							<form:option value="1">Admin</form:option>
						</form:select>
					
					</div>
					<button class="btn btn-primary mt-3">Cập nhật</button> 
				</div>

			</div>


		</form:form>
	</div>

</div>

