(function(){
    var app = angular.module("Application");

    app.service('SettingsService', function($rootScope){
        var lang = 'PL';

        function getLang(){
            return lang;
        }

        function setLang(langName){
            lang = langName;
            $rootScope.$broadcast('lang', langName);
        }


        return{
            getLang: getLang,
            setLang: setLang
        };
    });
})();