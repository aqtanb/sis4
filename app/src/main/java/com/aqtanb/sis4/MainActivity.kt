package com.aqtanb.sis4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aqtanb.sis4.adapter.ApodAdapter
import com.aqtanb.sis4.data.ApodItem
import com.aqtanb.sis4.data.ApodDatabase
import com.aqtanb.sis4.data.ApodRepository
import com.aqtanb.sis4.databinding.ActivityMainBinding
import com.aqtanb.sis4.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: ApodRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = ApodRepository(
            RetrofitClient.apiService,
            ApodDatabase.getInstance(applicationContext)
        )

        loadApodItems()

        binding.errorTextView.setOnClickListener {
            loadApodItems()
        }
    }

    private fun loadApodItems() {
        lifecycleScope.launch {
            showLoading()

            try {
                val result = withContext(Dispatchers.IO) {
                    Log.d("MainActivity", "Fetching APOD items from NASA API...")
                    repository.loadApods(
                        count = 10,
                        apiKey = BuildConfig.NASA_API_KEY
                    )
                }

                Log.d(
                    "MainActivity",
                    "Received ${result.items.size} APOD items (fromCache=${result.fromCache})"
                )
                showSuccess(result.items, result.fromCache)
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

    private fun showSuccess(items: List<ApodItem>, fromCache: Boolean) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.errorTextView.visibility = View.GONE

        val adapter = ApodAdapter(items)
        binding.recyclerView.adapter = adapter

        if (fromCache) {
            Toast.makeText(
                this,
                "Showing cached NASA items (offline)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showError() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
    }
}
