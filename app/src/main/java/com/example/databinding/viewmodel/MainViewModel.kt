package com.example.databinding.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding.adapter.DataBindingAdapter
import com.example.databinding.model.Owner
import com.example.databinding.model.RepositoryDTO
import com.example.databinding.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _repositoryDTO: MutableLiveData<RepositoryDTO> = MutableLiveData()
    val _repoDto: LiveData<RepositoryDTO> get() = _repositoryDTO
    private val adapter = DataBindingAdapter()

    fun provideAdapter(): DataBindingAdapter {
        return adapter
    }

    fun setAdapter(data: List<Owner>) {
        adapter.submitList(data)
    }

    fun callApi(query: String) {
        val call = RetroInstance.apiService.getAllRepository(query)
        call.enqueue(object : Callback<RepositoryDTO> {
            override fun onResponse(p0: Call<RepositoryDTO>, response: Response<RepositoryDTO>) {
                if (response.isSuccessful) {
                    _repositoryDTO.postValue(response.body())
                }
            }

            override fun onFailure(p0: Call<RepositoryDTO>, p1: Throwable) {
                Log.d("@@@", "onFailure: ${p1.message}")
            }
        })
    }
}