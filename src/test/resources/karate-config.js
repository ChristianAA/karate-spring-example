function() {
    // Test environment by default
    var env = karate.env;
    if(!env) {
        env = 'test';
    }
    // --
    
    karate.configure('ssl', { 
        trustAll: true 
    });
    karate.configure('report', { 
        showLog: true, 
        showAllSteps: true 
    });
    karate.configure('connectTimeout', 10000);
    karate.configure('readTimeout', 10000);
    karate.configure('headers', {Accept: '*/*'});
    
    
    var config = {};
    config.global = karate.callSingle('classpath:features/common_resources/global-variables.feature');
    
    
    // BASE_URL, environment dependent
    if(env == 'test') {
        config.BASE_URL = 'https://age-of-empires-2-api.herokuapp.com/api/v1/';
    }
    else if(env == 'jenkins') {
        config.BASE_URL = 'https://age-of-empires-2-api.herokuapp.com/api/v1/';
    }
    return config;
}