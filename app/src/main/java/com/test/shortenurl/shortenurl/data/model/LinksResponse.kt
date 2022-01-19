package com.test.shortenurl.shortenurl.data.model

import com.squareup.moshi.Json

data class LinksResponse(
    @Json(name = "alias")
    val id: String,
    @Json(name = "_links")
    val links: Links
)
