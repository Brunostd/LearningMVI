package com.example.learningmvi.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    title: String,
    action: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .padding(12.dp),
        onClick = { action() }
    ) {
        Text(text = title)
    }
}