package com.example.baskaryaapp.ui.recomendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baskaryaapp.data.response.SimilarImagesItem
import com.example.baskaryaapp.databinding.FragmentRecomendationBinding


class RecomendationFragment : Fragment() {
    private lateinit var binding: FragmentRecomendationBinding
    private lateinit var adapter: RecomentationAdapter // Ganti dengan nama adaptermu
    var uploadResponseData: List<SimilarImagesItem?>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentRecomendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView dan Adapter
        adapter = RecomentationAdapter()
        binding.idRVBatik.adapter = adapter
        binding.idRVBatik.layoutManager = GridLayoutManager(requireContext(),3)

        // Tampilkan data respons ke RecyclerView
        uploadResponseData?.let {
            updateRecyclerViewWithData(it)
        }
    }

    // Fungsi untuk mengupdate data RecyclerView dari luar
    fun updateRecyclerViewWithData(data: List<SimilarImagesItem?>?) {
        adapter.submitList(data)
    }
}
