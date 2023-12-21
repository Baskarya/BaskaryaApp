package com.example.baskaryaapp.ui.detailBatik

//class DetailBatikViewModel(private val bookmarkBatikRepository: BookmarkBatikRepository) : ViewModel() {
//    private val _listBatik = MutableLiveData<List<BatikItem>>()
//    val listBatik: LiveData<List<BatikItem>> = _listBatik
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    fun getBatik(title: String?) {
//        viewModelScope.launch {
//            try {
//                _isLoading.value = true
//                val response = bookmarkBatikRepository.batik()
//                if (!response.error!!) {
//                    _listBatik.value = response.data
//                } else {
//                    Log.e("BatikViewModel", "Error load stories: ${response.status}")
//                }
//            } catch (e: Exception) {
//                Log.e("BatikViewModel", "Error load stories: ${e.message}")
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
//}