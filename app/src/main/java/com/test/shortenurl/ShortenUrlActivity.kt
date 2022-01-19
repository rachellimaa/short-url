package com.test.shortenurl

import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.shortenurl.app.extensions.hideKeyboard
import com.test.shortenurl.databinding.ActivityShortenUrlBinding
import com.test.shortenurl.shortenurl.data.model.LinksResponse
import com.test.shortenurl.shortenurl.presentation.ShortUrlAdapter
import com.test.shortenurl.shortenurl.presentation.ShortUrlState
import com.test.shortenurl.shortenurl.presentation.ShortenUrlViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ShortenUrlActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val binding: ActivityShortenUrlBinding by lazy {
        ActivityShortenUrlBinding.inflate(layoutInflater)
    }

    private val viewModel: ShortenUrlViewModel by viewModels {
        viewModelFactory
    }

    private val shortUrlAdapter: ShortUrlAdapter by lazy { ShortUrlAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        observeStateChanges()
        setupListeners()
    }

    private fun setupViews() {
        setContentView(binding.root)
        with(binding.shortenedUrlsRecyclerView) {
            layoutManager = LinearLayoutManager(this@ShortenUrlActivity)
            adapter = shortUrlAdapter
        }
    }

    private fun observeStateChanges() {
        viewModel.vmState.observe(this) { uiState ->
            when (uiState) {
                is ShortUrlState.SavingShortenedUrl -> showProgress()
                is ShortUrlState.Failure -> showSnackbar(uiState.message)
                is ShortUrlState.ShortenedUrlSaved -> clearInput()
                is ShortUrlState.RecentlyShortenedUrlsFetched ->
                    showRecentlyShortenedUrlsOnUI(uiState.recentlyShortenedUrls)
                else -> hideProgress()
            }
        }
    }

    private fun setupListeners() {
        binding.shortenUrlButton.setOnClickListener {
            hideKeyboard()
            with(binding.UrlEditText) {
                val urlToShort = text
                when {
                    urlToShort.isNotEmpty() and Patterns.WEB_URL.matcher(urlToShort).matches() ->
                        viewModel.shortUrl(urlToShort.toString())
                    else -> error = getString(R.string.wrong_url_format)
                }
            }
        }
    }

    private fun showRecentlyShortenedUrlsOnUI(recentlyShortenedUrls: List<LinksResponse>) =
        shortUrlAdapter.submitList(recentlyShortenedUrls) {
            binding.shortenedUrlsRecyclerView.scrollToPosition(0)
        }

    private fun clearInput() {
        binding.UrlEditText.setText("")
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}