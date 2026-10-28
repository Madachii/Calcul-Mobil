package ro.calculmobil.newsscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ro.calculmobil.newsscroll.ui.feed.FeedScreen
import ro.calculmobil.newsscroll.ui.theme.NewsScrollTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsScrollTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FeedScreen(
                        onArticleClick = { /* navigarea spre detaliu vine in #14 */ },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
