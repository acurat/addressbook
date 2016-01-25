<!DOCTYPE html>
<html data-ng-app="AddressBook">
<head>
	<meta charset="UTF-8">
	<base href="${pageContext.request.contextPath}/">
	<title>My Contacts</title>
	
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lib/bootstrap.min.css">
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>

	
	<div class="container">

		<div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="page-header">
					<h2>My Address Book</h2>
				</div>
				<!-- view container for injected angular partials-->
				<div data-ng-view></div>
				<br>
			</div>
		</div>
		
	</div>
	
	<footer class="footer">
      <div class="container">
        <p class="text-muted">Demo for Intuit</p>
      </div>
    </footer>


	<!--  static resources -->
	<!--  ideally better if served from a CDN or AWS S3 -->
	
	<script src="${pageContext.request.contextPath}/resources/js/lib/angular.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/lib/angular-resource.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/lib/angular-route.min.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/js/application.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/service.js"></script>

</body>
</html>
