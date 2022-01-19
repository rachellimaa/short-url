package com.test.shortenurl.base

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

open class BaseMock {
    val moshiBuilder: Moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    inline fun <reified T> readFile(fileName: String): T? {
        val moshiAdapter = moshiBuilder.adapter(T::class.java)
        val json =
            javaClass.classLoader?.getResourceAsStream(fileName)?.bufferedReader(Charsets.UTF_8)
                ?.use { it.readText() } ?: ""
        return moshiAdapter.fromJson(json)
    }
}