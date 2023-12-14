package com.example.baskaryaapp.ui.batikpedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baskaryaapp.R
import com.example.baskaryaapp.data.api.ApiConfig.apiService
import com.example.baskaryaapp.data.api.ApiService
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.response.DataItem
import com.example.baskaryaapp.databinding.FragmentBatikpediaBinding
import com.example.baskaryaapp.ui.ViewModelFactory

class BatikpediaFragment : Fragment() {
    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: BatikRVAdapter
    lateinit var courseList: ArrayList<BatikRVModal>
    private lateinit var binding: FragmentBatikpediaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentBatikpediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val batikpediaFragmentViewModel = ViewModelProvider(requireActivity())[BatikpediaViewModel::class.java]
//        val batikpediaFragmentViewModel = ViewModelFactory(this)[BatikpediaViewModel::class.java]
        val repository = BatikRepository.getInstance(apiService)
        val factory = ViewModelFactory.getInstance(repository)
        val batikpediaFragmentViewModel = ViewModelProvider(this, factory)[BatikpediaViewModel::class.java]

        // on below line we are initializing
        // our views with their ids.
        courseRV = binding.idRVCourses

        // on below line we are initializing our list
        courseList = ArrayList()

        // on below line we are creating a variable
        // for our grid layout manager and specifying
        // column count as 2
        val layoutManager = GridLayoutManager(requireContext(), 3)

        courseRV.layoutManager = layoutManager

//        // on below line we are initializing our adapter
//        courseRVAdapter = BatikRVAdapter(courseList, requireContext())
//
//        // on below line we are setting
//        // adapter to our recycler view.
//        courseRV.adapter = courseRVAdapter
//
//        // on below line we are adding data to our list
//        courseList.add(BatikRVModal("Android Development", R.drawable.background_1))
//        courseList.add(BatikRVModal("C++ Development", R.drawable.background_2))
//        courseList.add(BatikRVModal("Java Development", R.drawable.ic_back))
//        courseList.add(BatikRVModal("Python Development", R.drawable.login_banner))
//        courseList.add(BatikRVModal("JavaScript Development", R.drawable.register_banner))
//
//        // on below line we are notifying adapter
//        // that data has been updated.
//        courseRVAdapter.notifyDataSetChanged()


        batikpediaFragmentViewModel.lisBatik.observe(requireActivity()) { listBatik ->
            setBatikData(listBatik)
        }

        batikpediaFragmentViewModel.isLoading.observe(requireActivity()) { loading ->
            showLoading(loading)
        }
    }

    private fun setBatikData(items: List<DataItem>) {
        val adapter = BatikRVAdapter()
        adapter.submitList(items)
        binding.idRVCourses.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}