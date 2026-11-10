package ro.calculmobil.newsscroll.data

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String?,
    val source: String,
    val publishedAt: Long, // milisecunde de la 1 ian 1970 UTC
    val url: String
)
