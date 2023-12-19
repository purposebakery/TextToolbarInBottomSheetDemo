package com.purposebakery.texttoolbarinbottomsheetdemo

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.purposebakery.texttoolbarinbottomsheetdemo.ui.theme.TextToolbarInBottomSheetDemoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextToolbarInBottomSheetDemoTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp), color = MaterialTheme.colorScheme.background
                ) {
                    val showingBottomSheet = remember { mutableStateOf(false) }

                    Column(verticalArrangement = spacedBy(16.dp)) {

                        Text(text = "Check readme to see how to reproduce the issue.")
                        Button(onClick = {
                            val clip = ClipData.newPlainText("text", "Some Text text")
                            (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(clip)
                        }) {
                            Text(text = "Click me to add something to your clipboard")
                        }

                        DemoTextField(supportingText = "Long click will not show text toolbar")

                        Button(onClick = { showingBottomSheet.value = true }) {
                            Text(text = "Click me to show bottom sheet")
                        }
                    }

                    if (showingBottomSheet.value) {
                        androidx.compose.material3.ModalBottomSheet(onDismissRequest = { showingBottomSheet.value = false }) {
                            Box(modifier = Modifier.padding(24.dp)) {
                                DemoTextField(supportingText = "Long click will not show text toolbar")
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun DemoTextField(supportingText: String) {
    val text = remember { mutableStateOf("") }
    androidx.compose.material3.TextField(
        value = text.value,
        onValueChange = { text.value = it },
        supportingText = { Text(text = supportingText) }
    )
}