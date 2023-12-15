package com.example.baskaryaapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baskaryaapp.data.repo.BatikRepository
import com.example.baskaryaapp.ui.batikpedia.BatikpediaViewModel

//class ViewModelFactory private constructor(private val batikRepository: BatikRepository) :
//    ViewModelProvider.NewInstanceFactory() {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BatikpediaViewModel::class.java)) {
//            return BatikpediaViewModel(batikRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//    }
//
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//        fun getInstance(context: Context): ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(Injection.provideRepository(context))
//            }.also { instance = it }
//    }
//}

class BatikViewModelFactory private constructor(private val batikRepository: BatikRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: BatikViewModelFactory? = null

        @JvmStatic
        fun getInstance(batikRepository: BatikRepository): BatikViewModelFactory {
            if (INSTANCE == null) {
                synchronized(BatikViewModelFactory::class.java) {
                    INSTANCE = BatikViewModelFactory(batikRepository)
                }
            }
            return INSTANCE as BatikViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BatikpediaViewModel::class.java)) {
            return BatikpediaViewModel(batikRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}