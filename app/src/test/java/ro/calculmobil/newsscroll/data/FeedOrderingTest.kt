package ro.calculmobil.newsscroll.data

import org.junit.Assert.assertEquals
import org.junit.Test

class FeedOrderingTest {

    private fun article(id: String, publishedAt: Long) = Article(
        id = id,
        title = "Titlu $id",
        description = "",
        content = "",
        imageUrl = null,
        source = "Test",
        publishedAt = publishedAt,
        url = "https://example.com/$id"
    )

    @Test
    fun newestFirst_ordersByDateDescending() {
        val articles = listOf(article("a", 100), article("b", 300), article("c", 200))

        val ordered = NewestFirst.order(articles)

        assertEquals(listOf("b", "c", "a"), ordered.map { it.id })
    }

    @Test
    fun newestFirst_sameDate_ordersById() {
        val articles = listOf(article("c", 100), article("a", 100), article("b", 100))

        val ordered = NewestFirst.order(articles)

        assertEquals(listOf("a", "b", "c"), ordered.map { it.id })
    }

    @Test
    fun newestFirst_emptyList_staysEmpty() {
        assertEquals(emptyList<Article>(), NewestFirst.order(emptyList()))
    }

    @Test
    fun newestFirst_fakeRepository_keepsAllArticlesInDateOrder() {
        val articles = FakeNewsRepository.getArticles()

        val ordered = NewestFirst.order(articles)

        assertEquals(articles.size, ordered.size)
        assertEquals(articles.map { it.id }.toSet(), ordered.map { it.id }.toSet())
        assertEquals(
            listOf("10", "8", "6", "9", "4", "2", "7", "3", "5", "1"),
            ordered.map { it.id }
        )
    }
}
