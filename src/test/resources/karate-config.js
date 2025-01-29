function() {
    // Test environment by default
    var env = karate.env;
    if(!env) {
        env = 'test';
    }
    // --

    karate.configure('connectTimeout', 10000);
    karate.configure('readTimeout', 10000);
    karate.configure('headers', {Accept: '*/*'});
    karate.configure('report', { showLog: true, showAllSteps: true });
    karate.configure('logPrettyRequest', true);
    karate.configure('logPrettyResponse', true);
    
    var config = {};
    config.global = karate.callSingle('classpath:features/common_resources/global-variables.feature');
    
    
    // BASE_URL, environment dependent
    if(env == 'test') {
        config.BASE_URL = 'https://www.freetogame.com/api/';
    }
    else if(env == 'jenkins') {
        config.BASE_URL = 'https://www.freetogame.com/api/';
    }
    return config;
}