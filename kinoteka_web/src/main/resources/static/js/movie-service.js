(function(){
    var app = angular.module("Application");

    app.service('MovieService', function($http){
        var movieUrl ="/api/movie";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };

        function getMovies(){
            return $http.get(movieUrl);
        }

        function getMoviesByLang(lang){
            let langMoviesUrl = `${movieUrl}/${lang}`;
            return $http.get(langMoviesUrl);
        }


        function deleteMovie(movieId){
            let url = `${movieUrl}/${movieId}`;
            return $http.delete(url);
        }

        function addMovie(movie){

            let movieObj =  {
                title: movie.title,
                description: movie.description,
                poster: movie.poster,
                link: movie.link,
                trailer: movie.trailer,
                minutes: Number.parseInt(movie.minutes),
                price: Number.parseFloat(movie.price),
                lang: movie.lang
            };

            console.log(movieObj);
            console.log(movieUrl);
           return $http.post(movieUrl, movieObj, config);
        }

        return{
            getMovies: getMovies,
            getMoviesByLang: getMoviesByLang,
            addMovie: addMovie,
            deleteMovie: deleteMovie
        };
    });
})();