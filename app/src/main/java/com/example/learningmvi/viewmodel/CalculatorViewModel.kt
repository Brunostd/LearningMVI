package com.example.learningmvi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.learningmvi.contract.CalculatorSideEffect
import com.example.learningmvi.contract.CalculatorState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CalculatorViewModel: ContainerHost<CalculatorState, CalculatorSideEffect>, ViewModel() {

    // Include `orbit-viewmodel` for the factory function
    override val container = container<CalculatorState, CalculatorSideEffect>(CalculatorState())

    fun add(number: Double) = intent {
        postSideEffect(CalculatorSideEffect.Toast("Adicionando $number no total de ${state.total}!"))

        reduce {
            state.copy(total = state.total + number)
        }
    }

    fun subtrair(number: Double) = intent {
        if (state.total >= number) {
            postSideEffect(CalculatorSideEffect.Toast("Subtraindo $number no total de ${state.total}!"))

            reduce {
                state.copy(total = state.total - number)
            }
        }
    }
}