(function(){
    var app = angular.module("Application");

    app.service('DateService', function($http){

        function today(){
            return new Date(new Date().toDateString());
        }

        function isBeforeToday(date){
            return new Date(date.toDateString()) < today();
        }

        


        return{
            isBeforeToday: isBeforeToday,
            today: today
        };
    });
})();