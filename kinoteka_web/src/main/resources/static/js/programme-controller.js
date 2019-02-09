(function () {
    var app = angular.module("Application");

    var ProgrammeController = function ($scope, $translate, $sce, UserAuthService, MovieService, SettingsService, DateService, ShowingService, TicketSeatService) {

        const AVAILABLE = "FREE", NORMAL = "NORMAL", DISCOUNT = "DISCOUNT";

        $scope.weekMovies;

        $scope.showings;

        $scope.selectedShowing;

        $scope.pickedDate = DateService.today();

        $scope.$watch('pickedDate', function () {
            console.log(`picked date: ${$scope.pickedDate}`);
            if (DateService.isBeforeToday($scope.pickedDate)) $scope.pickedDate = DateService.today();
            getShowingsByPickedDate();
        }, true);

        $scope.trustSrc = function (src) {
            return $sce.trustAsResourceUrl(src);
        }

        function getShowingsByPickedDate() {
            let lang = SettingsService.getLang();
            ShowingService.getShowingsByLangAndDate(lang, $scope.pickedDate)
                .then(res => {
                    $scope.showings = res.data;
                });
        }



        $scope.$on('lang', function (event, arg) {
            //getMoviesByLang(arg);
            //setToday();
            //getShowingsByPickedDate();
        });

        function getMoviesByLang(lang) {
            MovieService.getMoviesByLang(lang)
                .then(res => {
                    $scope.weekMovies = res.data;
                });
        }

        $scope.setToday = setToday;

        function setToday() {
            $scope.pickedDate = DateService.today();
        }

        $scope.findByMovie = function (index) {
            console.log($scope.weekMovies[index]);
            let movieId = $scope.weekMovies[index].id;
            ShowingService.getShowingsByMovie(movieId)
                .then(res => {
                    $scope.showings = res.data;
                    console.log($scope.showings);
                });
        }

        $scope.loadByDate = function () {
            getShowingsByPickedDate();
        }

        $scope.seats;

        $scope.tickets;

        var selectedSeats;

        $scope.getSeats = function (index) {
            console.log(`showing id: ${$scope.showings[index].id}`);
            $scope.selectedShowing = $scope.showings[index];
        }

        $scope.$watch('selectedShowing', function () {
            refreshSeats();
        });

        function refreshSeats() {
            selectedSeats = [];
            if ($scope.selectedShowing === undefined) return;
            TicketSeatService.getSeatsByRoom($scope.selectedShowing.room.id)
                .then(res => {

                    let resSeats = res.data;

                    angular.forEach(resSeats, function (seat) {
                        seat.status = AVAILABLE;
                    });

                    $scope.seats = resSeats;

                    TicketSeatService.getTicketsByShowing($scope.selectedShowing.id)
                        .then(res2 => {
                            $scope.tickets = res2.data;
                        });
                });
        }

        $scope.hasTicket = function (seat) {
            let seatHasTicket = false;

            angular.forEach($scope.tickets, function (ticket) {
                if (ticket.seat !== undefined) {
                    if (ticket.seat.row === seat.row && ticket.seat.col === seat.col) {
                        seatHasTicket = true;
                    }
                }
            });

            return seatHasTicket;
        }

        $scope.isMyTicket = function(seat){
            let myTicket = false;
            let auth = UserAuthService.get();
            angular.forEach($scope.tickets, function(ticket){
                if(ticket.seat !== undefined){
                    if(ticket.seat.row === seat.row && ticket.seat.col === seat.col && ticket.user !== null && ticket.user !== undefined){
                        if(ticket.user.id === auth.id) myTicket = true;
                    }
                }
            });
            return myTicket;
        }


        $scope.selectSeat = function (seat) {
            let activeSeat;
            angular.forEach($scope.seats, function (item) {
                if(item.col === seat.col && item.row === seat.row) activeSeat = item;
            });
            let hasActiveSeat = false;
            angular.forEach(selectedSeats, function (item) {
                if (activeSeat.row === item.row && activeSeat.col === item.col) {
                    hasActiveSeat = true;
                }
            });

            if (activeSeat.status === AVAILABLE) activeSeat.status = NORMAL;
            else if (activeSeat.status === NORMAL) activeSeat.status = DISCOUNT;
            else if (activeSeat.status === DISCOUNT) activeSeat.status = AVAILABLE;

            if (!hasActiveSeat && activeSeat.status !== AVAILABLE) selectedSeats.push(activeSeat);
            else if (hasActiveSeat && activeSeat.status === AVAILABLE){
                let index = selectedSeats.indexOf(activeSeat);
                if(index > -1) selectedSeats.splice(index, 1);
            }
        }

        $scope.buyTickets = function () {
            console.log("buy tickets action");
            TicketSeatService.buyTickets(selectedSeats, $scope.selectedShowing)
                .then(res => {
                    console.log(`buy tickets response: ${res.data}`);
                    refreshSeats();
                });
        }

        getMoviesByLang(SettingsService.getLang());

    }

    app.controller("ProgrammeController", ProgrammeController);
})();