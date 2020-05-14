package gg.bayes.challenge.service.strategy;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public interface EventStrategy {

    void execute(String eventLine, Long matchId);

    default Long getTimestampInMillis(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        LocalTime time = LocalTime.parse(timestamp, formatter);
        int hour = time.getHour();
        int minute = time.getMinute();
        int secs = time.getSecond();
        double nano = time.getNano()/Math.pow(10,6);
        return (hour*60*60*1000 + minute*60*1000 + secs*1000 + (long) nano);
    }
}
