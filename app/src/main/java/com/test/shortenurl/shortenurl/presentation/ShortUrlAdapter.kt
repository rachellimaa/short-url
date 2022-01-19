package com.test.shortenurl.shortenurl.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.shortenurl.R
import com.test.shortenurl.databinding.ShortenedUrlsItemBinding
import com.test.shortenurl.shortenurl.data.model.LinksResponse

class ShortUrlAdapter :
    ListAdapter<LinksResponse, ShortUrlAdapter.ShortenedUrlsItemViewHolder>(ShortenedUrlDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortenedUrlsItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shortened_urls_item, parent, false)
        return ShortenedUrlsItemViewHolder(ShortenedUrlsItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ShortenedUrlsItemViewHolder, position: Int) =
        holder.bind(getItem(position))


     class ShortenedUrlsItemViewHolder(
        private val binding: ShortenedUrlsItemBinding,
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(shortUrlModel: LinksResponse) {
            with(binding) {
                originUrlTextView.text = shortUrlModel.links.self
                ShortenedUrlTextView.text = shortUrlModel.links.short
            }
        }
    }

    class ShortenedUrlDiff : DiffUtil.ItemCallback<LinksResponse>() {
        override fun areContentsTheSame(oldItem: LinksResponse, newItem: LinksResponse): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: LinksResponse, newItem: LinksResponse): Boolean =
            oldItem == newItem
    }
}

