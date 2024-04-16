package com.example.learningmvi.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onChangeValue: (String) -> Unit,
    keyboardType: KeyboardType,
    titlePlaceholder: String
) {
    TextField(
        value = value,
        onValueChange = onChangeValue,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        placeholder = {
            Text(text = titlePlaceholder)
        }
    )
}