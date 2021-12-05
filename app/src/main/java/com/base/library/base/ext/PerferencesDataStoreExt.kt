package com.base.library.base.ext

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import java.util.prefs.Preferences
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/20
 * 描述　:
 */

// At the top level of your kotlin file:
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
//val Context.dataStore: DataStore("f",)
//
//
//

val Context.myDataStore by dataStore("filename", serializer)

class SomeClass(val context: Context) {
    suspend fun update() = context.myDataStore.updateData {...}
}
class PerferenceDataStoreExt {


//    private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "datastore_name")
//    private val dataStore = appContent.create


//    suspend  fun <T> DataStore<Preferences>.g

//    suspend fun setKeyValue(value:String){
//        dataStore
//    }
}