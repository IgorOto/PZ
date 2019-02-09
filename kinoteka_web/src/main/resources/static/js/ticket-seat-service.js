(function () {
    var app = angular.module("Application");

    app.service('TicketSeatService', function ($http, UserAuthService) {
        var roomUrl = "/api/room";
        var showingUrl = "/api/showing";
        var ticketUrl = "/api/ticket";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };

        function getSeatsByRoom(roomId) {
            let detailUrl = `${roomUrl}/${roomId}/seats`;
            return $http.get(detailUrl);
        }

        function getTicketsByShowing(showingId) {
            let detailUrl = `${showingUrl}/${showingId}/tickets`;
            return $http.get(detailUrl);
        }

        function buyTickets(selectedTicketSeats, selectedShowing) {
            let detailUrl = `${showingUrl}/${selectedShowing.id}/tickets`;
            let auth = UserAuthService.get();
        
            wrapper = {
                userId: auth.id,
                tickets: []
            };

            angular.forEach(selectedTicketSeats, function(ticketSeat){
                let ticketRep = {
                    seatId: ticketSeat.id,
                    ticketType: ticketSeat.status
                };

                wrapper.tickets.push(ticketRep);
            });

            return $http.post(detailUrl, wrapper, config);
        }

        function myTickets(){
            let auth = UserAuthService.get();
            let detailUrl = `${ticketUrl}/user/${auth.id}`;
            return $http.get(detailUrl);
        }

        function deleteTicket(id){
            let detailUrl = `${ticketUrl}/${id}`;

            return $http.delete(detailUrl);
        }


        return {
            getSeatsByRoom: getSeatsByRoom,
            getTicketsByShowing: getTicketsByShowing,
            buyTickets: buyTickets,
            myTickets: myTickets,
            deleteTicket: deleteTicket
        };
    });
})();