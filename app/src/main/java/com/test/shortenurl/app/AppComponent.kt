package com.test.shortenurl.app

import android.app.Application
import com.test.shortenurl.shortenurl.di.MainActivityModule
import com.test.shortenurl.shortenurl.di.UrlModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        MainActivityModule::class,
        NetworkModule::class,
        UrlModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: SampleApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
