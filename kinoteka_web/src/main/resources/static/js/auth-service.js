(function(){
    var app = angular.module("Application");

    app.service('UserAuthService', function($http){
        var url ="/api/user";
        var auth;

        function init(){
            $http.get(url)
                .then(onAuthSuccess);
        }

        function getPromise(){
            return $http.get(url);
        }

        function onAuthSuccess(response){
            console.log("auth success");
            auth = response.data;
        }

        function getAuth(){
            if(auth === undefined) init();
            return auth;
        }

        return{
            get: getAuth,
            promiseMe: getPromise,
            init: init
        };
    });
})();