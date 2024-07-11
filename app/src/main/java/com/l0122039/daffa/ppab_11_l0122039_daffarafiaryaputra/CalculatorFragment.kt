package com.l0122039.daffa.ppab_11_l0122039_daffarafiaryaputra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.l0122039.daffa.ppab_11_l0122039_daffarafiaryaputra.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    private val viewModel: CalculatorViewModel by viewModels()
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            binding.tvResult.text = result
        })

        binding.btn0.setOnClickListener { appendToExpression("0") }
        binding.btn1.setOnClickListener { appendToExpression("1") }
        binding.btn2.setOnClickListener { appendToExpression("2") }
        binding.btn3.setOnClickListener { appendToExpression("3") }
        binding.btn4.setOnClickListener { appendToExpression("4") }
        binding.btn5.setOnClickListener { appendToExpression("5") }
        binding.btn6.setOnClickListener { appendToExpression("6") }
        binding.btn7.setOnClickListener { appendToExpression("7") }
        binding.btn8.setOnClickListener { appendToExpression("8") }
        binding.btn9.setOnClickListener { appendToExpression("9") }
        binding.btnDot.setOnClickListener { appendToExpression(".") }
        binding.btnAdd.setOnClickListener { appendToExpression("+") }
        binding.btnSubtract.setOnClickListener { appendToExpression("-") }
        binding.btnMultiply.setOnClickListener { appendToExpression("*") }
        binding.btnDivide.setOnClickListener { appendToExpression("/") }
        binding.btnEquals.setOnClickListener { calculateResult() }

        binding.btnClear.setOnClickListener { clearExpression() }
        binding.btnDelete.setOnClickListener { deleteLastCharacter() }
    }

    private fun appendToExpression(value: String) {
        val currentExpression = binding.tvResult.text.toString()
        if (currentExpression == "0") {
            viewModel.updateExpression(value)
        } else {
            viewModel.updateExpression(currentExpression + value)
        }
    }

    private fun calculateResult() {
        val expression = binding.tvResult.text.toString()
        viewModel.calculate(expression)
    }

    private fun clearExpression() {
        viewModel.updateExpression("0")
    }

    private fun deleteLastCharacter() {
        val currentExpression = binding.tvResult.text.toString()
        if (currentExpression.isNotEmpty() && currentExpression != "0") {
            val newExpression = currentExpression.dropLast(1)
            if (newExpression.isEmpty()) {
                viewModel.updateExpression("0")
            } else {
                viewModel.updateExpression(newExpression)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
