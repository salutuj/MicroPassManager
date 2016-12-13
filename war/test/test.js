(function(){
  angular.module('ngMicroPassManagerTestApp', ['passwordModule']).   
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
      return PasswordService.storePassword(passwordData).then(function(data){
        $scope.test.result = data;
      });
    }
    
    $scope.test.readPassword = function(passwordData){
      return PasswordService.readPassword(passwordData).then(function(data){
        $scope.test.result = data;
      });
    }
    
    $scope.test.updatePassword = function(passwordData){
      return PasswordService.updatePassword(passwordData).then(function(data){
        $scope.test.result = data;
      });
    }
    
    $scope.test.removePassword = function(passwordData){
      return PasswordService.removePassword(passwordData).then(function(data){
        $scope.test.result = data;
      });
    }
  }]);
})()