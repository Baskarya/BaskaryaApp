package com.example.baskaryaapp.ui.customization

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.data.repo.CustomRepository
import com.example.baskaryaapp.data.response.BatikItem
import com.example.baskaryaapp.data.response.Data
import kotlinx.coroutines.launch

//class CustomizationViewModel(private val repository: CustomRepository) : ViewModel() {
//    private val _listCustom = MutableLiveData<List<Data>>()
//    val listBatik: LiveData<List<Data>> = _listCustom
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    init {
//        getCustom()
//    }
//
//    private fun getCustom() {
//        viewModelScope.launch {
//            try {
//                _isLoading.value = true
//                val response = repository.custom()
//                if (!response.error!!) {
//                    _listCustom.value = response.data
//                } else {
//                    Log.e("BatikViewModel", "Error load stories: ${response.status}")
//                }
////                _listBatik.value = response.data
//            } catch (e: Exception) {
//                Log.e("BatikViewModel", "Error load stories: ${e.message}")
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//}