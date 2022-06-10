<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div class=" mt-5">
	<div class="row bg-white rounded">
		<h5 class="mt-3 fw-bold">CẬP NHẬT SẢN PHẨM</h5>
			<hr class="">
		<div class="col-6 text-center mt-5 mb-5">
			<div class="card">
				<div class="card-body">
					<img alt="" src="/ASSIGNMENT_JAVA5/storage/${product.image}" width="400px" height="400px">
				</div>
			</div>
		</div>
		<div class="col-6 mb-5 mt-5">


			
			<form:form action="/ASSIGNMENT_JAVA5/admin/products/update/${product.id}"
				method="post" modelAttribute="product" enctype="multipart/form-data">
				<div class="form-group mb-3 mt-3">
					<label>Product name:</label>
					<form:input path="name" class="form-control mt-2" />
				</div>

				<div class="form-group mb-3 mt-3">
					<label>Product price:</label>
					<form:input path="price" class="form-control" />
				</div>
				<div class="form-group mb-3 mt-3">
					<label>Product size:</label>
					<form:input path="color" class="form-control" />
				</div>
				<div class="form-group mb-3 mt-3">
					<label>Product color:</label>
					<form:input path="size" class="form-control" />
				</div>
				
				<div class="form-group mb-3 mt-3">
					<label>Chọn ảnh:</label>
					<form:input path="" name="upload_file_product" type="file"
						class="form-control" />
				</div>
				<button class="btn btn-primary ">Cập nhật</button>
			</form:form>


		</div>
	</div>



</div>