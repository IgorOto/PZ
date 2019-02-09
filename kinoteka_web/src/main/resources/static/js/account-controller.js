(function (){
    var app = angular.module("Application");

    var AccountController = function($scope,$translate, UserAuthService, TicketSeatService){
       $scope.tickets;

       function loadMyTickets(){
        TicketSeatService.myTickets()
        .then(res => {
            console.log(res.data);
            $scope.tickets = res.data;
        });
       }
       

        $scope.ticketTypeTranslate = function(ticket){
            if(ticket.type === 'DISCOUNT') return 'DISCOUNT_50';
            else return 'DISCOUNT_0';
        }

        $scope.removeTicket = function(index){
            let id = $scope.tickets[index].id;
            console.log(id);

            TicketSeatService.deleteTicket(id)
                .then(res => {
                    loadMyTickets();
                });
        }

        loadMyTickets();
    }

    app.controller("AccountController", AccountController);
})();