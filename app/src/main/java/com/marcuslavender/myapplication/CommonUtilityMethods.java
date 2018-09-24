package com.marcuslavender.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marcus.lavender on 13/09/2018.
 */

 class CommonUtilityMethods {


    public static String getCurrentTime() {


        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh-mm-ss");
        String time = ft.format(dNow);
        return time;
    }


    public static String getCurrentDate()
    {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String date = ft.format(dNow);
        return date;
    }


}
