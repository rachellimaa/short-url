package com.test.shortenurl.shortenurl.data.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Url (
    @Json(name = "url")
    val url: String
) : Serializable