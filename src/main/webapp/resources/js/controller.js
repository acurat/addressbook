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
/*
			$scope.addContact = function() {
				$location.path("/contact/add");
			};
*/
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
/*
			$scope.back = function() {
				$location.path("/contacts");
			};

			$scope.edit = function() {
				$location.path('/contact/edit/' + $scope.contact.contactId);
			};
*/
			var contact = Contact.get({
				id : $routeParams.id
			}, function() {

				$scope.contact = GenericFunctions.convertToDisplay(contact);
				Store.saveContact(contact);

			});

			$scope.$on("$destroy", function() {
				delete $rootScope.success;
				delete $rootScope.message
			});
		} ]);