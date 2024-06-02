package com.example.proyectoapliacionesmoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pantalla3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla3)

        val buttonNavigate: Button = findViewById(R.id.btnLogin)
        buttonNavigate.setOnClickListener {
            val intent = Intent(this, niveles::class.java)
            startActivity(intent)
        }


    }
}