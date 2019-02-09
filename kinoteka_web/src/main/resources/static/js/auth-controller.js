(function (){
    var app = angular.module("Application");

    var AuthController = function($scope,$translate, UserAuthService){
        $scope.auth;
        $scope.getAuth = function(){
            console.log(UserAuthService.get().username);
           $scope.auth = UserAuthService.get();
        }
    }

    app.controller("AuthController", AuthController);
})();