package com.example.karate.runners;

import com.example.karate.configuration.ProfileResolver;
import com.example.karate.configuration.TestConfiguration;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@ActiveProfiles(resolver = ProfileResolver.class)
public class TestRunner {

    @Autowired
    private Properties propertiesConfiguration;

    @Test
    public void testParallel() {

        String threads = (String) propertiesConfiguration.get("execution.threads");

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        Results results = Runner.path("classpath:features")
                .outputCucumberJson(true)
                .tags("~@ignore").parallel(Integer.parseInt(threads));
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }
}
