<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div class="mt-4">
	<div class="d-grid gap-2 d-md-block">
		<a href="/ASSIGNMENT_JAVA5/orderedProduct"
			class="btn btn-danger btn-rounded" type="button">Chờ xác nhận</a> <a
			href="/ASSIGNMENT_JAVA5/orderedProduct1"
			class="btn btn-danger btn-rounded" type="button">Chờ lấy hàng</a> <a
			href="/ASSIGNMENT_JAVA5/orderedProduct2"
			class="btn btn-danger btn-rounded" type="button">Đang giao</a> <a
			href="/ASSIGNMENT_JAVA5/orderedProduct3"
			class="btn btn-danger btn-rounded" type="button">Đã giao</a> <a
			href="/ASSIGNMENT_JAVA5/orderedProduct4"
			class="btn btn-danger btn-rounded" type="button">Đã huỷ hàng</a> <a
			href="/ASSIGNMENT_JAVA5/orderedProduct5"
			class="btn btn-danger btn-rounded" type="button">Trả hàng</a>
	</div>
	<div class="row mt-3">
		<div class="col-12">
			<table class="table bg-white">
				<thead>
					<tr>
						<th>Mã hoá đơn</th>
						<th>Tên sản phẩm</th>
						<th>Hình ảnh</th>
						<th>Số lượng</th>
						<th>Địa chỉ</th>
						<th>Thời gian đặt</th>
						<th>Tổng tiền</th>
						<th colspan="2"></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="sp" items="${listsp}">
						<tr>
							<td>${sp.order.id}</td>
							<td>${sp.product.name}</td>
							<td>
								<img alt="" src="/ASSIGNMENT_JAVA5/storage/${sp.product.image}" width="80px">
							</td>
							<td>${sp.quantity}</td>
							<td>${sp.order.address }</td>
							<td>${sp.order.createdDate}</td>
							<td>${sp.price}</td>
							<td>
								<c:if test="${sp.status==4}">
									<i class="fw-bold text-danger">Đã huỷ hàng</i>
								</c:if>
								<c:if test="${sp.status==3}">
									<i class="text-success fw-bold">Đã giao hàng</i>
								</c:if>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>


</div>
