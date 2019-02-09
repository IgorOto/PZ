(function(){
    var app = angular.module("Application");

    app.service('ShowingService', function($http, DateService){
        var url ="/api/showing";

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };

        function getShowings(){
            return $http.get(url);
        }

        function getShowingsByMovie(movieId){
            let today = DateService.today().toLocaleDateString('en-GB');
            let detailUrl = `${url}/query/movie/${movieId}/after/${today}`;
            return $http.get(detailUrl);
        }

        function deleteShowing(showingId){
            let detailUrl = `${url}/${showingId}`;
            return $http.delete(detailUrl);
        }

        function getShowingsByLangAndDate(lang, date){
            let dateString = date.toLocaleDateString('en-GB');
            console.log(`date string : ${dateString}`);
            let detailUrl = `${url}/query/lang/${lang}/date/${dateString}`;
            return $http.get(detailUrl);
        }

        function addShowing(showing){
            let date = showing.date.toLocaleDateString('en-GB');
            let time = showing.time.toLocaleTimeString('en-GB').substring(0,5);
            let datetime = `${date}_${time}`;
            console.log(datetime);
            let showingObj = {
                datetime: datetime,
                movieId: Number.parseInt(showing.movie),
                roomId: Number.parseInt(showing.room)
            }
            console.log(showing);
            console.log(url);
            console.log(showingObj);
           return $http.post(url, showingObj, config);
        }

        return{
            getShowings: getShowings,
            addShowing: addShowing,
            deleteShowing: deleteShowing,
            getShowingsByMovie: getShowingsByMovie,
            getShowingsByLangAndDate: getShowingsByLangAndDate
        };
    });
})();