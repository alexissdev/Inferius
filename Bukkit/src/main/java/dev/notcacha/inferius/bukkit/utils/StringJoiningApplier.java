package dev.notcacha.inferius.bukkit.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringJoiningApplier {

    public static <T> String apply(String delimiter, T... objects) {
        return Arrays.stream(objects)
                .map(String::valueOf)
                .collect(Collectors.joining(delimiter));
    }

}
