package com.usama.ridekaro

import android.content.Context
import android.content.SharedPreferences

object AppPreference {


    private const val NAME = "Health & Rescue"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val FIRST_TIME: Boolean = true

    private val LANGUAGE = "En"

}