<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	  <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/bootstrap.min.css">
<link rel="stylesheet" href="/ASSIGNMENT_JAVA5/css/mdb.min.css">
</head>
<style>
@media ( min-width : 1025px) {
	.h-custom {
		height: 140vh !important;
	}
}
</style>
<body>
	<section class="h-100 h-custom" style="background-color: #8fc4b7;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-lg-8 col-xl-6">
					<div class="card rounded-3">
						<img
							src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img3.webp"
							class="w-100"
							style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
							alt="Sample photo">
						<div class="card-body p-4 p-md-5">
							<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2 text-center text-danger">REGISTER</h3>

							<form:form action="/ASSIGNMENT_JAVA5/register"
								method="post" modelAttribute="account"
								enctype="multipart/form-data">
								
								<div class="row  mb-3">
									
									<div class="col-12">
										<div class="form-group">
											<label>FullName:</label>
											<form:input path="fullname" class="form-control" />

											<form:errors path="fullname" class="text-danger" />
										</div>
										<div class="form-group">
											<label>Email:</label>
											<form:input path="email" class="form-control" />

										</div>
										<div class="form-group">
											<label>Username:</label>
											<form:input path="username" class="form-control" />
											<form:errors path="username" class="text-danger" />
										</div>
										<div class="form-group">
											<label>Password:</label>
											<form:input path="password" class="form-control" />
											<form:errors path="password" class="text-danger" />
										</div>
										<div class="form-group">
											<label>Photo:</label>
											<form:input type="file" path="" name="upload_file"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Address:</label>
											<form:input path="address" class="form-control" />
										</div>
										<div class="form-group">
											<label>Phone Number:</label>
											<form:input path="phoneNumber" class="form-control" />
										</div>
										
										<button class="btn btn-primary mt-3">Thêm</button>
										${message}
									</div>

								</div>


							</form:form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>