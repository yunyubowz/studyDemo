package com.example.demo.simpleDemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 获取当前月的第一天和最后一天
 */
public class demo01 {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) {
        System.out.println(getMonthFirstDay());
        System.out.println(getMonthLastDay());
    }

    /**
     * 获取当前月的第一天
     */
    private static String getMonthFirstDay(){
        Calendar cal_1= Calendar.getInstance();
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }

    /**
     * 获取当前月的最后一天
     */
    private static String getMonthLastDay(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastday = format.format(ca.getTime());
        return lastday;
    }
}
