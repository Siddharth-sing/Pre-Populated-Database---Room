package com.siddharthsinghbaghel.prepopulatedroomdb

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class Repository(private val dao: QuoteDao) {

    val allQuote: LiveData<List<Quote>> = dao.getQuotes()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(quote: Quote){
        dao.insert(quote)
    }
}