package com.example.iq_flow_one_zero.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Flashcard::class])
abstract class AppDatabase: RoomDatabase() {


    abstract fun flashcardDao(): FlashcardDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE?:synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "FlashcardDatabase"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}