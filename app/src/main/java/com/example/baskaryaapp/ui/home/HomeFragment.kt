package com.example.baskaryaapp.ui.home


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
import com.example.baskaryaapp.ui.article.ArticlesFragment
import com.example.baskaryaapp.ui.batikpedia.BatikpediaFragment
import com.example.baskaryaapp.ui.search.SearchResultFragment


class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null
    lateinit var rvBatik : RecyclerView
    lateinit var rvArticle :RecyclerView
  lateinit var vpSlider: ViewPager
  lateinit var notif : ImageView
  lateinit var morebp:TextView
  lateinit var morear:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_home,container,false)
        vpSlider=view.findViewById(R.id.view_pager)

        notif=view.findViewById(R.id.iv_notif)
        notif.setOnClickListener {
            val tesFragment = SearchResultFragment()
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.navhost, tesFragment)
            fragmentTransaction.commit()
        }
//        kalau ini kemungkinan nanti kita ganti logo aja

        morear =view.findViewById(R.id.tv_moreTa)
        morebp =view.findViewById(R.id.tv_morebp)

        morebp.setOnClickListener {
            val batikpediaFragment = BatikpediaFragment()
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.navhost, batikpediaFragment)
            fragmentTransaction.commit()
        }
        morear.setOnClickListener {
            val articleFragment = ArticlesFragment()
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.navhost, articleFragment)
            fragmentTransaction.commit()
        }
        val arrSlider= ArrayList<Int>()
        arrSlider.add(R.drawable.baskarya_logo)
        arrSlider.add(R.drawable.login_banner)
        arrSlider.add(R.drawable.register_banner)

        var adapterSlider=AdapterSlider(arrSlider,activity)
        vpSlider.adapter=adapterSlider

        //batik
        val lm =LinearLayoutManager(activity)
        lm.orientation =LinearLayoutManager.HORIZONTAL
        rvBatik=view.findViewById(R.id.idRVCourses)

        val adapterBatik = HomeAdapter(ArrayBatik,activity)
        rvBatik.setHasFixedSize(true)
        rvBatik.layoutManager = lm
        rvBatik.adapter = adapterBatik

        //article
        val lmarticle = LinearLayoutManager(activity)
        lmarticle.orientation = LinearLayoutManager.VERTICAL
        rvArticle = view.findViewById(R.id.RVArticle)

        val adapterArticle = AdapterArticle(ArrayArticle,activity)
        rvArticle.setHasFixedSize(true)
        rvArticle.layoutManager =lmarticle
        rvArticle.adapter=adapterArticle


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

    val ArrayArticle:ArrayList<ArticleModel>get(){
        val articleList =ArrayList<ArticleModel>()

        val articleList1 = ArticleModel()
        articleList1.articleName = "Android Development"
        articleList1.articleIsi = "Ini isi article"
        articleList1.articleImg =R.drawable.baskarya_logo

        val articleList2 = ArticleModel()
        articleList2.articleName = "Android Development"
        articleList2.articleIsi = "Ini isi article"
        articleList2.articleImg =R.drawable.baskarya_logo

        val articleList3 = ArticleModel()
        articleList3.articleName = "Android Development"
        articleList3.articleIsi = "Ini isi article"
        articleList3.articleImg =R.drawable.baskarya_logo


        articleList.add(articleList1)
        articleList.add(articleList2)
        articleList.add(articleList3)

        return articleList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}