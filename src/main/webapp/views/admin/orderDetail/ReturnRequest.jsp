<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div class="">
	<div class="row">
		<p class="text-center fw-bold fs-2 text-danger">YÊU CẦU TRẢ
			HÀNG/HOÀN TIỀN</p>
	</div>
	<div class="row">

		<div class="col-12">
			<table class="table bg-white rounded">
				<thead>
					<tr>
						<th>Mã hoá đơn</th>
						<th>Tên khách hàng</th>
						<th>Địa chỉ</th>
						<th>Thời gian đặt</th>
						<th>Tên sản phẩm</th>
						<th>Số lượng</th>
						<th>Tổng tiền</th>
						<th>Hình ảnh</th>
						<th>Lý do</th>

						<th colspan="3"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listOrder}">
						<tr>
							<td>${item.order.id}</td>
							<td><c:choose>
									<c:when test="${item.order.user.fullname!=null}">${item.order.user.fullname}</c:when>
									<c:when test="${item.order.fullname!=null}">${item.order.fullname}</c:when>
								</c:choose></td>
							<td>${item.order.address}</td>
							<td>${item.order.createdDate}</td>
							<td>${item.product.name}</td>
							<td>${item.quantity }</td>
							<td>${item.price}</td>
							<td><img alt=""
								src="/ASSIGNMENT_JAVA5/storage/${item.image}" width="80px">
							</td>
							<td>${item.note}</td>
							<td>
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-success"
									data-bs-toggle="modal" data-bs-target="#exampleModal${item.id}">
									Xác nhận trả hàng</button> <!-- Modal -->
								<div class="modal fade" id="exampleModal${item.id}" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">XÁC NHẬN YÊU CẦU TRẢ HÀNG/HOÀN TIỀN</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												Bạn có muốn xác nhận yêu cầu này không? ${item.id}
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Không</button>
												<a type="button" href="/ASSIGNMENT_JAVA5/admin/verifyRequest/${item.id}/${item.order.id}" class="btn btn-primary">Có</a>
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

</div>