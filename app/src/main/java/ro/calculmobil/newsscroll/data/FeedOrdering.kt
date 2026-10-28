package ro.calculmobil.newsscroll.data

// Stabileste ordinea in care apar stirile in feed. Deocamdata criteriul e fix (NewestFirst);
// feedul personalizat (#35) poate veni ca alta implementare, fara sa se schimbe ecranul.
fun interface FeedOrdering {
    fun order(articles: List<Article>): List<Article>
}

// Cele mai noi stiri primele. La aceeasi data se ordoneaza dupa id, ca ordinea sa fie mereu aceeasi.
object NewestFirst : FeedOrdering {
    override fun order(articles: List<Article>): List<Article> =
        articles.sortedWith(compareByDescending<Article> { it.publishedAt }.thenBy { it.id })
}
