<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div>
	<div class="row bg-white mt-5">
		<table class="table ">
			<thead>
				<tr>
					<th>Id</th>
					<th>Category name</th>
					<th colspan="2">Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${listCategory}">
					<tr>
						<td>${category.id}</td>
						<td>${category.name}</td>
						<td>
							<a class="btn btn-primary" href="/ASSIGNMENT_JAVA5/admin/categories/edit/${category.id}" type="button">Cập nhật</a>
						</td>
						<td>
							<!-- Button trigger modal -->
							<button type="button" class="btn btn-danger"
								data-bs-toggle="modal" data-bs-target="#exampleModal${category.id}">
								Xoá</button> <!-- Modal -->
							<div class="modal fade" id="exampleModal${category.id }" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">
												<h4 class="text-warning">FPT POLYTECHNIC</h4>
											</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											Bạn có muốn xoá bản ghi này không?
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>
											<a type="button" href="/ASSIGNMENT_JAVA5/admin/categories/delete/${category.id}" class="btn btn-danger">Xoá</a>
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