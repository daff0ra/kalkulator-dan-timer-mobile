package com.l0122039.daffa.ppab_11_l0122039_daffarafiaryaputra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class TimerFragment : Fragment() {

    private val timerViewModel: TimerViewModel by viewModels()

    private lateinit var etTimeInput: EditText
    private lateinit var tvTimer: TextView
    private lateinit var btnStart: Button
    private lateinit var btnPause: Button
    private lateinit var btnReset: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timer, container, false)
        etTimeInput = view.findViewById(R.id.et_time_input)
        tvTimer = view.findViewById(R.id.tv_timer)
        btnStart = view.findViewById(R.id.btn_start)
        btnPause = view.findViewById(R.id.btn_pause)
        btnReset = view.findViewById(R.id.btn_reset)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timerViewModel.timeLeft.observe(viewLifecycleOwner, Observer { timeLeft ->
            val minutes = (timeLeft / 60).toString().padStart(2, '0')
            val seconds = (timeLeft % 60).toString().padStart(2, '0')
            tvTimer.text = "$minutes:$seconds"
        })

        timerViewModel.timerRunning.observe(viewLifecycleOwner, Observer { isRunning ->
            btnStart.text = if (isRunning) "Pause" else "Start"
            btnPause.isEnabled = isRunning
            btnReset.isEnabled = !isRunning && timerViewModel.timeLeft.value!! > 0
        })

        btnStart.setOnClickListener {
            if (timerViewModel.timerRunning.value == true) {
                timerViewModel.pauseTimer()
            } else {
                val timeInput = etTimeInput.text.toString()
                if (timeInput.isNotEmpty()) {
                    val timeInSeconds = timeInput.toLong()
                    timerViewModel.setTime(timeInSeconds * 1000)
                    timerViewModel.startTimer()
                }
            }
        }

        btnPause.setOnClickListener {
            timerViewModel.pauseTimer()
        }

        btnReset.setOnClickListener {
            timerViewModel.resetTimer()
        }
    }
}
