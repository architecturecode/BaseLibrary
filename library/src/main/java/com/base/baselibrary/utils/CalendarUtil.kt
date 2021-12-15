package com.base.baselibrary.utils

import android.annotation.SuppressLint
import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by atuan on 2018/9/1.
 */
object CalendarUtil {
    private const val p = ""
    fun getTwoDay(sj1: String?, sj2: String?): String {
        val myFormatter = SimpleDateFormat("yyyy-MM-dd")
        var day: Long = 0
        day = try {
            val date = myFormatter.parse(sj1)
            val mydate = myFormatter.parse(sj2)
            (date.time - mydate.time) / (24 * 60 * 60 * 1000)
        } catch (e: Exception) {
            return ""
        }
        return day.toString() + ""
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 设置的需要判断的时间  //格式如2012-09-08
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    fun getWeekNoFormat(pTime: String?): Int {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val c = Calendar.getInstance()
        try {
            c.time = format.parse(pTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return c[Calendar.DAY_OF_WEEK]
    }

    fun toDate(pTime: String?): Calendar? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val c = Calendar.getInstance()
        try {
            c.time = format.parse(pTime)
            return c
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 参数格式：2012-12-1
     * return 12月1日
     *
     * @param date
     */
    fun FormatDateMD(date: String): String {
        if (TextUtils.isEmpty(date)) {
            Throwable()
        }
        val month = date.split("-".toRegex()).toTypedArray()[1]
        val day = date.split("-".toRegex()).toTypedArray()[2]
        return month + "月" + day + "日"
    }

    /**
     * 参数格式：2012-12-1
     * return 2012年12月1日
     *
     * @param date
     */
    fun FormatDateYMD(date: String): String {
        if (TextUtils.isEmpty(date)) {
            Throwable()
        }
        val year = date.split("-".toRegex()).toTypedArray()[0]
        val month = date.split("-".toRegex()).toTypedArray()[1]
        val day = date.split("-".toRegex()).toTypedArray()[2]
        return year + "年" + month + "月" + day + "日"
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 设置的需要判断的时间  //格式如2012-09-08
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    @SuppressLint("SimpleDateFormat")
    fun getWeekByFormat(pTime: String?): String {
        var week = ""
        val format = SimpleDateFormat("yyyy-MM-dd")
        val c = Calendar.getInstance()
        try {
            c.time = format.parse(pTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (c[Calendar.DAY_OF_WEEK] == 1) {
            week += "日"
        }
        if (c[Calendar.DAY_OF_WEEK] == 2) {
            week += "一"
        }
        if (c[Calendar.DAY_OF_WEEK] == 3) {
            week += "二"
        }
        if (c[Calendar.DAY_OF_WEEK] == 4) {
            week += "三"
        }
        if (c[Calendar.DAY_OF_WEEK] == 5) {
            week += "四"
        }
        if (c[Calendar.DAY_OF_WEEK] == 6) {
            week += "五"
        }
        if (c[Calendar.DAY_OF_WEEK] == 7) {
            week += "六"
        }
        return week
    }

    /**
     * 获取当前月有几天
     * @param calendar
     * @return
     */
    fun getCurrentMonthDays(calendar: Calendar): Int {
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1
        var days = 0
        if (month != 2) {
            when (month) {
                1, 3, 5, 7, 8, 10, 12 -> days = 31
                4, 6, 9, 11 -> days = 30
            }
        } else {
            // 闰年
            days = if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 29 else 28
        }
        //        System.out.println("当月有" + days + "天！");
        return days
    }
}