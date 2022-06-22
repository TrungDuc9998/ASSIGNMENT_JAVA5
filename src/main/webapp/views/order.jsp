<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<div class="container">

	<div class="row">

		<div class="col-8 bg-white rounded mt-3">
			<p class="mt-3 fw-bold fs-5">ĐỊA CHỈ GIAO HÀNG</p>
			<hr>
			<form:form action="/ASSIGNMENT_JAVA5/orderStore/${total}"
				method="post" modelAttribute="account">
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
							<form:input path="phoneNumber" class="form-control" />
						</div>
					</div>

				</div>

				<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
					<button class="btn btn-primary btn-rounded">Đặt hàng</button>
				</div>
			</form:form>





		</div>

		<div class="col-4 mt-3">
			<h5>SẢN PHẨM ĐÃ CHỌN</h5>
			<hr>
			<div class="bg-white row ms-1 rounded">
				<h6 class="mt-3 text-center text-danger fw-bold fs-5">Sản phẩm:</h6>

				<div class=" ">
					<hr width="400px" />
					<c:forEach var="item" items="${listCard}">
						<div class="col-9" style="float: left">
							<p>${item.product.name}(${item.quantity})</p>
						</div>
						<div class="col-3" style="float: left">
							<p>: ${item.price}</p>
						</div>

					</c:forEach>

				</div>
				<hr>
				<p class="text-danger fw-bold ">Tổng cộng : ${total}</p>
				<!-- Button trigger modal -->
				<a type="button" class="btn btn-primary btn-rounded"
					data-bs-toggle="modal" data-bs-target="#exampleModal">Thêm sản
					phẩm</a>

				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">THÊM SẢN
									PHẨM ĐẶT HÀNG</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form:form action="/ASSIGNMENT_JAVA5/orderStore" method="get"
									modelAttribute="product">
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
													<td><form:checkbox class="form-check-input"
															id="ck${pro.id}" path="product_id"
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
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Đóng</button>
									<button class="btn btn-primary">Thêm sản phẩm</button>
								</form:form>
							</div>

						</div>
					</div>
				</div>

			</div>
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
