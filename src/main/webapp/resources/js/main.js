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

/*
 * 
 * Define all angular controllers here
 * 
 * 
 */

/*Main controller for the view all contacts view */

addressBook.controller('AllContactsController', [ '$scope', '$location',
		'$routeParams', 'Contact',
		function($scope, $location, $routeParams, Contact) {

			$scope.loading = true;
			$scope.message = "Please wait while contacts are being loaded...";

			var contacts = Contact.query({}, function() {
				$scope.contacts = contacts;
				$scope.loading = false;
			}, function() {
				$scope.message = "Oops something went wrong!";
			});

			$scope.addContact = function() {
				$location.path("/contact/add");
			};

			$scope.getContact = function(id) {
				$location.path("/contact/" + id);
			};
		} ]);

/*Controller for the adding or editing a contact view */

addressBook.controller('AddEditContactController', [
		'$scope',
		'$location',
		'$rootScope',
		'$routeParams',
		'Contact',
		'Store',
		'GenericFunctions',
		function($scope, $location, $rootScope, $routeParams, Contact, Store,
				GenericFunctions) {

			$scope.error = false;
			$scope.edit = false;
			var storedContact;

			if ($location.path().indexOf("edit") > -1) {

				$scope.edit = true;
				storedContact = Store.getContact();

				if (storedContact !== null) {
					$scope.contact = GenericFunctions
							.convertToDisplay(storedContact);
				} else {
					$location.path("/contact/" + $routeParams.id);
				}

			}

			$scope.submit = function() {
				$scope.error = false;
				storedContact = GenericFunctions.convertToSend($scope.contact,
						storedContact);

				if ($scope.contact.contactId) {
					Contact.update(storedContact, function() {
						$rootScope.success = true;
						$rootScope.message = "Contact updated successfully!";
						$location.path("/contact/" + $scope.contact.contactId);
					}, function() {
						//handle error accordingly
						$scope.error = true;
					});

				} else {

					Contact.save(storedContact, function(data) {
						$rootScope.success = true;
						$rootScope.message = "Contact saved successfully!";
						$location.path("/contact/" + data.contactId);
					}, function() {
						//handle error accordingly
						$scope.error = true;
					});
				}
			};

			$scope.cancel = function() {
				$location.path("/contacts");
			};

		} ]);


/*Controller for the viewing a contact page */

addressBook.controller('GetContactController', [
		'$scope',
		'$location',
		'$routeParams',
		'$rootScope',
		'Contact',
		'Store',
		'GenericFunctions',
		function($scope, $location, $routeParams, $rootScope, Contact, Store,
				GenericFunctions) {

			$scope.back = function() {
				$location.path("/contacts");
			};

			$scope.edit = function() {
				$location.path('/contact/edit/' + $scope.contact.contactId);
			};

			var contact = Contact.get({
				id : $routeParams.id
			}, function() {

				$scope.contact = GenericFunctions.convertToDisplay(contact);
				Store.saveContact(contact);

			});

			$scope.$on("$destroy", function() {
				$rootScope.success = false;
				$rootScope.message = "";
			});
		} ]);

addressBook.directive("appHeader", function(){

	return {
		
		restrict: 'AE',
		template: '<h2>My Address Book</h2>',
	};


});
/*
 * 
 * Define all service methods here
 * Ideally they would be separated by functionality/modules in larger projects
 * 
 * 
 */

var contactServices = angular.module('ContactServices', [ 'ngResource' ]);

contactServices.factory('Contact', [ '$resource', function($resource) {
	return $resource('api/contacts/:id', {
		contactId : '@id'
	}, {
		get : {
			method : 'GET',
			isArray : false
		},
		query : {
			method : 'GET',
			isArray : true
		},
		update : {
			method : 'PUT'
		}
	});
} ]);

/* Using a service to pass data between controllers (screens) */
contactServices.service('Store', function() {
	var contact;

	var saveContact = function(obj) {
		contact = obj;
	};

	var getContact = function() {
		return contact;
	};

	return {
		saveContact : saveContact,
		getContact : getContact
	};

});

/* Using a service for generic functions that can be used across the app */
contactServices.service('GenericFunctions', function() {

	var convertToSend = function(obj, contact) {
		if (contact === null) {
			contact = {};
		}
		contact.id = obj.contactId;
		contact.lastName = obj.lastName;
		contact.firstName = obj.firstName;

		if (obj.phone !== null) {
			if (obj.phone.number !== null) {
				if (contact.phones !== null && contact.phones.length > 0) {
					for (var i = 0; i < contact.phones.length; i++) {
						if (contact.phones[i].primaryPhone === true) {
							contact.phones[i].number = obj.phone.number;
						}
					}
				} else {
					var phone = {};
					phone.number = obj.phone.number;
					phone.type = 'Cell';
					phone.primaryPhone = true;
					contact.phones = [];
					contact.phones.push(phone);
				}
			}
		}

		if (obj.email !== null) {
			console.log(1);
			if (obj.email.address !== null) {
				console.log(2);
				if (contact.emails !== null && contact.emails.length > 0) {
					console.log(3);
					for (var j = 0; j < contact.emails.length; j++) {
						if (contact.emails[j].primaryEmail === true) {
							contact.emails[j].address = obj.email.address;
						}
					}
				} else {
					console.log(5);
					var email = {};
					email.address = obj.email.address;
					email.primaryEmail = true;
					contact.emails = [];
					contact.emails.push(email);
					console.log(6);

				}
			}
		}

		var address = contact.address;
		if (obj.address !== null) {
			if (contact.address === null) {
				contact.address = {};
			}
			contact.address.address1 = obj.address.address1;
			contact.address.address2 = obj.address.address2;
			contact.address.city = obj.address.city;
			contact.address.state = obj.address.state;
			contact.address.country = obj.address.country;
			contact.address.zip = obj.address.zip;

		}
		return contact;

	};

	var convertToDisplay = function(contactObj) {

		contact = {};
		contact.contactId = contactObj.id;
		contact.lastName = contactObj.lastName;
		contact.firstName = contactObj.firstName;
		contact.fullName = contact.firstName + ' ' + contact.lastName;

		var phones = contactObj.phones;
		if (phones !== null) {
			for (var i = 0; i < phones.length; i++) {
				if (phones[i].primaryPhone === true) {
					contact.phone = {};
					contact.phone.id = phones[i].id;
					contact.phone.number = phones[i].number;
				}

			}
		}
		var emails = contactObj.emails;
		if (emails !== null) {
			for (var k = 0; k < emails.length; k++) {
				if (emails[k].primaryEmail === true)
					contact.email = {};
				contact.email.id = emails[k].id;
				contact.email.address = emails[k].address;
			}

		}

		if (contactObj.address !== null) {
			contact.address = {};
			var address = contactObj.address;
			contact.address.address1 = address.address1;
			contact.address.address2 = address.address2;
			contact.address.city = address.city;
			contact.address.zip = address.zip;
			contact.address.state = address.state;
			contact.address.country = address.country;

			if (contact.address.city !== null) {
				contact.address.address3 = contact.address.city;
			}
			if (contact.address.state !== null && contact.address.state !== '') {
				if (contact.address.city !== null) {
					contact.address.address3 += ', ';
					contact.address.address3 += contact.address.state;
				} else {
					contact.address.address3 = contact.address.state;
				}
			}
			if (contact.address.country !== null) {
				contact.address.address4 = contact.address.country;
			}
			if (contact.address.zip !== null && contact.address.zip !== '') {
				if (contact.address.country !== null) {
					contact.address.address4 += ', ';
					contact.address.address4 += contact.address.zip;

				} else {
					contact.address.address4 = contact.address.zip;

				}
			}

		}

		return contact;

	};

	return {
		convertToDisplay : convertToDisplay,
		convertToSend : convertToSend
	};
});