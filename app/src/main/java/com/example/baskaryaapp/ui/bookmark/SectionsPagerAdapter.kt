package com.example.baskaryaapp.ui.bookmark

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.baskaryaapp.ui.article.ArticleFragment
import com.example.baskaryaapp.ui.batikpedia.BatikpediaFragment
import com.example.baskaryaapp.ui.setting.SettingFragment

class SectionsPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = BatikpediaFragment()
            1 -> fragment = ArticleFragment()
            2 -> fragment = SettingFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }
}