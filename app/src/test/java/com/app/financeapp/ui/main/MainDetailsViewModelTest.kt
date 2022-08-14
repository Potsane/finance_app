package com.app.financeapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.financeapp.BaseTestRule
import com.app.financeapp.datasource.StockDataProvider
import com.app.financeapp.network.model.ArticleSource
import com.app.financeapp.network.model.NewsArticle
import com.app.financeapp.repository.DetailsRepository
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainDetailsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = BaseTestRule()

    @Mock
    private lateinit var repository: DetailsRepository

    @Mock
    private lateinit var stockDataSource: StockDataProvider

    private val viewModel by lazy { MainDetailsViewModel(repository, stockDataSource) }

    @Test(expected = Exception::class)
    fun `should not set stocks on failure`() = testCoroutineRule.runBlockingTest {
        mockNewsData()
        mockStocksInfoReader()
        viewModel.onResume(mock())
        assertNull(viewModel.stocks.value)
    }

    @Test(expected = Exception::class)
    fun `should get correct number of top news articles`() = testCoroutineRule.runBlockingTest {
        mockNewsData()
        mockStocksInfoReader()
        viewModel.onResume(mock())
        assertEquals(6, viewModel.topArticles.value)
    }

    @Test(expected = Exception::class)
    fun `should get correct number of generic news`() = testCoroutineRule.runBlockingTest {
        mockNewsData()
        mockStocksInfoReader()
        viewModel.onResume(mock())
        assertEquals(2, viewModel.articles.value)
    }

    @Test(expected = Exception::class)
    fun `should call repo to get articles`() = testCoroutineRule.runBlockingTest {
        mockNewsData()
        mockStocksInfoReader()
        viewModel.onResume(mock())
        verify(repository).getNewsArticles()
    }

    @Test(expected = Exception::class)
    fun `should call repo to get stocks info`() = testCoroutineRule.runBlockingTest {
        mockNewsData()
        mockStocksInfoReader()
        viewModel.onResume(mock())
        verify(repository).getStockInfo()
    }

    @Test(expected = Exception::class)
    fun `should get correct articles item`() = testCoroutineRule.runBlockingTest {
        mockNewsData()
        mockStocksInfoReader()
        viewModel.onResume(mock())
        val article = viewModel.articles.value?.first()

        assertEquals("author", article?.author)
        assertEquals("title", article?.title)
        assertEquals("desc", article?.description)
        assertEquals("url", article?.url)
        assertEquals("image", article?.urlToImage)
        assertEquals("date", article?.publishedAt)
        assertEquals("contents", article?.content)
    }

    private suspend fun mockNewsData() {
        val news = listOf(
            NewsArticle(
                ArticleSource("id", "name"),
                "author",
                "title",
                "desc",
                "url",
                "image",
                "date",
                "contents"
            ),
            NewsArticle(
                ArticleSource("id", "name"),
                "author",
                "title",
                "desc",
                "url",
                "image",
                "date",
                "contents"
            ),
            NewsArticle(
                ArticleSource("id", "name"),
                "author",
                "title",
                "desc",
                "url",
                "image",
                "date",
                "contents"
            ),
            NewsArticle(
                ArticleSource("id", "name"),
                "author",
                "title",
                "desc",
                "url",
                "image",
                "date",
                "contents"
            ),
            NewsArticle(
                ArticleSource("id", "name"),
                "author",
                "title",
                "desc",
                "url",
                "image",
                "date",
                "contents"
            ),
            NewsArticle(
                ArticleSource("id", "name"),
                "author",
                "title",
                "desc",
                "url",
                "image",
                "date",
                "contents"
            ),
            NewsArticle(
                ArticleSource("id", "name"),
                "author",
                "title",
                "desc",
                "url",
                "image",
                "date",
                "contents"
            ),
            NewsArticle(
                ArticleSource("id", "name"),
                "author",
                "title",
                "desc",
                "url",
                "image",
                "date",
                "contents"
            ),
        )
        Mockito.doReturn(Response.success(news)).`when`(repository).getNewsArticles()
    }

    private suspend fun mockStocksInfoReader() {
        doThrow(Exception()).`when`(repository).getStockInfo()
    }
}