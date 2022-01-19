package com.test.shortenurl.shortenurl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.shortenurl.app.DaggerViewModelFactory
import com.test.shortenurl.shortenurl.data.IUrlRepository
import com.test.shortenurl.shortenurl.data.UrlRepositoryImpl
import com.test.shortenurl.shortenurl.domain.IUrlInteractor
import com.test.shortenurl.shortenurl.domain.UrlInteractorImpl
import com.test.shortenurl.shortenurl.presentation.ShortenUrlViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Qualifier

@Module(includes = [SchedulerModule::class])
abstract class UrlModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShortenUrlViewModel::class)
    abstract fun bindShortenUrlViewModel(vm: ShortenUrlViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun provideUrlRepository(repositoryImpl: UrlRepositoryImpl) : IUrlRepository

    @Binds
    abstract fun provideUrlInteractor(interactor: UrlInteractorImpl) : IUrlInteractor
}

@Module
class SchedulerModule{
    @Provides
    @IoScheduler
    fun provideIoScheduler() = Schedulers.io()

    @Provides
    @MainScheduler
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoScheduler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainScheduler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ContextApplication