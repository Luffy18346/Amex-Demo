package com.luffy18346.amexdemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.luffy18346.amexdemo.ui.navigation.AppNavigation
import com.luffy18346.amexdemo.ui.theme.AmexDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmexDemoTheme {
                AppNavigation()
            }
        }
    }
}