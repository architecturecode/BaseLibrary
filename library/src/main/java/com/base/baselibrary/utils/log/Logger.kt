package com.base.baselibrary.utils.log

import android.util.Log
import com.base.baselibrary.BuildConfig

/**
 *  作者　: wuhaitao
 *  时间　: 2021/11/19
 *  描述　: 打印工具类
 */
object Logger {

    final val DEFAULT_TAG: String = "BLLogger"

    fun e(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

    fun e(message: String) {
        e(DEFAULT_TAG, message)
    }

    fun e(message: Int) {
        e(DEFAULT_TAG, message.toString())
    }

    fun e(message: Boolean) {
        e(DEFAULT_TAG, message.toString())
    }

    fun e(message: Long) {
        e(DEFAULT_TAG, message.toString())
    }

    fun e(message: Double) {
        e(DEFAULT_TAG, message.toString())
    }

    fun e(message: Float) {
        e(DEFAULT_TAG, message.toString())
    }

    fun e(message: Char){
        e(DEFAULT_TAG, message.toString())
    }

    fun e(message: CharArray?){
        e(DEFAULT_TAG, String(message!!))
    }


    fun i(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message)
        }
    }

    fun i(message: String) {
        i(DEFAULT_TAG, message)
    }

    fun i(message: Int) {
        i(DEFAULT_TAG, message.toString())
    }

    fun i(message: Boolean) {
        i(DEFAULT_TAG, message.toString())
    }

    fun i(message: Long) {
        i(DEFAULT_TAG, message.toString())
    }

    fun i(message: Double) {
        i(DEFAULT_TAG, message.toString())
    }

    fun i(message: Float) {
        i(DEFAULT_TAG, message.toString())
    }

    fun i(message: Char){
        i(DEFAULT_TAG, message.toString())
    }

    fun i(message: CharArray?){
        i(DEFAULT_TAG, String(message!!))
    }


    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

    fun d(message: String) {
        d(DEFAULT_TAG, message)
    }
    fun d(message: Int) {
        d(DEFAULT_TAG, message.toString())
    }

    fun d(message: Boolean) {
        d(DEFAULT_TAG, message.toString())
    }

    fun d(message: Long) {
        d(DEFAULT_TAG, message.toString())
    }

    fun d(message: Double) {
        d(DEFAULT_TAG, message.toString())
    }

    fun d(message: Float) {
        d(DEFAULT_TAG, message.toString())
    }

    fun d(message: Char){
        d(DEFAULT_TAG, message.toString())
    }

    fun d(message: CharArray?){
        d(DEFAULT_TAG, String(message!!))
    }

    fun v(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, message)
        }
    }

    fun v(message: String) {
        v(DEFAULT_TAG, message)
    }

    fun v(message: Int) {
        v(DEFAULT_TAG, message.toString())
    }

    fun v(message: Boolean) {
        v(DEFAULT_TAG, message.toString())
    }

    fun v(message: Long) {
        v(DEFAULT_TAG, message.toString())
    }

    fun v(message: Double) {
        v(DEFAULT_TAG, message.toString())
    }

    fun v(message: Float) {
        v(DEFAULT_TAG, message.toString())
    }

    fun v(message: Char){
        v(DEFAULT_TAG, message.toString())
    }

    fun v(message: CharArray?){
        v(DEFAULT_TAG, String(message!!))
    }



}