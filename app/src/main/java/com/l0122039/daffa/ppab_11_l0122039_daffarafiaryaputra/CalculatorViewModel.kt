package com.l0122039.daffa.ppab_11_l0122039_daffarafiaryaputra

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder


class CalculatorViewModel : ViewModel() {
    private val _result = MutableLiveData<String>().apply { value = "0" }
    val result: LiveData<String> get() = _result

    fun calculate(expression: String) {
        try {
            val result = ExpressionBuilder(expression).build().evaluate()
            _result.value = result.toString()
        } catch (e: Exception) {
            _result.value = "Error"
        }
    }

    fun updateExpression(expression: String) {
        _result.value = expression
    }
}
