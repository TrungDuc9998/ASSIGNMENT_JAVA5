<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="">
	<div class="row">
		<p class="text-center fw-bold fs-2 text-danger">QUẢN LÝ SẢN PHẨM ĐẶT</p>
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
						<th colspan="3"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listOrder}">
						<tr>
							<td>${item.id}</td>
							<td>${item.user.fullname}</td>
							<td>${item.address}</td>
							<td>${item.createdDate}</td>
							<td>${item.total}</td>
							<td><a type="button"
								href="/ASSIGNMENT_JAVA5/detail/${item.id}"
								class="btn btn-primary">Chi tiết</a></td>
							<c:choose>
								<c:when test="${item.status==0}">
									<td><a type="button"
										href="/ASSIGNMENT_JAVA5/updateOrder/${item.id}"
										class="btn btn-success">Xác nhận</a> <a type="button"
										class="btn btn-danger"
										href="/ASSIGNMENT_JAVA5/deleteOrder/${item.id}">Huỷ</a></td>

								</c:when>
								<c:when test="${item.status==2}">
									<td><i class="text-danger">Đã huỷ đơn</i></td>
								</c:when>
								<c:when test="${item.status==1}">
									<td><i class="text-success fw-bold">Đã xác nhận</i></td>
								</c:when>
							</c:choose>

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