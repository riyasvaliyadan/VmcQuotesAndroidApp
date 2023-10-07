package com.vmcplus.quotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vmcplus.quotes.data.Data.quotes
import com.vmcplus.quotes.sound.ClickSoundPlayer
import com.vmcplus.quotes.ui.theme.VmcQuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VmcQuotesTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier) {
    val context = LocalContext.current
    val clickSoundPlayer = remember { ClickSoundPlayer(context) }
    var quote by remember { mutableStateOf(quotes.random()) }
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = quote.toString(),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Medium, textAlign = TextAlign.Center),
                    modifier = Modifier
                        .padding(top = 140.dp, bottom = 30.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "by ${quote.author}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            NextButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 160.dp)
            ) {
                quote = quotes.random()
                clickSoundPlayer.play()
            }
        }
    }
}

@Composable
fun NextButton(modifier: Modifier, nextClicked: () -> Unit) {
    FilledIconButton(
        modifier = modifier.size(100.dp),
        onClick = nextClicked
    ) {
        Icon(
            modifier = Modifier
                .size(70.dp)
                .background(MaterialTheme.colorScheme.onBackground, shape = CircleShape),
            imageVector = Icons.Outlined.NavigateNext,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}