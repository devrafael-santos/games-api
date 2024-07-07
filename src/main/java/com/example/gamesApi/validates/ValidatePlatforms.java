package com.example.gamesApi.validates;

import com.example.gamesApi.exceptions.InvalidPlatformException;

import java.util.List;
import java.util.Set;

public class ValidatePlatforms {

        public static final Set<String> PLATFORMS = Set.of(
            "PS5", "PS4", "XBOX-360", "XBOX ONE", "PC", "NINTENDO-SWITCH", "ANDROID", "IOS"
        );

        public static void validatePlatform(String platform){
            if (!PLATFORMS.contains(platform.toUpperCase())) {
                throw new InvalidPlatformException(platform);
            }
        }

        public ValidatePlatforms(List<String> genres){
            for(String genre : genres){
                validatePlatform(genre);
            }
        }

    }

