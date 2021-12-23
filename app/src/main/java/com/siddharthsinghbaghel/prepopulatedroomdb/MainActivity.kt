package com.siddharthsinghbaghel.prepopulatedroomdb

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.siddharthsinghbaghel.prepopulatedroomdb.room.QuoteViewModel
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Quote
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

     private lateinit var viewModel: QuoteViewModel
     private var quoteList = ArrayList<Quote>()
     private var i:Int = -1
     private var red: Boolean = false
     private var green: Boolean = false


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

       btnN.setOnClickListener{

           if(i<quoteList.size-1)
           {
               i++
               txt.text = quoteList[i].text
               doneQuoteReading(quoteList[i].id)

           }else{
               Toast.makeText(this,"End",LENGTH_SHORT).show()
           }
       }
        btnP.setOnClickListener{
            if(i>0)
            {
                i--
                txt.text = quoteList[i].text
            }else{
                Toast.makeText(this,"End",LENGTH_SHORT).show()
            }
        }

        imgBook.setOnClickListener{
 
            if(!red){
                imgBook.setImageResource(R.drawable.ic_baseline_bookmark_diff)
                red = true
            }else{
                imgBook.setImageResource(R.drawable.ic_baseline_bookmark_24)
                red = false
            }
        }

        imgDone.setOnClickListener{
            if(!green){
                imgDone.setImageResource(R.drawable.ic_baseline_done_all_24)
                green = true
            }else{
                imgDone.setImageResource(R.drawable.ic_baseline_done_all_un)
                green = false
            }
        }


    }

   private fun doneQuoteReading(id: Int) {
        TODO("Not yet implemented")
    }
}