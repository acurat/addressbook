/*Initialize angular module for the application*/
var addressBook = angular.module('AddressBook', ['ngRoute', 'ContactServices']);

/*Routing for view navigation*/
addressBook.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/contacts', {
		templateUrl : 'resources/partials/allContacts.html',
		controller : 'AllContactsController'
	}).when('/contact/add', {
		templateUrl : 'resources/partials/contact.html',
		controller : 'AddEditContactController'
	}).when('/contact/:id', {
		templateUrl : 'resources/partials/viewContact.html',
		controller : 'GetContactController'
	}).when('/contact/edit/:id', {
		templateUrl : 'resources/partials/contact.html',
		controller : 'AddEditContactController'
	}).otherwise({
		redirectTo : '/contacts'
	});
} ]);
