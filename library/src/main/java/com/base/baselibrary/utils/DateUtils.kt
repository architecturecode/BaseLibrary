package com.base.baselibrary.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 作者　: wuhaitao
 * 时间　: 2021/12/10
 * 描述　: 日期工具类
 */
object DateUtils {


    private const val PATTERN = "yyyy-MM-dd"

    /**
     * 当前月有几天
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
            days = if (year % 4 == 0 && year % 100 == 0 || year % 400 == 0) 29 else 28
        }
        return days
    }

    /**
     * 判断当前日期是星期几
     */
    private fun getWeekNoFormat(dateStr: String?): Int {
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(PATTERN)
        val c = Calendar.getInstance()
        try {
            c.time = simpleDateFormat.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return c[Calendar.DAY_OF_WEEK]
    }


    /**
     * 判断当前日期是星期几
     */
    fun getWeekByFormat(dateStr: String?): String {
        var week = ""
        when (getWeekNoFormat(dateStr)) {
            0 -> week += "周日"
            1 -> week += "周一"
            2 -> week += "周二"
            3 -> week += "周三"
            4 -> week += "周四"
            5 -> week += "周五"
            6 -> week += "周六"
            else -> { // Note the block
                print("。。。。。")
            }
        }
        return week
    }

    /**
     *
     */
    @SuppressLint("SimpleDateFormat")
    fun getDateStr(distance: Int, pattern: String?): String {
        var simpleDateFormat: SimpleDateFormat? = null
        simpleDateFormat = if (pattern.isNullOrEmpty()) {
            SimpleDateFormat(PATTERN)
        } else {
            SimpleDateFormat(pattern)
        }
        val currentDate = Date()
        var calendar: Calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DATE, distance)
        var tempDate: Date? = null
        try {
            tempDate = simpleDateFormat.parse(simpleDateFormat.format(calendar.time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return simpleDateFormat.format(tempDate)
    }


    fun getDateStr(distance: Int): String {
        return getDateStr(distance, null)
    }

    fun getDateStr(pattern: String?): String {
        return getDateStr(0, pattern)
    }

    fun getCurrentDateStr(): String {
        return getCurrentDateStr("")
    }

    fun getCurrentDateStr(pattern: String?): String {
        return getDateStr(pattern)
    }


    @SuppressLint("SimpleDateFormat")
    fun getCalendar(dateStr: String?, pattern: String?): Calendar? {
        val calendar: Calendar = Calendar.getInstance()

        if (dateStr.isNullOrEmpty()) {
            calendar.time = Date()
            return calendar
        }

        val sdf = if (pattern.isNullOrEmpty()) {
            SimpleDateFormat(PATTERN)
        } else {
            SimpleDateFormat(pattern)
        }
        try {
            calendar.time = sdf.parse(dateStr)
            return calendar
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    fun getCalendar(dateStr: String?): Calendar? {
        return getCalendar(dateStr, null)
    }

    fun getCalendar(): Calendar? {
        return getCalendar(null)
    }

    /**
     * 获取上几个月的总天数
     */
    fun getMonthsDays(months: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        if (months <= 1)
            return getCurrentMonthDays(calendar)
        var days = 0
        for (i in 0 until months) {
            calendar.add(Calendar.MONTH, -1)
            days += getCurrentMonthDays(calendar)
        }
        return days
    }



}
