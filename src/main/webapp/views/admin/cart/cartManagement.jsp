<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div class="">
	<div class="row">
		<p class="text-center fw-bold fs-2 text-danger">QUẢN LÝ SẢN PHẨM
			ĐẶT</p>
	</div>
	<div class="row">

		<div class="col-9">
			<table class="table bg-white rounded">
				<thead>
					<tr>
						<th>Mã hoá đơn</th>
						<th>Tên khách hàng</th>
						<th>Địa chỉ</th>
						<th>Thời gian đặt</th>
						<th>Tổng tiền</th>
						<th colspan="4"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listOrder}">
						<tr>
							<td>${item.id}</td>
							<td><c:choose>
									<c:when test="${item.user.fullname!=null}">${item.user.fullname}</c:when>
									<c:when test="${item.fullname!=null}">${item.fullname}</c:when>
								</c:choose></td>
							<td>${item.address}</td>
							<td>${item.createdDate}</td>
							<td>${item.total}</td>
							<td><a type="button"
								href="/ASSIGNMENT_JAVA5/detail/${item.id}"
								class="btn btn-primary">Chi tiết</a></td>
							<c:choose>
								<c:when test="${item.status==0}">
									<td colspan="2">
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-success"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal${item.id}">Xác nhận</button> <!-- Modal -->
										<div class="modal fade" id="exampleModal${item.id}"
											tabindex="-1" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">XÁC
															NHẬN ĐƠN HÀNG</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">Bạn có muốn xác nhận đơn hàng
														${item.id} này không?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Không</button>
														<a href="/ASSIGNMENT_JAVA5/updateOrder/${item.id}"
															type="button" class="btn btn-primary">Xác nhận</a>
													</div>
												</div>
											</div>
										</div>
									</td>
									<td>
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-danger"
											data-bs-toggle="modal"
											data-bs-target="#exampleModa${item.id}">Huỷ</button> <!-- Modal -->
										<div class="modal fade" id="exampleModa${item.id}"
											tabindex="-1" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">HUỶ
															ĐƠN HÀNG</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">Bạn có muốn huỷ đơn hàng
														${item.id } này không?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Không</button>
														<a type="button" href="/ASSIGNMENT_JAVA5/admin/deleteOrder/${item.id}" class="btn btn-primary">Có</a>
													</div>
												</div>
											</div>
										</div></td>
								</c:when>
								<c:when test="${item.status==2}">
									<td><i class="text-danger">Đang giao hàng</i></td>
								</c:when>
								<c:when test="${item.status==1}">
									<td><i class="text-success fw-bold">Chờ lấy hàng</i></td>
								</c:when>
								<c:when test="${item.status==4}">
									<td><i class="text-danger fw-bold">Đã huỷ hàng</i></td>
								</c:when>
							</c:choose>
							<td><c:choose>
									<c:when test="${item.status==1}">
										<button type="button" class="btn btn-success"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal${item.id}">Xác nhận
											giao hàng</button>
										<!-- Modal -->
										<div class="modal fade" id="exampleModal${item.id}"
											tabindex="-1" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">XÁC
															NHẬN GIAO HÀNG</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">Bạn có muốn xác nhận giao
														hàng không? ${item.id}</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Không</button>
														<a type="button"
															href="/ASSIGNMENT_JAVA5/admin/order/deliveryConfirmation/${item.id}"
															class="btn btn-primary">Có</a>
													</div>
												</div>
											</div>
										</div>
									</c:when>
								</c:choose></td>
						</tr>
					</c:forEach>

				</tbody>

			</table>
		</div>
		<div class="col-3">
			<div class="bg-white rounded">
				<table class="table">
					<thead>
						<tr>
							<th>Tên sản phẩm</th>
							<th>Số lượng</th>
							<th>Đơn giá</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}">
							<tr>
								<td>${item.product.name}</td>
								<td>${item.quantity}</td>
								<td>${item.price}</td>
								<td><c:choose>
										<c:when test="${item.order.status==0}">
											<a type="button"
												href="/ASSIGNMENT_JAVA5/deleteOrderDetail/${item.id}"> <i
												class="fa fa-trash-o" style="font-size: 30px; color: red"></i>
											</a>
										</c:when>
									</c:choose></td>

							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>

		</div>
	</div>

</div>