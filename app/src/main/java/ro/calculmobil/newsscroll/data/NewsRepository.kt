package ro.calculmobil.newsscroll.data

interface NewsRepository {
    fun getArticles(): List<Article>

    fun getArticleById(id: String): Article?
}
