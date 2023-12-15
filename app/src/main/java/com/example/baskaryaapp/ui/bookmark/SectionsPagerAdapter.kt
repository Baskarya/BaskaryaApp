package com.example.baskaryaapp.ui.bookmark

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.baskaryaapp.ui.article.ArticlesFragment
import com.example.baskaryaapp.ui.batikpedia.BatikpediaFragment
import com.example.baskaryaapp.ui.customization.CustomizationFragment
import com.example.baskaryaapp.ui.recomendation.RecomendationFragment
import com.example.baskaryaapp.ui.search.SearchArticleFragment
import com.example.baskaryaapp.ui.search.SearchBatikFragment
import com.example.baskaryaapp.ui.setting.SettingFragment

class SectionsPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = CustomizationFragment()
            1 -> fragment = SearchBatikFragment()
            2 -> fragment = RecomendationFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }
}