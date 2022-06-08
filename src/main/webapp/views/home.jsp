<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="container mt-3">
	<div class="row">
		<div class="col-6 text-center">
			<div id="carouselExampleCaptions" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="/ASSIGNMENT_JAVA5/image/banner-phụ_2m-600x320.jpg"
							class="d-block w-100" width="600" height="340px">
						<div class="carousel-caption d-none d-md-block">
							<h5>First slide label</h5>
							<p>Some representative placeholder content for the first
								slide.</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="/ASSIGNMENT_JAVA5/image/IMG_9453_retouch-600x331.jpg"
							class="d-block w-100" width="600" height="340px">
						<div class="carousel-caption d-none d-md-block">
							<h5>Second slide label</h5>
							<p>Some representative placeholder content for the second
								slide.</p>
						</div>
					</div>

				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
		<div class="col-6 text-center" style="height: 350px">
			<div>
				<img alt="" src="/ASSIGNMENT_JAVA5/image/Banner_Sale-off-1.jpg"
					width="600" height="340px">
			</div>
			<div></div>
		</div>
	</div>



	<div class="row mt-5">
		<div class="col-3">
			<div class="card ">
				<div class="card-body row ">
					<div class="col-1"></div>
					<div class="col-2 text-center">
						<img alt="" src="/ASSIGNMENT_JAVA5/image/check.png">
					</div>
					<div class="col-8 ">
						<h5>Chất lượng</h5>
					</div>
					<div class="col-1"></div>
				</div>
			</div>
		</div>
		<div class="col-3">
			<div class="card ">
				<div class="card-body row ">
					<div class="col-1"></div>
					<div class="col-2 text-center">
						<img alt="" src="/ASSIGNMENT_JAVA5/image/delivery-truck.png">
					</div>
					<div class="col-8 ">
						<h5>Miễn phí ship</h5>
					</div>
					<div class="col-1"></div>
				</div>
			</div>
		</div>
		<div class="col-3">
			<div class="card ">
				<div class="card-body row ">
					<div class="col-1"></div>
					<div class="col-2 text-center">
						<img alt="" src="/ASSIGNMENT_JAVA5/image/opposite-arrows.png">
					</div>
					<div class="col-8 ">
						<h5>14 ngày đổi trả</h5>
					</div>
					<div class="col-1"></div>
				</div>
			</div>
		</div>
		<div class="col-3">
			<div class="card ">
				<div class="card-body row ">
					<div class="col-1"></div>
					<div class="col-2 text-center">
						<img alt="" src="/ASSIGNMENT_JAVA5/image/phone-call.png">
					</div>
					<div class="col-8 ">
						<h5>24/7 Hỗ trợ</h5>
					</div>
					<div class="col-1"></div>
				</div>
			</div>
		</div>
	</div>

	<div class="row mt-5">
		<h1>Danh mục</h1>
		<hr />
		<c:forEach var="item" items="${listCategory}">
		<div class="col-3">
			<div class="card ">
				<div class="card-body row ">
					
						<div class="col-1"></div>
						<div class="col-2 text-center">
							<img alt=""
								src="https://cf.shopee.vn/file/687f3967b7c2fe6a134a2c11894eea4b_tn"
								width="60px">
						</div>
						<div class="col-8 mt-3">
							<a href="/ASSIGNMENT_JAVA5/findProductByCategoryId/${item.id}" class="ms-2">${item.name}</a>
							
						</div>
						<div class="col-1"></div>
					
				</div>
			</div>
			
		</div>
		</c:forEach>


	</div>
	<div class="row mt-5">
		<h1>Sản phẩm</h1>
		<hr />
		<c:forEach var="item" items="${listProduct}">
				<div class="col-3 mt-3">
			<div class="card ">
				<div class="card-body row ">
					<div class="text-center">
						<img
							src="/ASSIGNMENT_JAVA5/storage/${item.image}"
							width="250px" height="250px">
					</div>
				</div>
				<div class="card-footer bg-white">

					<div class="text-center">
						<p class="fw-bold text-primary fs-5">${item.name}</p>
						<p>${item.price}</p>
					</div>
					<div class="row">
						<div class="col-6 text-center">
							<button class="btn btn-primary btn-rounded">Mua</button>
						</div>
						<div class="col-6">
							<a type="button" href="/ASSIGNMENT_JAVA5/productDetails/${item.id}" class="btn btn-warning btn-rounded ">Xem</a>
						</div>


					</div>

				</div>
			</div>
		</div>
		</c:forEach>
	
		
		
		
	</div>



</div>