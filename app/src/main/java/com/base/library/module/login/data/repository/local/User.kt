package com.base.library.module.login.data.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 作者　: wuhaitao
 * 时间　: 2021/11/20
 * 描述　:
 */
@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true) val userId:Long,


){
}