<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">


<!-- Button trigger modal -->


<div class="bg-white rounded ">
	<a type="button" class="btn btn-primary"
		href="/ASSIGNMENT_JAVA5/admin/accounts/create">Thêm mới</a>
	<table class="table table-striped table-hover">
		<thead>
			<tr class="">
				<th>Username</th>
				<th>FullName</th>
				<th>Email</th>
				<th>admin</th>
				<th colspan="2">Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="account" items="${page.content}">
				<tr>
					<td>${account.username}</td>
					<td>${account.fullname}</td>
					<td>${account.email}</td>
					<td><c:choose>
							<c:when test="${account.admin==0}">member</c:when>
							<c:when test="${account.admin==1}">admin</c:when>
						</c:choose></td>

					<td><a type="button" class="btn btn-primary"
						data-bs-toggle="tooltip" data-bs-placement="top"
						title="Cập nhật tài khoản"
						href="/ASSIGNMENT_JAVA5/admin/accounts/edit/${account.id}">Cập
							nhật</a></td>
					<td>
						<button type="button" class="btn btn-danger"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal${account.id}">Xoá</button> <!-- Modal -->
						<div class="modal fade" id="exampleModal${account.id}"
							tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Xoá Tài
											Khoản</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">Bạn có muốn xoá tài khoản này
										không?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Đóng</button>
										<a type="button" class="btn btn-primary"
											href="/ASSIGNMENT_JAVA5/admin/accounts/delete/${account.id}">Xoá</a>
									</div>
								</div>
							</div>
						</div>
					<td>
				</tr>

			</c:forEach>

		</tbody>
	</table>
	<div class="row">
		<div class="col-12">
			<div class="text-center">
				<nav aria-label="Page navigation example" >
					<ul class="pagination justify-content-center">
						<li class="page-item bg-success rounded"><a class="page-link"
							href="/ASSIGNMENT_JAVA5/admin/accounts/show?p=0" tabindex="-1"
							aria-disabled="true">First page</a></li>
						<li class="page-item bg-warning rounded"><a class="page-link"
							href="/ASSIGNMENT_JAVA5/admin/accounts/show?p=${page.number-1}">Previous
								page</a></li>

						<li class="page-item active" aria-current="page"><a class="page-link" href="#">${page.number}</a></li>
						<li class="page-item bg-warning rounded"><a class="page-link"
							href="/ASSIGNMENT_JAVA5/admin/accounts/show?p=${page.number+1}">Next
								page</a></li>
						<li class="page-item bg-success rounded" ><a class="page-link"
							href="/ASSIGNMENT_JAVA5/admin/accounts/show?p=${page.totalPages-1}">Last
								page</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</div>