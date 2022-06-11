<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<div class="container">

	<div class="row">

		<div class="col-12 bg-white rounded mt-3">
			<p class="mt-3 fw-bold fs-5">ĐỊA CHỈ GIAO HÀNG</p>
			<hr>
			<form:form action="/ASSIGNMENT_JAVA5/admin/orderStore" method="post"
				modelAttribute="product">
				<div class="row mt-4">

					<div class="col-6">

						<div class="form-group">
							<label>Họ Tên</label>
							<form:input path="fullname" class="form-control" />
						</div>
						<div class="form-group">
							<label>Email</label>
							<form:input path="email" class="form-control" />
						</div>
					</div>
					<div class="col-6">

						<div class="form-group">
							<label>Địa chỉ</label>
							<form:input path="address" class="form-control" />
						</div>
						<div class="form-group">
							<label>Số điện thoại</label>
							<form:input path="phone" class="form-control" />
						</div>
					</div>

				</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
					<button class="btn btn-primary btn-rounded ">Đặt hàng</button>
					<a class="btn btn-danger" type="button" href="/ASSIGNMENT_JAVA5/admin/accounts/create">Tạo tài khoản</a>
				</div>
			

					<p>
						<a class="btn btn-warning btn-rounded"
							data-bs-toggle="collapse" href="#collapseExample" role="button"
							aria-expanded="false" aria-controls="collapseExample"> Chọn
							sản phẩm </a>

					</p>
					
					<div class="collapse" id="collapseExample">
						<div class="card card-body">

							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>Tên sản phẩm</th>
										<th>Hình ảnh</th>
										<th>Giá tiền</th>
										<th>Số lương</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pro" items="${listPro}">
										<tr>
											<td><form:checkbox class="form-check-input" id="ck${pro.id}" path="product_id"
													onclick="check('${pro.id}')" value="${pro.id}" /></td>
											<td>${pro.name}</td>
											<td><img alt="" width="80px" height="80px"
												src="/ASSIGNMENT_JAVA5/storage/${pro.image}"></td>
											<td>${pro.price}</td>
											<td><form:input path="quantity" id="ip${pro.id}"
													disabled="true" min="1" value="1" type="number" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
					</div>

				




			</form:form>





		</div>


	</div>
</div>
<script>
	function check(id) {
		a = document.getElementById('ck' + id);
		if (a.checked == true) {
			document.getElementById('ip' + id).removeAttribute('disabled');
		}
		if (a.checked == false) {
			document.getElementById('ip' + id).setAttribute('disabled', 'true');
		}
	}
</script>

