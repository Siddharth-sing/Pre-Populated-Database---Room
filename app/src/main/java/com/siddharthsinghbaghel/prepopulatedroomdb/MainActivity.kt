package com.siddharthsinghbaghel.prepopulatedroomdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: QuoteViewModel
    var quoteList = ArrayList<Quote>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[QuoteViewModel::class.java]

        viewModel.allQuote.observe(this){
            quoteList = it as ArrayList<Quote>
        }

        print("Author name : ${quoteList.size}")



    }
}