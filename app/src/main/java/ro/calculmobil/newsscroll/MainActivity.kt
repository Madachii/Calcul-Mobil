package ro.calculmobil.newsscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ro.calculmobil.newsscroll.ui.detail.ArticleDetailScreen
import ro.calculmobil.newsscroll.ui.feed.FeedScreen
import ro.calculmobil.newsscroll.ui.theme.NewsScrollTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsScrollTheme {
                // Lista (cu preview-ul de la #14 peste ea) -> articolul complet (#22)
                var fullArticleId by rememberSaveable { mutableStateOf<String?>(null) }
                // Pastreaza starea listei (pozitia de scroll) cat timp e deschis articolul complet
                val stateHolder = rememberSaveableStateHolder()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val currentFullId = fullArticleId

                    if (currentFullId != null) {
                        BackHandler { fullArticleId = null }
                        ArticleDetailScreen(
                            articleId = currentFullId,
                            onBack = { fullArticleId = null },
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        stateHolder.SaveableStateProvider("feed") {
                            FeedScreen(
                                onReadFullArticle = { fullArticleId = it },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}
