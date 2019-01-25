package com.igi.kinoteka_api.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.parse(
                source, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
    }
}
