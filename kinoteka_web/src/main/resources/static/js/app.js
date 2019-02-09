var translationsPL = {
    HELLO: "siema",
    LOGOUT: "wyloguj",
    ADMIN_PANEL: "panel admina",
    PROGRAMME: "repertuar",
    ACCOUNT: "moje bilety",
    ADD_MOVIE: "dodaj film",
    MOVIE_TITLE: "Tytuł filmu",
    MOVIE_DESC: "Opis",
    MOVIE_POSTER: "Plakat",
    MOVIE_TRAILER: "Zwiastun",
    MOVIE_HOURS: "Ilość godzin",
    MOVIE_MINUTES: "Ilość minut",
    MOVIE_PRICE: "Cena biletu normalnego",
    MOVIE_LANG: "Film w języku",
    ALL_MOVIES: "Filmy",
    OPTIONS: "Opcje",
    ADD_SHOWING_TITLE: "Dodaj seans",
    SHOWING_DATE: "Data seansu",
    ROOM: "Sala",
    SHOWING_TIME: "Godzina seansu",
    ADD_SHOWING: "Dodaj seans",
    ALL_SHOWINGS: "Seanse",
    SHOWING_END_DATE: "Koniec seansu",
    VIEW: "Zobacz",
    TODAY: "Dzis",
    BUY_TICKETS: "Kup bilety",
    NORMAL: "Normalny",
    DISCOUNT: "Ulgowy",
    FREE: "Wolne miejsce",
    OCCUPIED: "Zajęte miejsce",
    YOUR_TICKET: "Twoje bilety",
    MY_TICKETS: "Moje bilety",
    DISCOUNT_0: "Bilet normalny",
    DISCOUNT_50: "Znizka 50%",
    ROW: "Rząd",
    SEAT: "Miejsce",
    SKIN: "Skórka",
    SKIN_DEFAULT: "Skórka 3",
    SKIN_1: "Skórka 1",
    SKIN_2: "Skórka 2"
};

var translationsEN = {
    HELLO: "HAI",
    LOGOUT: "log out",
    ADMIN_PANEL: "admin panel",
    PROGRAMME: "programme",
    ACCOUNT: "my tickets",
    ADD_MOVIE: "add movie",
    MOVIE_TITLE: "Movie title",
    MOVIE_DESC: "Description",
    MOVIE_POSTER: "Poster",
    MOVIE_TRAILER: "Trailer",
    MOVIE_HOURS: "How many hours",
    MOVIE_MINUTES: "How many minutes",
    MOVIE_PRICE: "Standard ticket price",
    MOVIE_LANG: "Movie language",
    ALL_MOVIES: "Movies",
    OPTIONS: "Options",
    ADD_SHOWING_TITLE: "Add showing",
    SHOWING_DATE: "Showing date",
    ROOM: "Room",
    SHOWING_TIME: "Showing time",
    ADD_SHOWING: "Add showing",
    ALL_SHOWINGS: "Showings",
    SHOWING_END_DATE: "Showing end",
    VIEW: "Show",
    TODAY: "Today",
    BUY_TICKETS: "Buy tickets",
    NORMAL: "Normal ticket",
    DISCOUNT: "Discount ticket",
    FREE: "Free seat",
    OCCUPIED: "Occupied seat",
    YOUR_TICKET: "Your tickets",
    MY_TICKETS: "My tickets",
    DISCOUNT_0: "Normal ticket",
    DISCOUNT_50: "Discount 50%",
    ROW: "Row",
    SEAT: "Seat",
    SKIN: "Skin",
    SKIN_DEFAULT: "Skin 3",
    SKIN_1: "Skin 1",
    SKIN_2: "Skin 2"
};

(function(){
    var app = angular.module("Application", ["ngRoute","pascalprecht.translate"]);
    
    app.run(function(UserAuthService){
        UserAuthService.init();
    });

    app.config(function ($routeProvider) {
        $routeProvider
            .when("/programme", {
                templateUrl: "/partials/_programme.html",
                controller: "ProgrammeController"
            })
            .when("/account", {
                templateUrl: "/partials/_account.html",
                controller: "AccountController"
            })
            .when("/admin", {
                templateUrl: "/partials/_admin2.html",
                controller: "AdminController"
            })
            .otherwise({ redirectTo: "/programme" });
    });

    app.config(['$translateProvider', function ($translateProvider) {
        $translateProvider.translations('en', translationsEN);
        $translateProvider.translations('pl', translationsPL);
        $translateProvider.fallbackLanguage('pl');
        $translateProvider.preferredLanguage('pl');
      }]);

      app.filter('retUniqDates', function () {
        return function (items, sampleDate) {
            var filteredDates = [];
            angular.forEach(items, function (item) {
                let add = true;
                angular.forEach(filteredDates, function (date) {
                    if (date === item.datetime.substring(0,10)) {
                        add = false;
                    }
                });
                if (add === true) filteredDates.push(item.datetime.substring(0, 10));
            });

            return filteredDates;
        }
    });

    app.filter('byDate', function () {
        return function (items, date) {
            var filtered = [];
            angular.forEach(items, function (item) {
                if (item.datetime.substring(0, 10) === date) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
    });

    app.filter('retUniqRows', function () {
        return function (items, sampleDate) {
            var filteredRows = [];
            angular.forEach(items, function (item) {
                let add = true;
                angular.forEach(filteredRows, function (row) {
                    if (row === item.row) {
                        add = false;
                    }
                });
                if (add === true) filteredRows.push(item.row);
            });

            return filteredRows;
        }
    });

    app.filter('byRow', function () {
        return function (items, row) {
            var filtered = [];
            angular.forEach(items, function (item) {
                if (item.row === row) {
                    filtered.push(item);
                }
            });
            return filtered;
        }
    });


})();