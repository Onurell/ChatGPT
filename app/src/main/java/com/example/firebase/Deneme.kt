package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class Deneme : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_deneme)

            val chatgpt = findViewById<CardView>(R.id.chatgpt)

            chatgpt.setOnClickListener {
                startActivity(Intent(this@Deneme, Chatgpt::class.java))
            }
        }
    }
