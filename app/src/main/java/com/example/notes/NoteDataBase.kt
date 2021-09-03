package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Note::class],version = 1)
abstract class NoteDataBase:RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
    companion object{

        @Volatile
        private var INSTANCE: NoteDataBase? = null
        fun getDatabase(context: Context): NoteDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }
}