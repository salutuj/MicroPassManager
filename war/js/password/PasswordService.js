(function(){
  'use strict';
  angular.module('passwordModule').service('PasswordService', PasswordService);
  PasswordService.$inject = ['$filter', '$resource', 'passwordWsUrl'];
  
  function PasswordService($filter, $resource, passwordWsUrl){
    // WS-DAO
    var Password = $resource(passwordWsUrl, {}, {}, {     stripTrailingSlashes: false});
    
    
    this.storePassword = function(passwordData){
      var newPasswordData = {          
          'user': passwordData.user,
          'service' : passwordData.services[0],
      }
      return Password.save( {passwordData : $filter('json')(newPasswordData,0) } );
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
      return Password.get(config);
    }
    
    this.updatePassword = function(passwordData){
      var newPasswordData = {          
          'user': passwordData.user,
          'service' : passwordData.services[0],
      }
      return Password.put(newPasswordData);
    }
    
    this.removePassword = function(passwordData){
      var newPasswordData = {          
          'user': passwordData.user,
          'service.name' : passwordData.services[0].name,
      }
      return Password.remove(newPasswordData);
    }
  }
})()