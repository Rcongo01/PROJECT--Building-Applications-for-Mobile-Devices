package com.example.proyectoapliacionesmoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.ImageView




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val logoImageView: ImageView = findViewById(R.id.imageViewlogo)
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation)
        logoImageView.startAnimation(logoAnimation)

        val buttonNavigate: Button = findViewById(R.id.button4)
        buttonNavigate.setOnClickListener {
            val intent = Intent(this, Opcioones::class.java)
            startActivity(intent)
        }




    }
}