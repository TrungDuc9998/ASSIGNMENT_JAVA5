<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">

<div class="">
	<div class="row mt-3">
		<div class="col-4">
			<div class="card text-center">
				<div class="card-body">
					<img alt="" src="/ASSIGNMENT_JAVA5/storage/${product.image}">
				</div>
				<c:choose>
					<c:when test="${favorite.isLike==0}">
						<a class="btn btn-danger" type="button"
							href="/ASSIGNMENT_JAVA5/favoriteupdate/${product.id}/${favorite.isLike}">
							<i class="fa fa-heart"> </i> Like
						</a>
					</c:when>
					<c:when test="${favorite.isLike==1}">
						<a class="btn btn-danger" type="button"
							href="/ASSIGNMENT_JAVA5/favoriteupdate/${product.id}/${favorite.isLike}">
							<i class="fa fa-heart"> </i> Unlike
						</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-danger" type="button"
							href="/ASSIGNMENT_JAVA5/favorite/${product.id}"> <i
							class="fa fa-heart"> </i> Like
						</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="col-8">
			<div class="card">
				<div class="card-body ms-2">
					<form
						action="/ASSIGNMENT_JAVA5/store/cart/${product.id}/${product.price}"
						method="post">
						<h5 class="fw-bold fs-3 text-danger">
							<c:set var="string1" value="${product.name}" />
							<c:set var="string2" value="${fn:toUpperCase(string1)}" />
							<p>${string2}</p>
						</h5>
						<h5 class="fw-bold fs-4 text-warning">Giá tiền :
							${product.price } $</h5>
						<p style="height: 40px">${product.description }</p>

						<label class="fw-bold mt-2 fs-6">Số lượng:</label>
						<div style="width: 200px" class=" mt-2">
							<input type="number" name="quantity" value="1" min="1"
								class="form-control">
						</div>
						<p class="fs-5 fw-bold mt-2">Màu sắc: ${product.color }</p>
						<p class="fs-5 fw-bold">Size: ${product.size }</p>

						<button class="btn btn-warning">Thêm vào giỏ hàng</button>
					</form>


				</div>
			</div>

		</div>
	</div>
	<div class="row bg-white ms-1 rounded mt-4">
		<p class=" fs-4 ms-2 mt-2">Bình luận</p>
		<div class="col-12 comments">
			<p class="ms-2">10 bình luận</p>
			<form action="/ASSIGNMENT_JAVA5/admin/accounts/comment/${product.id}"
				method="post">
				<div class="row">
					<div class="col-3 mt-2">
						<p class="text-center">Mời nhập bình luận:</p>
					</div>
					<div class="col-6 mt-2">
						<input class="form-control" name="content">
					</div>
					<div class="col-3">
						<button class="btn btn-primary btn-rounded">Bình luận</button>
					</div>
				</div>
			</form>
			<c:forEach items="${listComment}" var="comment">
				<div class="row">
					<div class="col-1 text-center">
							<img alt="" src="/ASSIGNMENT_JAVA5/storage/${comment.account.photo}" class="rounded-circle" style="width:50px;height:50px">	
					</div>
					<div class="col-11">
						<h5 class=""> ${comment.account.fullname}</h5>
						<p class="fs-7" style="margin-top:-10px">${comment.date}</p>
						<p>${comment.content }</p>
					</div>
				</div>	
			</c:forEach>
		</div>
	</div>
</div>
