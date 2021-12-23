package com.siddharthsinghbaghel.prepopulatedroomdb

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.siddharthsinghbaghel.prepopulatedroomdb.room.QuoteViewModel
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Quote
import com.siddharthsinghbaghel.prepopulatedroomdb.room.entities.Record
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

     private lateinit var viewModel: QuoteViewModel
     private var quoteList = ArrayList<Quote>()
     private var i:Int = -1
     private var red: Boolean = false
     private var green: Boolean = false
     private var done: Boolean = false
     private var undone: Boolean = true
     private var bookmarked: Boolean = false


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
               checkRecord(quoteList[i].id)
           }else{
               Toast.makeText(this,"End",LENGTH_SHORT).show()
           }
       }

        btnP.setOnClickListener{
            if(i>0)
            {
                i--
                txt.text = quoteList[i].text
                checkRecord(quoteList[i].id)

            }else{
                Toast.makeText(this,"End",LENGTH_SHORT).show()
            }
        }



        imgBook.setOnClickListener{
            bookmark()
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

    private fun checkRecord(id: Int) {

        val recordList = ArrayList<Record>()
        viewModel.allRecords.observe(this){
            recordList.addAll(it)
        }

        val currentIndex = searchList(id,recordList)

        if(currentIndex!=-1){

        }else{
            doneQuoteReading(quoteList[i].id)
        }

    }

    private fun searchList(id: Int, recordList: ArrayList<Record>): Int {
        var j = 0
        val size = recordList.size
        var res: Int = -1
        while(j<size)
        {
            if(recordList[j].id == id)
            {
                res = j
                break
            }else{
                j++;
            }
        }
        return res
    }

    private fun bookmark() {
           if(!red){
                imgBook.setImageResource(R.drawable.ic_baseline_bookmark_diff)
                red = true
            }else{
                imgBook.setImageResource(R.drawable.ic_baseline_bookmark_24)
                red = false
            }
    }

    private fun doneQuoteReading(id: Int) {

       val recordId: Int = id
       val newRecord = Record(id, done = true, undone = false, bookmark = false)

        viewModel.insertRecord(newRecord)

    }
}



