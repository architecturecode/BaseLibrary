package com.base.baselibrary.ext.commom

import okhttp3.internal.and
import java.io.UnsupportedEncodingException
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/19
 * 描述　:
 */
//class MD5Ext {

    fun encodeStringToMD5(str: String?):String{
        str?.let {
            val hash: ByteArray = try {
                MessageDigest.getInstance("MD5").digest(it?.toByteArray(charset("UTF-8")))
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException("Huh, MD5 should be supported?", e)
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException("Huh, UTF-8 should be supported?", e)
            }
            val hex = StringBuilder(hash.size * 2)
            for (b in hash) {
                if (b and  0xFF < 0x10) hex.append("0")
                hex.append(Integer.toHexString(b and 0xFF))
            }
            return hex.toString()
        }
        return ""
    }
//}