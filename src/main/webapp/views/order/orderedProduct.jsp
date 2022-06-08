<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="">
	<div class="d-grid gap-2 d-md-block">
		<a href="/ASSIGNMENT_JAVA5/orderedProduct" class="btn btn-danger btn-rounded"  type="button">Chờ xác nhận</a>
		<a href="/ASSIGNMENT_JAVA5/orderedProduct1" class="btn btn-danger btn-rounded" type="button">Chờ lấy hàng</a>
		<a href="/ASSIGNMENT_JAVA5/orderedProduct2" class="btn btn-danger btn-rounded" type="button">Đang giao</a>
		<a href="/ASSIGNMENT_JAVA5/orderedProduct3" class="btn btn-danger btn-rounded" type="button">Đã giao</a>
		<a href="/ASSIGNMENT_JAVA5/orderedProduct4" class="btn btn-danger btn-rounded" type="button">Đã huỷ hàng</a>
		<a href="/ASSIGNMENT_JAVA5/orderedProduct5" class="btn btn-danger btn-rounded" type="button">Đổi hàng</a>
	</div>
	<div class="row mt-3">
		<div class="col-8">
			<table class="table bg-white">
				<thead>
					<tr>
						<th>Mã hoá đơn</th>
						<th>Địa chỉ</th>
						<th>Thời gian đặt</th>
						<th>Tổng tiền</th>
						<th colspan="2"></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="sp" items="${listsp}">
						<tr>
							<td>${sp.id}</td>
							<td>${sp.address }</td>
							<td>${sp.createdDate}</td>
							<td>${sp.total}</td>
							<td>
							
							<a type="button"
								href="/ASSIGNMENT_JAVA5/detailAccount/${sp.id}"
								class="btn btn-warning">Chi tiết</a></td>

							<c:choose>
								<c:when test="${sp.status==0}">
									<td><a type="button" class="btn btn-danger"
										href="/ASSIGNMENT_JAVA5/orderCancellation/${sp.id}">Huỷ
											đơn hàng</a></td>
								</c:when>
								<c:when test="${sp.status==1}">
									<td><i class="text-success">Chờ lấy hàng</i></td>

								</c:when>
								<c:when test="${sp.status==2}">
									<td><i class="text-danger">Đang giao hàng</i></td>

								</c:when>
							</c:choose>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-3">
			<table class="table bg-white">
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
							<c:choose>
								<c:when test="${item.order.status==0}">
									<td><a type="button"
										href="/ASSIGNMENT_JAVA5/deleteOrderDetail/${item.id}"> <i
											class="fa fa-trash-o" style="font-size: 30px; color: red"></i>
									</a></td>
								</c:when>
							</c:choose>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


</div>
