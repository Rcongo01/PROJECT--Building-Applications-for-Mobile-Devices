package com.example.proyectoapliacionesmoviles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Opcioones : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_opcioones)
        val logoImageView: ImageView = findViewById(R.id.imageViewlogo2)
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation)
        logoImageView.startAnimation(logoAnimation)

        val buttonNavigate: Button = findViewById(R.id.buttonlogin)
        buttonNavigate.setOnClickListener {
            val intent = Intent(this, pantalla3::class.java)
            startActivity(intent)
        }
    }
}