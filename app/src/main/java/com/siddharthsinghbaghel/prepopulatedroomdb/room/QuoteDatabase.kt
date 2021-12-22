package com.siddharthsinghbaghel.prepopulatedroomdb.room



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Quote

@Database(entities = [Quote::class], version = 5)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null
        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, QuoteDatabase::class.java, "quote_database")
                            .createFromAsset("database/quote.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}

