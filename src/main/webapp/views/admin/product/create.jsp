<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="row">
	<div class="col-3"></div>
	<div class="col-6 mb-5 mt-5" >
		<div class="card">
			<div class="card-body ">
				<h5 class="mt-3 fw-bold">THÊM SẢN PHẨM</h5>
				<hr>
				<form:form action="/ASSIGNMENT_JAVA5/admin/products/store" method="post" modelAttribute="product" enctype="multipart/form-data">
					<div class="form-group">
						<label>Category name:</label>
						<form:select path="" class="form-select" name="cate_id">
							<c:forEach var="cate" items="${listCategory}">
								<form:option value="${cate.id}">${cate.name}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-group mb-3 mt-3">
						<label>Product name:</label>
						<form:input path="name"  class="form-control mt-2"/>
					</div>
					<div class="form-group">
						<label>Product size:</label>
						<form:select path="size" >
							<form:option value="L">L</form:option>
							<form:option value="M">L</form:option>
							<form:option value="S">S</form:option>
							<form:option value="XL">XL</form:option>
							<form:option value="XXL">XXL</form:option>
						</form:select>
					</div>
					<div class="form-group mb-3 mt-3">
						<label>Product color:</label>
						<form:input path="color"   class="form-control mt-2"/>
					</div>
					
					<div class="form-group mb-3 mt-3">
						<label>Product price:</label>
						<form:input  path="price" class="form-control" />
					</div>
					<div class="form-group mb-3 mt-3">
						<label>Chọn ảnh:</label>
						<form:input path="" name="upload_file_product" type="file" class="form-control"/>
					</div>
					<div class="form-group mb-3 mt-3">
						<label>Product Description:</label>
						<form:textarea path="description" class="form-control"/>
					</div>
					<button class="btn btn-primary ">Thêm</button>
				</form:form>

			</div>
		</div>
	</div>
	<div class="col-3"></div>

</div>