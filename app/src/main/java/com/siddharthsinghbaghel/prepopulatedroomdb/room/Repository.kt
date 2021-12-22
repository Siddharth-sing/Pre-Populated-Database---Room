package com.siddharthsinghbaghel.prepopulatedroomdb.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.siddharthsinghbaghel.prepopulatedroomdb.room.QuoteDao
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Quote
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Record


class Repository(private val dao: QuoteDao) {

    val allQuote: LiveData<List<Quote>> = dao.getQuotes()
    val allRecords: LiveData<List<Record>> = dao.getRecords()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(quote: Quote){
        dao.insert(quote)
    }
    suspend fun insertRecords(record: Record){
        dao.insertRecords(record)
    }
    suspend fun updateRecord(record: Record){
        dao.updateRecord(record)
    }
}