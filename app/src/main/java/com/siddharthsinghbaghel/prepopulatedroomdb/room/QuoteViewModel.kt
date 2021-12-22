package com.siddharthsinghbaghel.prepopulatedroomdb.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Quote
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(application : Application): AndroidViewModel(application) {

    private val mRepo: Repository
    val allQuote: LiveData<List<Quote>>
    val allRecords: LiveData<List<Record>>
    init{
        val dao = QuoteDatabase.getDatabase(application).quoteDao()
        mRepo = Repository(dao)
        allQuote = mRepo.allQuote
        allRecords = mRepo.allRecords
    }
    fun insert(quote : Quote) = viewModelScope.launch(Dispatchers.IO) {
        mRepo.insert(quote)
    }
    fun insertRecord(record: Record) = viewModelScope.launch(Dispatchers.IO){
        mRepo.insertRecords(record)
    }
    fun updateRecord(record: Record) = viewModelScope.launch(Dispatchers.IO){
        mRepo.updateRecord(record)
    }

}