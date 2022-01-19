package com.test.shortenurl.shortenurl.domain

import com.test.shortenurl.shortenurl.data.IUrlRepository
import com.test.shortenurl.shortenurl.data.model.LinksResponse
import com.test.shortenurl.shortenurl.data.model.Url
import io.reactivex.Single
import javax.inject.Inject

interface IUrlInteractor{
    fun getShortenUrl(url : Url) : Single<LinksResponse>
}

class UrlInteractorImpl @Inject constructor(
    private val repository: IUrlRepository
) : IUrlInteractor {
    override fun getShortenUrl(url: Url) : Single<LinksResponse> = repository.getShortenUrl(url)
}