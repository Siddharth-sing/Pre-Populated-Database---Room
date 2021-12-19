package com.siddharthsinghbaghel.prepopulatedroomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {
    @Insert
    suspend fun insert(quote: Quote)

    @Query("Select * From quote")
    fun getQuotes(): LiveData<List<Quote>>
}