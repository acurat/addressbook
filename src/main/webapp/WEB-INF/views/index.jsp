<!DOCTYPE html>
<html data-ng-app="AddressBook">
<head>
	<meta charset="UTF-8">
	<base href="${pageContext.request.contextPath}/">
	<title>Address Book</title>
	
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	
	<link rel="stylesheet"
		href="https://s3.amazonaws.com/arjuncurat/addressbook/css/main.css">
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
			<p class="text-muted">Free to copy and distribute</p>
		</div>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-resource.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.min.js"></script>
	<script
		src="https://s3.amazonaws.com/arjuncurat/addressbook/js/application.js"></script>
	<script
		src="https://s3.amazonaws.com/arjuncurat/addressbook/js/controller.js"></script>
	<script
		src="https://s3.amazonaws.com/arjuncurat/addressbook/js/service.js"></script>

</body>
</html>
