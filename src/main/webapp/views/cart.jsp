<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div class="container">
	<div class="row">
		<div class="col-12 bg-white mt-3">

			

				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>Tên sản phẩm</th>
							<th>Hình ảnh</th>

							<th>Màu sắc</th>
							<th>Size</th>
							<th>Số lượng</th>
							<th>Thành tiền</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cart" items="${listCart}">
							<tr>
								<td>



									
								</td>
								<td>${cart.product.name}</td>
								<td>
									<img alt="" src="/ASSIGNMENT_JAVA5/storage/${cart.product.image}" width="80px">
								</td>

								<td>${cart.product.color}</td>
								<td>${cart.product.size}</td>
								<td>
									${cart.quantity }
								</td>
								<td>${cart.price}</td>
								<td>
									<!-- Button trigger modal --> <a type="button"
									data-bs-toggle="modal"
									data-bs-target="#exampleModal${cart.id }"> <i
										class="fa fa-remove" style="font-size: 36px; color: red"></i></a>
									<!-- Modal -->
									<div class="modal fade" id="exampleModal${cart.id}"
										tabindex="-1" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title text-warning" id="exampleModalLabel">FPT
														POLYTECHNIC</h5>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">Bạn có muốn xoá sản phẩm khỏi
													giỏ hàng?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Đóng</button>
													<a type="button" type="button"
														href="/ASSIGNMENT_JAVA5/deleteCart/${cart.id}"
														class="btn btn-primary">Xác nhận</a>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>

					</tbody>

				</table>
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">

					<a class="btn btn-danger" href="/ASSIGNMENT_JAVA5/order">TIẾN HÀNH ĐẶT HÀNG</a>

				</div>
			
		</div>

	</div>
</div>
