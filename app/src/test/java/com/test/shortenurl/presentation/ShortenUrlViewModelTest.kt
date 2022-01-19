package com.test.shortenurl.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.test.shortenurl.data.UrlDatasourceTest
import com.test.shortenurl.shortenurl.domain.IUrlInteractor
import com.test.shortenurl.shortenurl.domain.UrlInteractorImpl
import com.test.shortenurl.shortenurl.presentation.ShortUrlState
import com.test.shortenurl.shortenurl.presentation.ShortenUrlViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShortenUrlViewModelTest {
    private lateinit var interactor: IUrlInteractor

    private val repository: UrlDatasourceTest by lazy { UrlDatasourceTest() }

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var vm: ShortenUrlViewModel

    private val mainScheduler: Scheduler = Schedulers.trampoline()
    private val ioScheduler: Scheduler = Schedulers.trampoline()

    @MockK
    private lateinit var stateVm: Observer<ShortUrlState>

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = UrlInteractorImpl(
            repository
        )

        vm = ShortenUrlViewModel(
            interactor,
            ioScheduler,
            mainScheduler
        )

        vm.vmState.observeForever(stateVm)
    }

    @Test
    fun `get shorten url`() {
        vm.shortUrl("http://www.google.com.br/")

        verify { stateVm.onChanged(ShortUrlState.SavingShortenedUrl) }
    }
}