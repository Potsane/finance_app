package com.app.financeapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.financeapp.BaseTestRule
import com.app.financeapp.network.FinanceAppApiService
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailsRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = BaseTestRule()

    @Mock
    private lateinit var apiService: FinanceAppApiService

    private val repository by lazy { DetailsRepository(apiService) }

    @Test
    fun `should make api call to fetch news articles`() = testCoroutineRule.runBlockingTest {
        repository.getNewsArticles()
        verify(apiService).getNewsArticles()
    }
}