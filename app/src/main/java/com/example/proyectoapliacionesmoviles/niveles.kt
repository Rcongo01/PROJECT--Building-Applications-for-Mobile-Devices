package com.example.proyectoapliacionesmoviles

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class niveles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_niveles)

        val logoImageView: ImageView = findViewById(R.id.imageViewlogo)
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation)
        logoImageView.startAnimation(logoAnimation)

        val buttonLevelFacil: Button = findViewById(R.id.btnLevelfacil)
        buttonLevelFacil.setOnClickListener {
            val intent = Intent(this, nivelfacil::class.java)
            startActivity(intent)
        }

        // Conexión para el botón btnLevelmedio
        val buttonLevelMedio: Button = findViewById(R.id.btnLevelmedio)
        buttonLevelMedio.setOnClickListener {
            val intent = Intent(this, nivelmedio::class.java)
            startActivity(intent)
        }

        // Conexión para el botón btnLeveldificl
        val buttonLevelDificil: Button = findViewById(R.id.btnLeveldificil)
        buttonLevelDificil.setOnClickListener {
            val intent = Intent(this, niveldificil::class.java)
            startActivity(intent)
        }
    }

    private fun enableEdgeToEdge() {
        // Método para habilitar el modo de borde a borde (si es necesario)
    }
}
