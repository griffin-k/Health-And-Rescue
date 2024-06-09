package com.usama.ridekaro.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.froyo.usama.R
import kotlinx.android.synthetic.main.activity_lets_celebrate.*

class LetsCelebrate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lets_celebrate)
        btnRideComplete.setOnClickListener {
            finish()
        }
    }
}