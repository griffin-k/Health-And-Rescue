package com.usama.ridekaro.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.froyo.usama.R
import kotlinx.android.synthetic.main.activity_temp.*

class Temp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        btnShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Please download Health & Rescue from this link www.google.com")
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Please select app: "))
        }
    }
}