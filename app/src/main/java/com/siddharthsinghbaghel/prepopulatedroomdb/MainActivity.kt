package com.siddharthsinghbaghel.prepopulatedroomdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import com.siddharthsinghbaghel.prepopulatedroomdb.room.QuoteViewModel
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Quote
import kotlinx.android.synthetic.main.activity_main.*
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat


class MainActivity : AppCompatActivity() {

     private lateinit var viewModel: QuoteViewModel
     private var quoteList = ArrayList<Quote>()
     private var i:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[QuoteViewModel::class.java]

       viewModel.allQuote.observe(this){
             quoteList.addAll(it)
        }

       btn.setOnClickListener{

           if(i<quoteList.size)
           {
               txt.text = quoteList[i].text
           }
           i++

       }

        imgBook.setOnClickListener{
 
            imgBook.setImageResource(R.drawable.ic_baseline_bookmark_diff)
        }







    }
}