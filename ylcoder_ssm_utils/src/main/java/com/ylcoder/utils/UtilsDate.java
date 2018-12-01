package com.ylcoder.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsDate {

    public static String dateToString(Date date,String p){
        SimpleDateFormat sdf=new SimpleDateFormat(p);
        String format = sdf.format(date);
        return format;
    }

    public static Date strignToDate(String date,String p) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(p);
        Date parse = sdf.parse(date);
        return parse;
    }

}
