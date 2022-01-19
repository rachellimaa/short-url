package com.test.shortenurl.shortenurl.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.shortenurl.shortenurl.data.model.LinksResponse
import com.test.shortenurl.shortenurl.data.model.Url
import com.test.shortenurl.shortenurl.di.IoScheduler
import com.test.shortenurl.shortenurl.di.MainScheduler
import com.test.shortenurl.shortenurl.domain.IUrlInteractor
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class ShortenUrlViewModel @Inject constructor(
    private val interactor: IUrlInteractor,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    val vmState: MutableLiveData<ShortUrlState> by lazy { MutableLiveData<ShortUrlState>() }

    private val disposable = CompositeDisposable()

    fun shortUrl(url: String) {
        val links = interactor.getShortenUrl(Url(url))
        disposable += links.observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .doOnSubscribe {
                vmState.value = ShortUrlState.SavingShortenedUrl
            }
            .subscribe(
                { response ->
                    val result = arrayListOf<LinksResponse>()
                    result.add(response)
                    vmState.value = ShortUrlState.UrlShorted(response)
                    vmState.value = ShortUrlState.RecentlyShortenedUrlsFetched(result)
                    vmState.value = ShortUrlState.Ready
                }, {
                    vmState.value = ShortUrlState.Failure(it.message.orEmpty())
                }
            )
    }

}