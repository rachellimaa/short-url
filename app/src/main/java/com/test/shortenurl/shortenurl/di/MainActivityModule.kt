package com.test.shortenurl.shortenurl.di

import com.test.shortenurl.ShortenUrlActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity() : ShortenUrlActivity
}