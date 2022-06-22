<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div class="row">
	<div class="col-3"></div>
	<div class="col-6 mb-5" style="margin-top:100px">
		<div class="card">
			<div class="card-body text-center">
				<h5 class="mt-3 fw-bold">THÊM LOẠI SẢN PHẨM</h5>
				<hr>
				<form:form action="/ASSIGNMENT_JAVA5/admin/categories/store" method="post" modelAttribute="category">
					<div class="form-group mb-3 mt-3">
						<label>Category name:</label>
						<form:input path="name" class="form-control mt-2"/>
					</div>
					<button class="btn btn-primary ">Thêm</button>
				</form:form>

			</div>
		</div>
	</div>
	<div class="col-3"></div>

</div>