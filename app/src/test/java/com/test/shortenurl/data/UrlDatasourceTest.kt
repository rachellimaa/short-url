package com.test.shortenurl.data

import com.test.shortenurl.base.BaseMock
import com.test.shortenurl.shortenurl.data.IUrlRepository
import com.test.shortenurl.shortenurl.data.model.LinksResponse
import com.test.shortenurl.shortenurl.data.model.Url
import io.reactivex.Single

open class UrlDatasourceTest : BaseMock(), IUrlRepository {
    val links : LinksResponse? by lazy {
        readFile<LinksResponse>("short_url_response.json")
    }

    override fun getShortenUrl(url: Url): Single<LinksResponse> =
        links?.let {
            Single.just(it)
        } ?: Single.error(
            Exception()
        )
}