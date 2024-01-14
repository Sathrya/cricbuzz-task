package com.example.sneakerapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakerapp.data.Sneakeritem
import com.example.sneakerapp.domain.GetSneakersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SneakerListViewModel (private val getListUseCase: GetSneakersUseCase) : ViewModel() {

    val sneakerList = MutableLiveData<List<Sneakeritem>>()

    fun getAllSneakers() {
        viewModelScope.launch(Dispatchers.IO){
            val response = getListUseCase.execute()
            sneakerList.postValue(response.sneakers)
        }
    }

}