package com.example.karate.configuration;

import com.example.karate.runners.TestRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

@ContextConfiguration
public class TestConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRunner.class);

    @Bean
    @Qualifier("propertiesConfiguration")
    public Properties propertiesConfiguration(ResourceLoader resourceLoader) throws IOException {
        Properties properties = new Properties();

        ProfileResolver profileResolver = new ProfileResolver();
        String[] profiles = profileResolver.resolve(getClass());

        ArrayList<String> propertiesFiles = new ArrayList<String>();
        propertiesFiles.add("classpath:application.properties");

        for (String profile : profiles) {
            propertiesFiles.add(String.format("classpath:application-%s.properties", profile));
        }

        for (String propertieFile : propertiesFiles) {

            Resource[] resource = ResourcePatternUtils
                    .getResourcePatternResolver(resourceLoader)
                    .getResources(propertieFile);

            InputStream input = com.example.karate.configuration.TestConfiguration.class.getClassLoader().getResourceAsStream(resource[0].getFilename());

            if (input != null) {
                properties.load(input);
            } else {
                String profile = propertieFile.substring(propertieFile.indexOf("-") + 1, propertieFile.indexOf("."));
                LOGGER.warn("Resource " + propertieFile + " not found for " + profile + " profile; " +
                        "is the input profile correct?");
            }
        }
        return properties;
    }
}