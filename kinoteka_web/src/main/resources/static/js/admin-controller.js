(function () {
    var app = angular.module("Application");

    var AdminController = function ($scope, MovieService, RoomService, ShowingService, SettingsService) {

        $scope.movies;

        $scope.showings;

        $scope.showingForm;

        $scope.movieForm = {
            hours: "1",
            minutes: "45",
            lang: "PL"
        }

        function loadMovies() {
            MovieService.getMovies()
                .then(res => {
                    $scope.movies = res.data;
                });
        }

        function loadShowings(){
            ShowingService.getShowings()
                .then(res => {
                    $scope.showings = res.data;
                });
        }

        function loadRooms() {
            RoomService.getRooms()
                .then(res => {
                    $scope.rooms = res.data;
                });
        }

        $scope.addMovie = function addMovie() {
            MovieService.addMovie($scope.movieForm)
                .then(res => {
                    loadMovies();
                });
        }

        $scope.addShowing = function addShowing() {
            ShowingService.addShowing($scope.showingForm)
            .then(res => {
                loadShowings();
            });
            console.log($scope.showingForm.time.getHours());
        }

        loadMovies();
        loadRooms();
        loadShowings();

        $scope.removeMe = function (index) {
            let movieId = $scope.movies[index].id;
            console.log(movieId);
            MovieService.deleteMovie(movieId)
                .then(res => {
                    loadMovies();
                });
        }

        $scope.removeShowing = function(index){
            let showingId = $scope.showings[index].id;
            ShowingService.deleteShowing(showingId)
                .then(res => {
                    loadShowings();
                });
        }

    }

    app.controller("AdminController", AdminController);
})();