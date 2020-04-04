package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse

/**
 * Created by SegunFrancis
 */

@Database(entities = [BaseResponse::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class CryptoRoomDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile
        private var INSTANCE: CryptoRoomDatabase? = null

        fun getDatabase(context: Context): CryptoRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoRoomDatabase::class.java,
                    "crypto_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}