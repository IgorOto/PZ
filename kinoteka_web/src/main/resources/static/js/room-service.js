(function(){
    var app = angular.module("Application");

    app.service('RoomService', function($http){
        var url ="/api/room";

        function getRooms(){
            return $http.get(url);
        }


        return{
            getRooms: getRooms
        };
    });
})();