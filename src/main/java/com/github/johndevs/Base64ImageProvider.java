package com.github.johndevs;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@UtilityClass
public class Base64ImageProvider {

    public String getImage(String name) {
        log.info("Converting image {} to base64...", name);
        try(var stream = Base64ImageProvider.class.getResourceAsStream(name)) {
            byte[] bytes = Objects.requireNonNull(stream).readAllBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            log.error("Failed to convert image {} to base64. Error was: {}", name, e.getMessage());
            return "";
        }
    }
}
