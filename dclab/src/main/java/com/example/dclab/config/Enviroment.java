package com.example.dclab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Enviroment {
    @Value("${default.save.path}")
    private static String defaultSavePath;

    public static String getSavePath(String subPath) {
        return defaultSavePath + subPath;
    }
}
