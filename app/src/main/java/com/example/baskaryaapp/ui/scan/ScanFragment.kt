package com.example.baskaryaapp.ui.scan

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.baskaryaapp.R
import com.example.baskaryaapp.databinding.FragmentScanBinding
import com.example.baskaryaapp.ui.recomendation.RecomendationFragment
import com.example.baskaryaapp.utils.getImageUri

class ScanFragment : Fragment(R.layout.fragment_scan) {

    private lateinit var binding: FragmentScanBinding
    private var currentImageUri: Uri? = null
    private val REQUEST_IMAGE_CAPTURE = 100

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScanBinding.bind(view)
        binding.imageView2.setOnClickListener{back()}
        binding.btnGallery.setOnClickListener { startGallery() }
        binding.btnCamera.setOnClickListener { startCamera() }
        binding.buttonAdd.setOnClickListener { uploadImage() }
    }

    override fun onResume() {
        super.onResume()
        binding.buttonAdd.visibility = View.VISIBLE
        binding.cardView.visibility = View.VISIBLE
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun uploadImage(){
        val recommendationFragment = RecomendationFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.navhost, recommendationFragment)
            .addToBackStack(null)
            .commit()

        binding.buttonAdd.visibility = View.GONE
        binding.cardView.visibility= View.GONE
    }
    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivItemImage.setImageURI(it)
        }
    }
    private fun back(){
        requireActivity().onBackPressed()
    }
}