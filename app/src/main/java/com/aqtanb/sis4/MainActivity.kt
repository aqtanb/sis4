package com.aqtanb.sis4

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aqtanb.sis4.adapter.ApodAdapter
import com.aqtanb.sis4.data.ApodItem
import com.aqtanb.sis4.databinding.ActivityMainBinding
import com.aqtanb.sis4.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadApodItems()

        binding.errorTextView.setOnClickListener {
            loadApodItems()
        }
    }

    private fun loadApodItems() {
        lifecycleScope.launch {
            showLoading()

            try {
                val items = withContext(Dispatchers.IO) {
                    Log.d("MainActivity", "Fetching APOD items from NASA API...")
                    RetrofitClient.apiService.getApodItems(
                        count = 10,
                        apiKey = BuildConfig.NASA_API_KEY
                    )
                }

                Log.d("MainActivity", "Received ${items.size} APOD items")
                showSuccess(items)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error loading APOD items", e)
                showError()
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.errorTextView.visibility = View.GONE
    }

    private fun showSuccess(items: List<ApodItem>) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.errorTextView.visibility = View.GONE

        val adapter = ApodAdapter(items)
        binding.recyclerView.adapter = adapter
    }

    private fun showError() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
    }
}