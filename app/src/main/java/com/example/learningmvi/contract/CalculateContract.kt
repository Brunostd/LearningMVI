package com.example.learningmvi.contract

data class CalculatorState(
    val total: Double = 0.0
)

sealed class CalculatorSideEffect {
    data class Toast(val text: String) : CalculatorSideEffect()
}