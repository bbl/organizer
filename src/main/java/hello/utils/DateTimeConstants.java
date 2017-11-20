package hello.utils;

import java.time.format.DateTimeFormatter;

public class DateTimeConstants {
    public static final String DD_MM_YYYY_HH_MM_SS = "dd.MM.yyyy HH:mm:ss";
    public static final DateTimeFormatter DD_MM_YYYY_HH_MM_SS_FORMATTER =
            DateTimeFormatter.ofPattern(DD_MM_YYYY_HH_MM_SS);

    public static final String DD_MM_YYYY = "dd.MM.yyyy";
    public static final DateTimeFormatter DD_MM_YYYY_FORMATTER =
            DateTimeFormatter.ofPattern(DD_MM_YYYY);
}
