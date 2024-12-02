package com.example.khodamgo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultPersonalityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_personality)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val personalityType = intent.getStringExtra("personalityType")
        val explanation = intent.getStringExtra("explanation")
        val khodam = intent.getStringExtra("KHODAM_RESULT");

        // Tampilkan hasil ke layout
        findViewById<TextView>(R.id.personalityType).text = personalityType
        findViewById<TextView>(R.id.personalityExplanation).text = explanation

        // Tombol kembali ke beranda
        findViewById<Button>(R.id.backToHomeButton).setOnClickListener {
            val intent = Intent(this, HasilActivity::class.java)
            intent.putExtra("KHODAM_RESULT",khodam);
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}