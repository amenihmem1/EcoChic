package com.example.echochicapplication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.echochicapplication.databinding.ActivitySignupBinding
import com.example.echochicapplication.model.userData
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class signup_activity_firstStep : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisation de FirebaseAuth et Firestore
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Gestionnaire de clic pour rediriger vers l'écran de connexion
        binding.tvSignIn.setOnClickListener {
            startActivity(Intent(this, login_activity::class.java))
        }

        // Gestionnaire de clic pour le bouton de création de compte
        binding.btnSignUp.setOnClickListener {
            val username = binding.fullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            // Vérification des champs non vides
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields must be filled out", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Vérification que les mots de passe correspondent
            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Vérification de la longueur du mot de passe
            if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Création du compte utilisateur avec Firebase
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Enregistrement des données utilisateur dans Firestore
                        val userId = firebaseAuth.currentUser?.uid ?: return@addOnCompleteListener
                        val userData = userData(userId, username, email, password, confirmPassword)

                        firestore.collection("users").document(userId).set(userData)
                            .addOnCompleteListener { firestoreTask ->
                                if (firestoreTask.isSuccessful) {
                                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, login_activity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this, "Failed to save user data: ${firestoreTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        val exception = task.exception
                        if (exception is FirebaseNetworkException) {
                            Toast.makeText(this, "Network error. Check your internet connection.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Sign-up failed: ${exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }
}
