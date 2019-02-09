$(document).ready(function onDOMReady(){
    var PL, EN;
    var ajaxCounter = 0;

        $.getJSON("/api/lang/PL", function(result){
            PL = result;
            ajaxCounter++;
            readCookie();
        });

        $.getJSON("/api/lang/EN", function(result){
            EN = result;
            ajaxCounter++;
            readCookie();
        });

        $("#EN").click(function(){
            translateEN();
            Cookie.setCookie("lang", "EN", 365);
        });

        $("#PL").click(function(){
            translatePL();
            Cookie.setCookie("lang", "PL", 365);
        });

        $("#register-btn").click(function onRegisterClicked(){
            let username1 = $("#exampleInputEmail1").val();
            let password1 = $("#exampleInputPassword1").val();
            let langVal = Cookie.showCookie("lang");
            if(langVal === undefined || langVal.length === 0) langVal = "PL";
            if(username1.length === 0 || password1.length === 0) return;

            $.ajax({
                url         : "/api/user", //wymagane, gdzie się łączymy
                method      : "post", //typ połączenia, domyślnie get
                dataType    : 'json', //typ danych jakich oczekujemy w odpowiedzi
                data        : {
                    username : `${username1}`,
                    password : `${password1}`,
                    lang: `${langVal}`
                },
                dataType: 'text'
                })
                .done(function(res,textStatus, xhr){
                    console.log(res);
                    console.log("done");
                    $("#register-error").removeClass("show");
                    $("#register-error").addClass("hide");
                    $("#register-success").removeClass("hide");
                    $("#register-success").addClass("show");

                })
                .fail(function(res,textStatus, xhr){
                    console.log(res);
                    console.log("fail");
                    $("#register-success").removeClass("show");
                    $("#register-success").addClass("hide");
                    $("#register-error").removeClass("hide");
                    $("#register-error").addClass("show");
                });
            
            console.log(`${username} ${password}`);
        });

        setTimeout(showLoginError, 200);

    function showLoginError(){
        let isError = $("#login-error").data("error");
        console.log(isError);
        if(isError === true) $("#login").trigger("click");
    }

    function readCookie(){
        if(ajaxCounter !== 2) return;
        let cookieVal = Cookie.showCookie("lang");
        console.log(cookieVal);
        if(cookieVal === "EN") translateEN();
        else translatePL();
    }

    function translateEN(){
        translate(EN);
        $("#EN").addClass("active");
        $("#PL").removeClass("active");
    }

    function translatePL(){
        translate(PL);
        $("#PL").addClass("active");
        $("#EN").removeClass("active");
    }

    function translate(lang){
        $("#login").text(lang.login);
        $("#register").text(lang.register);
        $("#title").text(lang.title);
        $("#loginTitle").text(lang.login);
        $("#login-username-lbl").text(lang.username);
        $("#login-passwd-lbl").text(lang.password);
        $("#login-btn").text(lang.login);
        $("#login-error").text(lang.loginErrorMsg);
        $("#registerTitle").text(lang.register);
        $("#register-username-lbl").text(lang.username);
        $("#register-passwd-lbl").text(lang.password);
        $("#register-btn").text(lang.register);
        $("#register-error").text(lang.registerErrorMsg);
        $("#register-success").text(lang.registerSuccessMsg);
    }
});