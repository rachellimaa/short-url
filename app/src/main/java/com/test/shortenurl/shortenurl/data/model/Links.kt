package com.test.shortenurl.shortenurl.data.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Links(
    @Json(name = "self")
    val self: String,
    @Json(name = "short")
    val short: String
) : Serializable
