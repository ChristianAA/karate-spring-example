package com.example.karate.configuration;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfilesResolver;

public class ProfileResolver implements ActiveProfilesResolver {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileResolver.class);

    public final String[] resolve(Class<?> targetClass) {
        String springProfilesActive = System.getProperty("spring.profiles.active");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Value of [spring.profiles.active] property: [{}]", springProfilesActive);
        }

        String[] externalProfiles = StringUtils.split(springProfilesActive, ",");

        String[] activeProfiles = ArrayUtils.addAll(externalProfiles);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("List of collected active profiles: [{}]", (Object) activeProfiles);
        }

        return activeProfiles;
    }
}
