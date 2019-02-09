(function () {
    var app = angular.module("Application");

    var MenuController = function ($scope, $translate, UserAuthService, SettingsService) {
        $scope.role;
        $scope.username;
        UserAuthService.promiseMe().then(res => {
            $scope.username = res.data.username;
            $scope.role = res.data.role.name;
        });

        $scope.activate = function (id) {
            let idString = "#" + id;
            console.log(idString);
            $("nav ul li").removeClass("active");
            $(idString).addClass("active");
        }

        $scope.skin = function(nr){
            let body = document.querySelector("body");
            body.classList.remove("skinOne");
            body.classList.remove("skinTwo");
            switch(nr){
                case 1:
                    body.classList.add("skinOne");
                break;
                case 2:
                body.classList.add("skinTwo");
                break;
            }
        }

        $scope.changeLanguage = function (langKey) {
            $translate.use(langKey);
            $(document).ready(function () {
                if (langKey === 'en') {
                    $("#en").addClass("active-lang");
                    $("#pl").removeClass("active-lang");
                    SettingsService.setLang("EN");
                } else {
                    $("#pl").addClass("active-lang");
                    $("#en").removeClass("active-lang");
                    SettingsService.setLang("PL");
                }
            });
        }



    }

    app.controller("MenuController", MenuController);
})();