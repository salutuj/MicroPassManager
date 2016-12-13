(function(){
  'use strict';
  angular.module('passwordModule').service('PasswordService', PasswordService);
  PasswordService.$inject = ['$http', 'passwordWsUrl'];
  function PasswordService($http, passwordWsUrl){
    this.storePassword = function(passwordData){
      var newPasswordData = {          
          'user': passwordData.user,
          'service' : passwordData.services[0],
      }
      return $http.post(passwordWsUrl, newPasswordData).then(function(result){
        return result.data; //.data;
      });
    }
    
    this.readPassword = function(passwordData){
      var config = {'params' : { 
        'passwordData' : {
          'user': passwordData.user,
          'service.name' : passwordData.services[0].name
         }
        }, 'headers' : {
          'Accept' : 'application/json;charset=utf-8'
        }
      }
      return $http.get(passwordWsUrl, config).then(function(result){
        return result.data;
      });
    }
    
    this.updatePassword = function(passwordData){
      var newPasswordData = {          
          'user': passwordData.user,
          'service' : passwordData.services[0],
      }
      return $http.put(passwordWsUrl, newPasswordData).then(function(result){
        return result; //.data;
      });
    }
    
    this.removePassword = function(passwordData){
      var newPasswordData = {          
          'user': passwordData.user,
          'service.name' : passwordData.services[0].name,
      }
      return $http.delete(passwordWsUrl, newPasswordData).then(function(result){
        return result; //.data;
      });
    }
  }
})()