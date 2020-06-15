package com.example.karate.runners;

import com.example.karate.configuration.ProfileResolver;
import com.example.karate.configuration.TestConfiguration;
import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import static org.apache.commons.io.FileUtils.listFiles;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@ActiveProfiles(resolver = ProfileResolver.class)
@KarateOptions(
        features = "classpath:features",
        tags = {"~@ignore"})
public class TestRunner {

    @Autowired
    private Properties propertiesConfiguration;

    @Test
    public void testParallel() {

        String threads = (String) propertiesConfiguration.get("execution.threads");

        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        Results results = Runner.parallel(getClass(), Integer.parseInt(threads), "results/surefire-reports");
        generateReport(results.getReportDir());
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }

    private void generateReport(String karateOutputPath) {
        Collection<File> files = listFiles(
                new File(karateOutputPath),
                new String[]{"json"},
                true);

        ArrayList<String> paths = new ArrayList<>(files.size());
        files.stream()
                .map(File::getAbsolutePath)
                .forEach(p -> paths.add(p));

        Configuration configuration = new Configuration(new File("results"), "Karate-Spring-Example");

        ReportBuilder builder = new ReportBuilder(paths, configuration);
        builder.generateReports();
    }
}
