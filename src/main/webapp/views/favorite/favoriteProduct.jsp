<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    <link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
<div class="row mt-5">
		<h1>SẢN PHẨM ĐƯỢC YÊU THÍCH</h1>
		<hr />
		<c:forEach var="item" items="${listFavorite}">
				<div class="col-3 mt-3">
			<div class="card ">
				<div class="card-body row ">
					<div class="text-center">
						<img
							src="/ASSIGNMENT_JAVA5/storage/${item.product.image}"
							width="250px" height="250px">
					</div>
				</div>
				<div class="card-footer bg-white">

					<div class="text-center">
						<p class="fw-bold text-primary fs-5">${item.product.name}</p>
						<p>${item.product.price}</p>
					</div>
					<div class="row">
						<c:choose>
							<c:when test="${item.isLike==0}">
								<a class="btn btn-danger" type="button"
								href="/ASSIGNMENT_JAVA5/favoriteupdate/${item.product.id}/${item.isLike}"> <i
								class="fa fa-heart"> </i> Like
							</a>
							</c:when>
							<c:when test="${item.isLike==1}">
							<a class="btn btn-danger" type="button"
								href="/ASSIGNMENT_JAVA5/favoriteupdate/${item.product.id}/${item.isLike}"> <i
								class="fa fa-heart"> </i> Unlike
							</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-danger" type="button"
								href="/ASSIGNMENT_JAVA5/favorite/${item.product.id}"> <i
								class="fa fa-heart"> </i> Like
							</a>
						</c:otherwise>
						</c:choose>
					</div>

				</div>
			</div>
		</div>
		</c:forEach>
	
		
		
		
	</div>