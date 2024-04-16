package com.example.learningmvi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.learningmvi.components.CustomButton
import com.example.learningmvi.components.CustomTextField
import com.example.learningmvi.contract.CalculatorSideEffect
import com.example.learningmvi.ui.theme.LearningMVITheme
import com.example.learningmvi.viewmodel.CalculatorViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<CalculatorViewModel>()
        setContent {
            LearningMVITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val state = viewModel.collectAsState()
    val context = LocalContext.current
    val (sum, onValueChangeSum) = remember {
        mutableStateOf("")
    }
    val (subtract, onValueChangeSubtract) = remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        CustomTextField(
            value = sum,
            onChangeValue = onValueChangeSum,
            keyboardType = KeyboardType.Decimal,
            titlePlaceholder = stringResource(id = R.string.sum)
        )
        CustomButton(title = stringResource(id = R.string.sum)) {
            if (sum.isNotEmpty()) viewModel.add(sum.toDouble())
        }
        CustomTextField(
            value = subtract,
            onChangeValue = onValueChangeSubtract,
            keyboardType = KeyboardType.Decimal,
            titlePlaceholder = stringResource(id = R.string.subtract)
        )
        CustomButton(title = stringResource(id = R.string.subtract)) {
            if (subtract.isNotEmpty()) viewModel.subtrair(subtract.toDouble())
        }
        Text(
            text = "Total ${state.value.total}!",
            modifier = modifier
        )
        viewModel.collectSideEffect { uiComponent ->
            when(uiComponent) {
                is CalculatorSideEffect.Toast -> {
                    Toast.makeText(context, uiComponent.text, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearningMVITheme {
        //Greeting("Android")
    }
}