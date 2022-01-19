package com.test.shortenurl.shortenurl.data

import com.test.shortenurl.shortenurl.data.model.LinksResponse
import com.test.shortenurl.shortenurl.data.model.Url
import com.test.shortenurl.shortenurl.data.service.UrlService
import io.reactivex.Single
import javax.inject.Inject

interface IUrlRepository{
    fun getShortenUrl(url : Url) : Single<LinksResponse>

}

class UrlRepositoryImpl @Inject constructor(
    private val service: UrlService,
) : IUrlRepository {

    override fun getShortenUrl(url: Url) : Single<LinksResponse> =
        service.getShortenUrl(url)
}