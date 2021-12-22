package com.siddharthsinghbaghel.prepopulatedroomdb.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Quote
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Record

@Dao
interface QuoteDao {
/* Quote */
    @Insert
    suspend fun insert(quote: Quote)
    @Query("Select * From quote")
    fun getQuotes(): LiveData<List<Quote>>

/* Records */
    @Insert
    suspend fun insertRecords(record: Record)
    @Query("Select * From record")
    fun getRecords(): LiveData<List<Record>>
    @Update
    suspend fun updateRecord(record: Record)



}