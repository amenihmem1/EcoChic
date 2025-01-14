package com.example.echochicapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.echochicapplication.R
import com.example.echochicapplication.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val getButton1: TextView=findViewById(R.id.tvSignUp)
        val getStartedButton: Button = findViewById(R.id.getStartedButton)
        // Gestionnaire de clic pour rediriger vers l'écran d'inscription
        getButton1.setOnClickListener {
            val intent = Intent(this,login_activity::class.java)
            startActivity(intent)
        }
        // Gestion du clic
        getStartedButton.setOnClickListener {
            val intent = Intent(this, signup_activity_firstStep::class.java)
            startActivity(intent)
        }
    }
}


