package com.siddharthsinghbaghel.prepopulatedroomdb

import android.os.Bundle
import android.util.Log
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
     private var recordList = ArrayList<Record>()
     private var i:Int = -1
     private var red: Boolean = false
     private var green: Boolean = false
     private var mId: Int = -1



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


        imgDone.setOnClickListener {

            if(green)
            {
                greenTick(0)
                val cBook = recordList[mId].bookmark
                val uRecord = Record(mId,done = false, undone = true, bookmark = cBook)
                viewModel.updateRecord(uRecord)
                printTable()
            }else{
                greenTick(1)
                val cBook = recordList[mId].bookmark
                val uRecord = Record(mId,done = true, undone = false, bookmark = cBook)
                viewModel.updateRecord(uRecord)
                printTable()
            }

        }
        imgBook.setOnClickListener{
            if(red){
                bookmark(0)
                val cDone = recordList[mId].done
                val cUndone = recordList[mId].undone
                val uRecord = Record(mId,done = cDone, undone = cUndone, bookmark = false)
                viewModel.updateRecord(uRecord)
                printTable()
            }else{
                bookmark(1)
                val cDone = recordList[mId].done
                val cUndone = recordList[mId].undone
                val uRecord = Record(mId,done = cDone, undone = cUndone, bookmark = true)
                viewModel.updateRecord(uRecord)
                printTable()

            }
        }


        btnN.setOnClickListener{

           if(i<quoteList.size-1)
           {
               i++
               txt.text = quoteList[i].text
               mId = quoteList[i].id
               checkRecord(mId)
           }else{
               Toast.makeText(this,"End",LENGTH_SHORT).show()
           }
       }

        btnP.setOnClickListener{
            if(i>0)
            {
                i--
                txt.text = quoteList[i].text
                mId = quoteList[i].id
                checkRecord(mId)

            }else{
                Toast.makeText(this,"End",LENGTH_SHORT).show()
            }
        }

    }

    private fun checkRecord(id: Int) {

        recordList = ArrayList<Record>()
        viewModel.allRecords.observe(this){
            recordList.addAll(it)
        }

        val currentIndex = searchList(id,recordList)

        if(currentIndex!=-1){
            extractRecordAndSet(currentIndex,recordList)
            printTable()
        }else{
            doneQuoteReading(quoteList[i].id)
            printTable()
        }

    }

    private fun extractRecordAndSet(currentIndex: Int, recordList: ArrayList<Record>) {
        if(recordList[currentIndex].bookmark)
        {
            bookmark(1)
        } else{bookmark(0)}

        if(recordList[currentIndex].done)
        {
            greenTick(1)
        }
        if(recordList[currentIndex].undone)
        {
            greenTick(0)
        }
    }

    private fun greenTick(i: Int) {
        if(i==1){
            imgDone.setImageResource(R.drawable.ic_baseline_done_all_24)
            green = true
        }else{
            imgDone.setImageResource(R.drawable.ic_baseline_done_all_un)
            green = false
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

    private fun bookmark(i:Int) {
           if(i==1){
                imgBook.setImageResource(R.drawable.ic_baseline_bookmark_diff)
                red = true
            }else{
                imgBook.setImageResource(R.drawable.ic_baseline_bookmark_24)
                red = false
            }
    }

    private fun doneQuoteReading(id: Int) {

       val newRecord = Record(id, done = true, undone = false, bookmark = false)
        viewModel.insertRecord(newRecord)
        Toast.makeText(this,"Now Greening and No Bookmarking",LENGTH_SHORT).show()
        greenTick(1)
        bookmark(0)

    }

    fun printTable(){
        val s = recordList.size
        var p:Int = 0
        while(p<s)
        {
            Log.d("TAG","\nid - ${recordList[p].id},\ndone - ${recordList[p].done},\nbookmark - ${recordList[p].bookmark}\n\n")
            p++
        }
    }
}



/*
     imgDone.setOnClickListener{
                if(!green){
                    imgDone.setImageResource(R.drawable.ic_baseline_done_all_24)
                    green = true
                }else{
                    imgDone.setImageResource(R.drawable.ic_baseline_done_all_un)
                    green = false
                }
            }
* */