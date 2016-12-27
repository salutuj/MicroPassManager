(function(){
  angular.module('ngMicroPassManagerTestApp', ['ngResource', 'passwordModule']).   
  controller('defaultTestCtrl', ['$scope', 'PasswordService', function($scope, PasswordService){
    $scope.test = {
      'title' : "MicroPassManager test",
      'btnCreate' : {'caption' : "Create"},
      'btnRead'   : {'caption' :  "Read"},
      'btnUpdate' : {'caption' :  "Update"},
      'btnDelete' : {'caption' :  "Delete"},
      'passwordData' : {
        'user' : {
          'name' : 'test',
          'password' : 'test'
        },
        'services' : [{
          'name' : 'serwisTestowy',
          'login' : 'loginTestowy',
          'password' : 'hasloTestowe'
        }]
      }
    };
       
    $scope.test.storePassword = function(passwordData){
      $scope.test.result = PasswordService.storePassword(passwordData);
    }
    
    $scope.test.readPassword = function(passwordData){
      $scope.test.result = PasswordService.readPassword(passwordData);
    }
    
    $scope.test.updatePassword = function(passwordData){
      $scope.test.result = PasswordService.updatePassword(passwordData);
    }
    
    $scope.test.removePassword = function(passwordData){
      $scope.test.result =  PasswordService.removePassword(passwordData);
    }
  }]);
})()