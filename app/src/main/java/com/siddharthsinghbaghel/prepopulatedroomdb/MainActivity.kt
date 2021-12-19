package com.siddharthsinghbaghel.prepopulatedroomdb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

     private lateinit var viewModel: QuoteViewModel
     private var quoteList = ArrayList<Quote>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[QuoteViewModel::class.java]

       viewModel.allQuote.observe(this){
             quoteList.addAll(it)
          Toast.makeText(this,"${quoteList.size}",LENGTH_SHORT).show()
          Log.d("TAG 1", "Quote 1 -> ${quoteList[0].id}\n${quoteList[0].text}\n${quoteList[0].author}\n\n")
          Log.d("TAG 2", "Quote 2 -> ${quoteList[1].id}\n${quoteList[1].text}\n${quoteList[1].author}\n\n")
        }





    }
}