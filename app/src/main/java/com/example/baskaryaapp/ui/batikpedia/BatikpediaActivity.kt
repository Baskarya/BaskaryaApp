package com.example.baskaryaapp.ui.batikpedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baskaryaapp.R

class BatikpediaActivity : AppCompatActivity() {
    // on below line we are creating variables
    // for our swipe to refresh layout,
    // recycler view, adapter and list.
    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: BatikRVAdapter
    lateinit var courseList: ArrayList<BatikRVModal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batikpedia)

        // on below line we are initializing
        // our views with their ids.
        courseRV = findViewById(R.id.idRVCourses)

        // on below line we are initializing our list
        courseList = ArrayList()

        // on below line we are creating a variable
        // for our grid layout manager and specifying
        // column count as 2
        val layoutManager = GridLayoutManager(this, 3)

        courseRV.layoutManager = layoutManager

        // on below line we are initializing our adapter
        courseRVAdapter = BatikRVAdapter(courseList, this)

        // on below line we are setting
        // adapter to our recycler view.
        courseRV.adapter = courseRVAdapter

        // on below line we are adding data to our list
        courseList.add(BatikRVModal("Android Development", R.drawable.background_1))
        courseList.add(BatikRVModal("C++ Development", R.drawable.background_2))
        courseList.add(BatikRVModal("Java Development", R.drawable.ic_back))
        courseList.add(BatikRVModal("Python Development", R.drawable.login_banner))
        courseList.add(BatikRVModal("JavaScript Development", R.drawable.register_banner))

        // on below line we are notifying adapter
        // that data has been updated.
        courseRVAdapter.notifyDataSetChanged()
    }
}