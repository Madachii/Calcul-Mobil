package ro.calculmobil.newsscroll.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.calculmobil.newsscroll.data.Article
import ro.calculmobil.newsscroll.data.FakeNewsRepository
import ro.calculmobil.newsscroll.data.NewsRepository
import ro.calculmobil.newsscroll.ui.theme.NewsScrollTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val dateFormatter = DateTimeFormatter
    .ofPattern("d MMMM yyyy, HH:mm", Locale.forLanguageTag("ro"))
    .withZone(ZoneId.systemDefault())

@Composable
fun ArticleDetailScreen(
    articleId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    repository: NewsRepository = FakeNewsRepository
) {
    val article = remember(articleId) { repository.getArticleById(articleId) }

    if (article == null) {
        ArticleNotFound(onBack = onBack, modifier = modifier)
    } else {
        ArticleDetailContent(article = article, onBack = onBack, modifier = modifier)
    }
}

@Composable
private fun ArticleDetailContent(
    article: Article,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Înapoi")
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "${article.source} · ${dateFormatter.format(Instant.ofEpochMilli(article.publishedAt))}",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = article.description,
            style = MaterialTheme.typography.titleMedium
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { uriHandler.openUri(article.url) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Deschide sursa originală")
        }
    }
}

@Composable
private fun ArticleNotFound(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Articolul nu a fost găsit.",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Înapoi la știri")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleDetailScreenPreview() {
    NewsScrollTheme {
        ArticleDetailScreen(articleId = "1", onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleNotFoundPreview() {
    NewsScrollTheme {
        ArticleDetailScreen(articleId = "inexistent", onBack = {})
    }
}
