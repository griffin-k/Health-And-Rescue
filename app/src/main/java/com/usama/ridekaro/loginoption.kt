package com.usama.ridekaro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.froyo.usama.R
import com.usama.ridekaro.views.MainActivity

class loginoption : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginoption)

        val button1 = findViewById<View>(R.id.loginbtn)
        val button2 = findViewById<View>(R.id.signupbtn)

        button1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, driverlogin::class.java)
            startActivity(intent)
        }
    }
}