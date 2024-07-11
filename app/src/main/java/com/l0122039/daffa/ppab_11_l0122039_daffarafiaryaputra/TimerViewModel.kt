package com.l0122039.daffa.ppab_11_l0122039_daffarafiaryaputra

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private val _timeLeft = MutableLiveData<Long>()
    val timeLeft: LiveData<Long> get() = _timeLeft

    private val _timerRunning = MutableLiveData<Boolean>()
    val timerRunning: LiveData<Boolean> get() = _timerRunning

    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0

    fun setTime(milliseconds: Long) {
        timeLeftInMillis = milliseconds
        _timeLeft.value = timeLeftInMillis / 1000
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                _timeLeft.value = timeLeftInMillis / 1000
            }

            override fun onFinish() {
                _timerRunning.value = false
            }
        }.start()

        _timerRunning.value = true
    }

    fun pauseTimer() {
        timer?.cancel()
        _timerRunning.value = false
    }

    fun resetTimer() {
        timer?.cancel()
        timeLeftInMillis = 0
        _timeLeft.value = 0
        _timerRunning.value = false
    }
}
