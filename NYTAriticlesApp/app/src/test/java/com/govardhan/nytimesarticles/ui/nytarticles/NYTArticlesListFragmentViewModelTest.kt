package com.govardhan.nytimesarticles.ui.nytarticles

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.govardhan.nytimesarticles.actions.nytarticles.NYTArticlesnteractor
import com.govardhan.nytimesarticles.data.remote.pojo.MostviewedArticlesResponse
import com.govardhan.nytimesarticles.data.remote.pojo.NYTArticleItem

import io.reactivex.Maybe
import org.junit.*

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class NYTArticlesListFragmentViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }


    @Mock
    lateinit var nytArticlesnteractor: NYTArticlesnteractor

    lateinit var nytArticlesListFragmentViewModel: NYTArticlesListFragmentViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.nytArticlesListFragmentViewModel = NYTArticlesListFragmentViewModel(this.nytArticlesnteractor)
    }

    @Test
    fun getMostViewd_positiveResponse() {
        Mockito.`when`(this.nytArticlesnteractor.getMostViewedArticles(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<MostviewedArticlesResponse>())
        }



    }




}