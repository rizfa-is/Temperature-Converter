package com.issog.temperature_converter.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.issog.temperature_converter.R


@Composable
fun StatefulTemperatureInput(
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.stateful_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        GeneralTemperatureInput(
            scale = Scale.CELSIUS,
            input = input,
            onValueChanged = { newInput ->
                input = newInput
                output = convertTemperature(Scale.CELSIUS, newInput)
            }
        )
        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}

@Composable
fun ConverterApp(
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    Column(modifier) {
        StatelessTemperatureInput(
            input = input,
            output = output,
            onValueChanged = { newInput ->
                input = newInput
                output = convertTemperature(Scale.CELSIUS, newInput)
            },
            modifier
        )
    }
}

@Composable
fun TwoWaysConverter(
    modifier: Modifier = Modifier
) {
    var celsius by remember {
        mutableStateOf("")
    }
    var fahrenheit by remember {
        mutableStateOf("")
    }

    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.two_way_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        GeneralTemperatureInput(
            scale = Scale.CELSIUS,
            input = celsius,
            onValueChanged = { newTemp ->
                celsius = newTemp
                fahrenheit = convertTemperature(Scale.CELSIUS, newTemp)
            }
        )
        GeneralTemperatureInput(
            scale = Scale.FAHRENHEIT,
            input = fahrenheit,
            onValueChanged = { newTemp ->
                fahrenheit = newTemp
                celsius = convertTemperature(Scale.FAHRENHEIT, newTemp)
            }
        )
    }
}

@Composable
fun StatelessTemperatureInput(
    input: String,
    output: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.stateless_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        GeneralTemperatureInput(
            scale = Scale.CELSIUS,
            input = input,
            onValueChanged = onValueChanged
        )
        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}

@Composable
fun GeneralTemperatureInput(
    scale: Scale,
    input: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(
            value = input,
            label = { Text(text = stringResource(id = R.string.enter_temperature, scale.scaleName)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChanged
        )
    }
}

fun convertTemperature(scale: Scale, temperature: String): String {
    return temperature.toDoubleOrNull()?.let {
        when(scale) {
            Scale.CELSIUS -> {
                (it * 9 / 5) + 32
            }
            Scale.FAHRENHEIT -> {
                (it - 32) * 5 / 9
            }
        }
    }.toString()
}

enum class Scale(val scaleName: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit")
}
