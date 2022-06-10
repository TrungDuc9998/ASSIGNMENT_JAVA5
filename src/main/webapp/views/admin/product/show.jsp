<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div>
	<div class=" bg-white mt-5 rounded">
		<form:form action="/ASSIGNMENT_JAVA5/admin/products/show" modelAttribute="category">
			<div class="row">

				<div class="col-4 "></div>
				<div class="col-4 ">

					<select class="form-select mt-4" name="cate_id">
						<c:forEach var="cate" items="${listCategory}">
							<option value="${cate.id}">${cate.name}</option>
						</c:forEach>
					</select>

				</div>
				<div class="col-4 ">
					<button class="btn btn-primary btn-rounded mt-4">Tìm kiếm</button>
				</div>

			</div>
		</form:form>
		<table class="table ">
			<thead>
				<tr>
					<th>Id</th>
					<th>Product name</th>
					<th>Product image</th>
					<th>Product price</th>
					
					<th>Create Date</th>
					
					<th colspan="2">Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${listProduct}">
					<tr>
						<td>${product.id}</td>
						<td>${product.name}</td>
						<td>
							<img alt="" src="/ASSIGNMENT_JAVA5/storage/${product.image}" width="80px" height="80px">
						</td>
						<td>${product.price}</td>
						
						
						<th>${product.createdDate}</th>
						
						<td><a class="btn btn-primary"
							href="/ASSIGNMENT_JAVA5/admin/products/edit/${product.id}"
							type="button">Cập nhật</a></td>
						<td>
							<!-- Button trigger modal -->
							<button type="button" class="btn btn-danger"
								data-bs-toggle="modal"
								data-bs-target="#exampleModal${product.id}">Xoá</button> <!-- Modal -->
							<div class="modal fade" id="exampleModal${product.id }"
								tabindex="-1" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">
												<h4 class="text-warning">FPT POLYTECHNIC</h4>
											</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">Bạn có muốn xoá bản ghi này
											không?</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>
											<a type="button"
												href="/ASSIGNMENT_JAVA5/admin/products/delete/${product.id}"
												class="btn btn-danger">Xoá</a>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>