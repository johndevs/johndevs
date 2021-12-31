package com.github.johndevs;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BadgeLinkProvider {

    private final Random random = new Random(1337L);
    private final Map<String,String> cache = new HashMap<>();

    @RequiredArgsConstructor
    public enum BadgeColor {
        ORANGE("23ED8B00"),
        GREEN("236DB33F"),
        BLACK("23323330"),
        BLUE("231572B6"),
        RED("23E34F26"),
        WHITE("FFFFFFFF");
        private final String code;
    }

    public String getLink(String colorName, String caption, String logo) {
        var color = BadgeColor.valueOf(colorName);
        return cache.computeIfAbsent(caption, (c) -> String.format("![%s](https://img.shields.io/badge/%s-%s" +
                        ".svg?style=for-the-badge&logo=%s&logoColor=%s)",
                caption,
                caption.replace(" ", "_").toLowerCase(),
                "%" + color.code,
                logo,
                "white"
        ));
    }

    public String getLink(String caption) {
        BadgeColor color = BadgeColor.values()[random.nextInt(BadgeColor.values().length-1)];
        return getLink(color.name(), caption,  caption.split(" ")[0].toLowerCase());
    }

    public String getLink(String caption, String logo) {
        BadgeColor color = BadgeColor.values()[random.nextInt(BadgeColor.values().length-1)];
        return getLink(color.name(), caption,  logo);
    }
}
