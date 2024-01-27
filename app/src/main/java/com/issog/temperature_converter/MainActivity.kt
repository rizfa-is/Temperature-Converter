package com.issog.temperature_converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.issog.temperature_converter.ui.component.ConverterApp
import com.issog.temperature_converter.ui.component.StatefulTemperatureInput
import com.issog.temperature_converter.ui.component.TwoWaysConverter
import com.issog.temperature_converter.ui.theme.TemperatureConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        StatefulTemperatureInput()
                        ConverterApp()
                        TwoWaysConverter()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemperatureConverterTheme {
        Column {
            StatefulTemperatureInput()
            ConverterApp()
            TwoWaysConverter()
        }
    }
}