package ro.calculmobil.newsscroll.ui.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ro.calculmobil.newsscroll.data.Article
import ro.calculmobil.newsscroll.data.FakeNewsRepository
import ro.calculmobil.newsscroll.ui.theme.NewsScrollTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val dateFormatter = DateTimeFormatter
    .ofPattern("d MMMM yyyy, HH:mm", Locale.forLanguageTag("ro"))
    .withZone(ZoneId.systemDefault())

// Cate randuri din descriere apar in preview, textul intreg e in ecranul de detaliu (#22)
private const val DESCRIPTION_MAX_LINES = 4

// Panoul care urca de jos peste lista cand se apasa pe o stire (#14).
// Se inchide tragand in jos, apasand in afara lui sau cu Back.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlePreviewSheet(
    article: Article,
    onReadFull: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        ArticlePreviewContent(article = article, onReadFull = onReadFull)
    }
}

@Composable
private fun ArticlePreviewContent(
    article: Article,
    onReadFull: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
    ) {
        if (article.imageUrl != null) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.height(16.dp))
        }

        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
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
            style = MaterialTheme.typography.bodyLarge,
            maxLines = DESCRIPTION_MAX_LINES,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onReadFull,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Citește articolul complet")
        }
    }
}

// Previzualizam doar continutul: ModalBottomSheet se deschide ca fereastra separata si nu apare bine in Preview
@Preview(showBackground = true)
@Composable
fun ArticlePreviewContentPreview() {
    NewsScrollTheme {
        ArticlePreviewContent(
            article = FakeNewsRepository.getArticles().first(),
            onReadFull = {}
        )
    }
}
