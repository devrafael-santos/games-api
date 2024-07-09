package com.example.gamesApi.validates;

import com.example.gamesApi.exceptions.InvalidPlatformException;

import java.util.HashSet;
import java.util.Set;

public class ValidatePlatforms {

        public static final Set<String> PLATFORMS = Set.of(
            "PS5", "PS4", "XBOX-360", "XBOX ONE", "PC", "NINTENDO-SWITCH", "ANDROID", "IOS"
        );

        public static String validatePlatform(String platform){
            if (!PLATFORMS.contains(platform)) {
                throw new InvalidPlatformException(platform);
            }
            return platform.toUpperCase();
        }

        public static Set<String> validate (Set<String> platforms){
            Set<String> validatedPlatforms = new HashSet<>();
            for(String platform : platforms){
                validatedPlatforms.add(validatePlatform(platform.toUpperCase()));
            }
            return validatedPlatforms;
        }

    }

