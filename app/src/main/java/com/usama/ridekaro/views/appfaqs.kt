package com.usama.ridekaro.views


import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class appfaqs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.froyo.usama.R.layout.activity_appfaqs)

        val cardView1 = findViewById<CardView>(com.froyo.usama.R.id.card1)
        val cardView2 = findViewById<CardView>(com.froyo.usama.R.id.card2)
        val cardView3 = findViewById<CardView>(com.froyo.usama.R.id.card3)
        val cardView4 = findViewById<CardView>(com.froyo.usama.R.id.card4)
        val cardView5 = findViewById<CardView>(com.froyo.usama.R.id.card5)

        // Load the animation from XML

        // Load the animation from XML
        val slideInAnimation: Animation = AnimationUtils.loadAnimation(this, com.froyo.usama.R.anim.animation1)

        // Apply the animation to the CardView

        // Apply the animation to the CardView
        cardView1.startAnimation(slideInAnimation)
        cardView2.startAnimation(slideInAnimation)
        cardView3.startAnimation(slideInAnimation)
        cardView4.startAnimation(slideInAnimation)
        cardView5.startAnimation(slideInAnimation)

    }



}