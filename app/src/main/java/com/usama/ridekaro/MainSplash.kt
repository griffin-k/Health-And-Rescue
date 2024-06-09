package com.usama.ridekaro

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.froyo.usama.R

class MainSplash : AppCompatActivity() {
    private val splashTimeOut: Long = 7000 // 3 seconds
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash) // Set your splash screen layout

        mediaPlayer = MediaPlayer.create(this, R.raw.ambomp3)
        mediaPlayer.isLooping = true
        // Start audio playback
        mediaPlayer.start()


        Handler().postDelayed({
            // This code will be executed after the specified delay
            val intent = Intent(this, loginoption::class.java) // Replace MainActivity with your main activity
            startActivity(intent)
            finish() // Close the splash activity
        }, splashTimeOut)
    }


    override fun onResume() {
        super.onResume()
        // Resume playback if it was paused
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    override fun onPause() {
        super.onPause()
        // Pause playback when activity is paused
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release resources when activity is destroyed
        mediaPlayer.release()
    }
}
