package com.siddharthsinghbaghel.prepopulatedroomdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(application : Application): AndroidViewModel(application) {

    private val mRepo: Repository
    val allQuote: LiveData<List<Quote>>
    init{
        val dao = QuoteDatabase.getDatabase(application).quoteDao()
        mRepo = Repository(dao)
        allQuote = mRepo.allQuote
    }
    fun insert(quote : Quote) = viewModelScope.launch(Dispatchers.IO) {
        mRepo.insert(quote)
    }

}