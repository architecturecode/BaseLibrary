package com.base.baselibrary.ext

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import java.lang.Exception

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/19
 * 描述　:
 */
fun Fragment.nav(): NavController {
    return NavHostFragment.findNavController(this)
}

fun nav(view: View): NavController {
    return Navigation.findNavController(view)
}

var lastNavTime = 0L
/**
 * 防止快速点击导致的报错
 */
fun NavController.navigateAction(resId:Int,bundle: Bundle?=null,interval:Long=500){
    val currentTime = System.currentTimeMillis()
    if (currentTime>= lastNavTime+interval){
        lastNavTime= currentTime
        try {
            navigate(resId,bundle)
        }catch (e:Exception){

        }
    }


}