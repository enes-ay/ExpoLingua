package com.enesay.expolingua.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enesay.expolingua.data.VocabDao
import com.enesay.expolingua.data.local.VocabEntity

@Database(entities = [VocabEntity::class], version = 1, exportSchema = false)
abstract class VocabDatabase : RoomDatabase() {
    abstract fun vocabDao(): VocabDao

    companion object {
        @Volatile
        private var INSTANCE: VocabDatabase? = null

        fun getDatabase(context: Context): VocabDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VocabDatabase::class.java,
                    "vocab_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}