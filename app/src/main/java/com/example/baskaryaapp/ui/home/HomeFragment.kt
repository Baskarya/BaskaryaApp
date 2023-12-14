package com.example.baskaryaapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.baskaryaapp.R
import com.example.baskaryaapp.databinding.FragmentHomeBinding
import com.example.baskaryaapp.ui.batikpedia.BatikpediaActivity

class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null
    lateinit var rvBatik : RecyclerView
  lateinit var vpSlider: ViewPager
  lateinit var notif : ImageView
  lateinit var morebp:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_home,container,false)
        vpSlider=view.findViewById(R.id.view_pager)
        notif=view.findViewById(R.id.iv_notif)


        notif.setOnClickListener{
            val intent=Intent(activity,BatikpediaActivity::class.java)
            startActivity(intent)
        }

        morebp =view.findViewById(R.id.tv_morebp)
        morebp.setOnClickListener{
            val intent=Intent(activity,BatikpediaActivity::class.java)
            startActivity(intent)
        }

        val arrSlider= ArrayList<Int>()
        arrSlider.add(R.drawable.baskarya_logo)
        arrSlider.add(R.drawable.login_banner)
        arrSlider.add(R.drawable.register_banner)

        var adapterSlider=AdapterSlider(arrSlider,activity)
        vpSlider.adapter=adapterSlider

        val lm =LinearLayoutManager(activity)
        lm.orientation =LinearLayoutManager.HORIZONTAL
        rvBatik=view.findViewById(R.id.idRVCourses)

        val adapterBatik = CoursesAdapter(ArrayBatik,activity)
        rvBatik.setHasFixedSize(true)
        rvBatik.layoutManager = lm
        rvBatik.adapter = adapterBatik


     return view
    }
    val ArrayBatik :ArrayList<BatikRvModel>get(){
        val courseList = ArrayList<BatikRvModel>()

        val courseList1 = BatikRvModel()
        courseList1.courseName = "Android Development"
        courseList1.courseImg =R.drawable.background_1

        val courseList2 = BatikRvModel()
        courseList2.courseName = "C++ Development"
        courseList2.courseImg =R.drawable.background_2

        val courseList3 = BatikRvModel()
        courseList3.courseName = "Java Development"
        courseList3.courseImg =R.drawable.ic_back

        val courseList4 = BatikRvModel()
        courseList4.courseName = "Python Development"
        courseList4.courseImg =R.drawable.login_banner

        val courseList5 = BatikRvModel()
        courseList5.courseName = "JavaScript Development"
        courseList5.courseImg =R.drawable.register_banner

        courseList.add(courseList1)
        courseList.add(courseList2)
        courseList.add(courseList3)
        courseList.add(courseList4)
        courseList.add(courseList5)


        return courseList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}