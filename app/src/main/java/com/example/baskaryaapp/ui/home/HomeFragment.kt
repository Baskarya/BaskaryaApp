package com.example.baskaryaapp.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.api.ApiConfig
import com.example.baskaryaapp.data.api.ApiConfig.apiService
import com.example.baskaryaapp.data.repo.ArticlesRepository
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.response.ArticlesItem
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.databinding.FragmentHomeBinding
import com.example.baskaryaapp.ui.ArticlesViewModelFactory
import com.example.baskaryaapp.ui.BatikViewModelFactory
import com.example.baskaryaapp.ui.articles.ArticlesFragment
import com.example.baskaryaapp.ui.articles.ArticlesViewModel
import com.example.baskaryaapp.ui.batikpedia.BatikpediaFragment
import com.example.baskaryaapp.ui.batikpedia.BatikpediaViewModel
import com.example.baskaryaapp.ui.search.SearchResultFragment

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
      lateinit var vpSlider: ViewPager
    lateinit var morebp:TextView
    lateinit var morear: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler(Looper.getMainLooper())
        val delay = 3000L // Ubah waktu delay sesuai keinginan, misalnya 3000L untuk 3 detik
        vpSlider=view.findViewById(R.id.view_pager)
        val arrSlider= ArrayList<Int>()
        arrSlider.add(R.drawable.baskarya_logo)
        arrSlider.add(R.drawable.login_banner)
        arrSlider.add(R.drawable.register_banner)

        var adapterSlider=AdapterSlider(arrSlider,activity)
        vpSlider.adapter=adapterSlider

        val runnable = object : Runnable {
            override fun run() {
                val currentItem = vpSlider.currentItem
                val totalCount = adapterSlider.count

                if (currentItem < totalCount - 1) {
                    vpSlider.currentItem = currentItem + 1
                } else {
                    vpSlider.currentItem = 0
                }

                handler.postDelayed(this, delay)
            }
        }

        handler.postDelayed(runnable, delay)


        val repository = BatikRepository.getInstance(apiService)
        val factory = BatikViewModelFactory.getInstance(repository)
        val batikpediaViewModel = ViewModelProvider(this, factory)[BatikpediaViewModel::class.java]
        val articlerepository = ArticlesRepository.getInstance(ApiConfig.apiService)
        val articlefactory = ArticlesViewModelFactory.getInstance(articlerepository)
        val articlesViewModel = ViewModelProvider(this, articlefactory)[ArticlesViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val arlayoutManager = LinearLayoutManager(requireActivity())
        binding.RVArticle.layoutManager = arlayoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), arlayoutManager.orientation)
        binding.RVArticle.addItemDecoration(itemDecoration)

        articlesViewModel.listArticles.observe(requireActivity()) { listArticles ->
            setArticlesData(listArticles)
        }
        binding.idRVCourses.layoutManager = layoutManager

        batikpediaViewModel.listBatik.observe(requireActivity()) { listBatik ->
            setBatikData(listBatik)
        }

        batikpediaViewModel.isLoading.observe(requireActivity()) { loading ->
            showLoading(loading)
        }

        //SearchView
        val searchView =view.findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    // Buat instance dari SearchResultFragment
                    val searchResultFragment = SearchResultFragment()

                    // Lakukan transaksi fragment untuk pindah ke SearchResultFragment
                    val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.homefragment, searchResultFragment)
                    fragmentTransaction.addToBackStack(null) // Agar dapat kembali ke fragment sebelumnya
                    fragmentTransaction.commit()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchResultFragment = SearchResultFragment()

                if (!newText.isNullOrEmpty()) {
                    // Buat instance SearchResultFragment dengan membawa data pencarian
                    val bundle = Bundle()
                    bundle.putString("query", newText)
                    searchResultFragment.arguments = bundle
                }

                return true
            }
        })
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
    }

    private fun setBatikData(items: List<BatikItem>) {
        val adapter = HomeAdapter(3)
        adapter.submitList(items)
        binding.idRVCourses.adapter = adapter
    }

    private fun setArticlesData(items: List<ArticlesItem>) {
        val adapter = AdapterArticle(1)
        adapter.submitList(items)
        binding.RVArticle.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}