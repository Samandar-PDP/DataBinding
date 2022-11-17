package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.adapter.DataBindingAdapter
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.model.Employee
import com.example.databinding.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initViews()

    }

    private fun initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.viewModel, makeApiCall())
    }

    private fun makeApiCall(): MainViewModel {
        viewModel._repoDto.observe(this) {
            if (it != null) {
                binding.prb.isVisible = false
                viewModel.setAdapter(it.items.map { owner -> owner.owner })
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.callApi("android")
        return viewModel
    }
}