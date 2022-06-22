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
							<td><a type="button"
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
									<td>
										<a type="button" href="/ASSIGNMENT_JAVA5/receive/${sp.id}" class="btn btn-success">
											Nhận hàng
										</a>
									</td>
									
								</c:when>
								<c:when test="${sp.status==3}">
									<td>
										<i class="text-success bold-success">Đã giao hàng</i>
									</td>
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
						<th>#</th>
						<th>Tên sản phẩm</th>
						<th>Số lượng</th>
						<th>Đơn giá</th>
						<th colspan="2"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}">
						<tr>
							<td>${item.id}</td>
							<td>${item.product.name}</td>
							<td>${item.quantity}</td>
							<td>${item.price}</td>
							
							<c:choose>
								<c:when test="${item.status==0}">
									<td><a type="button"
										href="/ASSIGNMENT_JAVA5/deleteOrderDetail/${item.id}/${item.order.id}"> <i
											class="fa fa-trash-o" style="font-size: 30px; color: red"></i>
									</a></td>
								</c:when>
								<c:when test="${item.status==4}">
									<td>	
										<i class="text-danger fw-bold">Đã huỷ hàng</i>
									</td>
								</c:when>
								<c:when test="${item.status==3}">
									<td>
										<i class="text-success">Đã nhận hàng</i>
									</td>
								</c:when>
								<c:when test="${item.status==6}">
									<td>
										<i class="text-danger fw-bold">Đã gửi yêu cầu trả hàng</i>
									</td>
								</c:when>
								<c:when test="${item.status==5}">
									<td>
										<i class="text-danger">Đã trả hàng</i>
									</td>
								</c:when>
								<c:when test="${item.status!=5 && item.status!=1}">
									<td>
										<a type="button" class="btn btn-success mt-2" href="/ASSIGNMENT_JAVA5/receiveDetail/${item.order.id}/${item.id}/${item.product.id}">NHẬN HÀNG</a>
									</td>
									<td>
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal"
											data-bs-target="#exampleModal${item.id}">Trả hàng</button> <!-- Modal -->
										<div class="modal fade" id="exampleModal${item.id}"
											tabindex="-1" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">Trả
															hàng</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">
														<form action="/ASSIGNMENT_JAVA5/returns/${item.id}" method="post"
															enctype="multipart/form-data">
															<p>Nhập lý do cụ thể.Hãy thêm hình ảnh và chỉ rõ lỗi của sản phẩm</p>
															<div class="form-group">
																<label>Hỉnh ảnh</label> <input class="form-control"
																	type="file" name="upload_file">
															</div>
															<div class="form-group">
																<label>Ghi chú</label>
																<textarea rows="5" cols="10" class="form-control" name="note"></textarea>
															</div>
															<button type="button" class="btn btn-secondary mt-2" data-bs-dismiss="modal">Đóng</button>
															<button  class="btn btn-primary mt-2">Yêu cầu trả hàng/Hoàn tiền</button>
														</form>
													</div>

												</div>
											</div>
										</div>
									</td>
								</c:when>
							</c:choose>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


</div>
