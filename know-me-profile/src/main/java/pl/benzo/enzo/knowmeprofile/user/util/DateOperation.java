package pl.benzo.enzo.knowmeprofile.user.util;

import java.time.LocalDateTime;

public class DateOperation {
    public static LocalDateTime addHoursToDate(LocalDateTime date,long hours){
        return date.plusHours(hours);
    }
}
