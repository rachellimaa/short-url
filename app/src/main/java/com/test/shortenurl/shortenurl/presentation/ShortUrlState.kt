package com.test.shortenurl.shortenurl.presentation

import com.test.shortenurl.shortenurl.data.model.LinksResponse

sealed class ShortUrlState {
    data class UrlShorted(val shortUrlModel: LinksResponse) : ShortUrlState()
    data class RecentlyShortenedUrlsFetched(
        val recentlyShortenedUrls: List<LinksResponse>
    ) : ShortUrlState()
    data class Failure(val message: String) : ShortUrlState()
    object Ready : ShortUrlState()
    object SavingShortenedUrl : ShortUrlState()
    object ShortenedUrlSaved : ShortUrlState()
}