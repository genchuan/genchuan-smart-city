package cn.iocoder.yudao.module.vehiclepass.framework.jackson;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeParseUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static LocalDateTime parse(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        String value = text.trim();
        if (value.matches("^\\d{10,}$")) {
            try {
                long timestamp = Long.parseLong(value);
                Instant instant = value.length() == 10 ? Instant.ofEpochSecond(timestamp) : Instant.ofEpochMilli(timestamp);
                return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            } catch (NumberFormatException ignore) {
            }
        }
        try {
            return LocalDateTime.parse(value, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException ignore) {
            return LocalDateTime.parse(value, ISO_FORMATTER);
        }
    }

}
