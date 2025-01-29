package com.example.karate.runners;

import com.example.karate.configuration.ProfileResolver;
import com.example.karate.configuration.TestConfiguration;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;


@ContextConfiguration(classes = {TestConfiguration.class})
@ExtendWith(SpringExtension.class)
@ActiveProfiles(resolver = ProfileResolver.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRunner {

    public static final String TESTS_PATH = "classpath:features";
    @Autowired
    private Properties propertiesConfiguration;

    @Test
    public void testParallel() {

        System.setProperty("execution.tags", System.getProperty("execution.tags", (String) this.propertiesConfiguration.get("execution.tags")));
        String tags = System.getProperty("execution.tags");
        List<String> tagsList = new ArrayList<>(List.of("~@ignore"));
        if (tags != null && !tags.isEmpty()) {
            if (tags.contains("\"")) {
                tags = tags.replace("\"", "");
                tagsList.addAll(Arrays.asList(tags.split(",")));
            } else {
                tagsList.add(tags);
            }
        }


        Results results = Runner.path(TESTS_PATH)
                .outputCucumberJson(true)
                .tags(tagsList).parallel(1);
        generateReport(results);
    }

    private void generateReport(Results results) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(results.getReportDir()), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "FREE TO GAME Karate Results");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}