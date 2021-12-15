package com.base.baselibrary.utils.log

import android.util.Log

/**
 *  Created by wuhaitao on 2021/11/18
 */
class LogUtils {

    private var logEnable: Boolean = true

    companion object {
        private var instance: LogUtils? = null
            get() {
                if (field == null)
                    field = LogUtils()

                return field
            }

        fun get(): LogUtils {
            return instance!!
        }
    }


    fun init(logEnable: Boolean) {
        this.logEnable = logEnable
    }

    private final val TAG = "tag"


    /**
     * 分段打印出较长log文本
     * @param logContent  打印文本
     * @param showLength  规定每段显示的长度（AndroidStudio控制台打印log的最大信息量大小为4k）
     * @param tag         打印log的标记
     */

    fun showLongLog(logContent: String) {
        showLongLog(logContent, 0, null)
    }

    fun showLongLog(logContent: String, tag: String?) {
        showLongLog(logContent, 0, tag)
    }

    private fun showLongLog(logContent: String, showLength: Int, tag: String?) {
        val length:Int = if (showLength <= 0 || showLength >= 4000) {
            3076
        }else
            showLength

        val logTag: String = if (tag.isNullOrEmpty()) {
            TAG
        } else
            tag


        if (logEnable) {
            if (logContent.length > length) {
                val show = logContent.substring(0, length)
                Log.e(logTag, show)
                /*剩余的字符串如果大于规定显示的长度，截取剩余字符串进行递归，否则打印结果*/
                if (logContent.length - length > length) {
                    val partLog = logContent.substring(length, logContent.length)
                    showLongLog(partLog, length, logTag)
                } else {
                    val printLog = logContent.substring(length, logContent.length)
                    Log.e(logTag, printLog)
                }
            } else {
                Log.e(logTag, logContent)
            }
        }
    }
}