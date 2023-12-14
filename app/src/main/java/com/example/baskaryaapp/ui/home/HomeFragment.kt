package com.example.baskaryaapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.baskaryaapp.R
import com.example.baskaryaapp.ui.batikpedia.BatikpediaFragment

class HomeFragment : Fragment() {
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
//        notif=view.findViewById(R.id.iv_notif)
//        notif.setOnClickListener{
//            val intent=Intent(activity,BatikpediaFragment::class.java)
//            startActivity(intent)
//        }
//        kalau ini kemungkinan nanti kita ganti logo aja

        morebp =view.findViewById(R.id.tv_morebp)
//        ubah jadi activity bang
//        morebp.setOnClickListener{
//            val intent=Intent(activity,BatikpediaFragment::class.java)
//            startActivity(intent)
//        }
        morebp.setOnClickListener {
            val batikpediaFragment = BatikpediaFragment()
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            // Replace the current fragment with BatikpediaFragment
            fragmentTransaction.replace(R.id.navhost, batikpediaFragment)
            // You can add the fragment to the back stack if needed
            // fragmentTransaction.addToBackStack("BatikpediaFragment")
            fragmentTransaction.commit()
        }

        val arrSlider= ArrayList<Int>()
        arrSlider.add(R.drawable.baskarya_logo)
        arrSlider.add(R.drawable.login_banner)
        arrSlider.add(R.drawable.register_banner)

        var adapterSlider=AdapterSlider(arrSlider,activity)
        vpSlider.adapter=adapterSlider
        return view

    }
}