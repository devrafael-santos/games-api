package com.example.gamesApi.validates;

import com.example.gamesApi.exceptions.InvalidPlatformException;

import java.util.Set;

public class ValidatePlatforms {

        public static final Set<String> PLATFORMS = Set.of(
            "PS5", "PS4", "XBOX-360", "XBOX ONE", "PC", "NINTENDO-SWITCH", "ANDROID", "IOS"
        );

        public static void validatePlatform(String platform){
            if (!PLATFORMS.contains(platform)) {
                throw new InvalidPlatformException(platform);
            }
        }

        public ValidatePlatforms(Set<String> platforms){
            for(String platform : platforms){
                validatePlatform(platform.toUpperCase());
            }
        }

    }

