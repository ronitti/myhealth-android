package myhealth.ufscar.br.myhealth.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateTime {
    public static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy"){
        DateFormat init(){
            setTimeZone(TimeZone.getTimeZone("UTC-3"));
            return this;
        }
    }.init();
    public static final DateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat("HH:mm"){
        DateFormat init(){
            setTimeZone(TimeZone.getTimeZone("UTC-3"));
            return this;
        }
    }.init();
    public static final DateFormat SIMPLE_TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"){
        DateFormat init(){
            setTimeZone(TimeZone.getTimeZone("UTC-3"));
            return this;
        }
    }.init();
}
