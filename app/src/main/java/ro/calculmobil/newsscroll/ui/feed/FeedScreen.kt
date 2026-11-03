package ro.calculmobil.newsscroll.ui.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ro.calculmobil.newsscroll.data.Article
import ro.calculmobil.newsscroll.data.FakeNewsRepository
import ro.calculmobil.newsscroll.data.FeedOrdering
import ro.calculmobil.newsscroll.data.NewestFirst
import ro.calculmobil.newsscroll.data.NewsRepository
import ro.calculmobil.newsscroll.ui.preview.ArticlePreviewSheet
import ro.calculmobil.newsscroll.ui.theme.NewsScrollTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val dateFormatter = DateTimeFormatter
    .ofPattern("d MMMM yyyy, HH:mm", Locale.forLanguageTag("ro"))
    .withZone(ZoneId.systemDefault())

@Composable
fun FeedScreen(
    onReadFullArticle: (String) -> Unit,
    modifier: Modifier = Modifier,
    repository: NewsRepository = FakeNewsRepository,
    ordering: FeedOrdering = NewestFirst
) {
    val articles = remember(repository, ordering) { ordering.order(repository.getArticles()) }

    // Stirea apasata, afisata in preview peste lista (#14). null = niciun preview deschis.
    var previewArticleId by rememberSaveable { mutableStateOf<String?>(null) }
    val previewArticle = articles.firstOrNull { it.id == previewArticleId }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(articles, key = { it.id }) { article ->
            ArticleCard(article = article, onClick = { previewArticleId = article.id })
        }
    }

    if (previewArticle != null) {
        ArticlePreviewSheet(
            article = previewArticle,
            onReadFull = {
                previewArticleId = null
                onReadFullArticle(previewArticle.id)
            },
            onDismiss = { previewArticleId = null }
        )
    }
}

@Composable
private fun ArticleCard(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick, modifier = modifier.fillMaxWidth()) {
        if (article.imageUrl != null) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
        }

        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "${article.source} · ${dateFormatter.format(Instant.ofEpochMilli(article.publishedAt))}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = article.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    NewsScrollTheme {
        FeedScreen(onReadFullArticle = {})
    }
}
