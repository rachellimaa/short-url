package com.test.shortenurl.shortenurl.data.service

import com.test.shortenurl.shortenurl.data.model.LinksResponse
import com.test.shortenurl.shortenurl.data.model.Url
import io.reactivex.Single
import retrofit2.http.*

interface UrlService {

    @POST("/api/alias")
    fun getShortenUrl(@Body url : Url) : Single<LinksResponse>

}