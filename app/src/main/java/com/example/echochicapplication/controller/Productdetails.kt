package com.example.echochicapplication

import com.google.gson.Gson

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.echochicapplication.model.CartItem
import com.google.common.reflect.TypeToken

class Productdetails : AppCompatActivity() {

    // Liste pour stocker les articles du panier
    private val cartItems = mutableListOf<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetails)

        // Charger les articles du panier sauvegardés
        loadCartItems()

        // Récupérer les vues
        val productNameTextView: TextView = findViewById(R.id.textView11)
        val productPriceTextView: TextView = findViewById(R.id.textView12)
        val productQuantityTextView: TextView = findViewById(R.id.textView14)
        val addToCartButton: Button = findViewById(R.id.button2)
        val minusButton: ImageView = findViewById(R.id.imageView8)
        val plusButton: ImageView = findViewById(R.id.imageView9)
        val backButton: ImageView = findViewById(R.id.imageView)
        val cartIcon: ImageView = findViewById(R.id.imageViewCart)

        // Ajouter un écouteur de clic pour revenir à la page précédente
        backButton.setOnClickListener {
            onBackPressed()  // Appelle la méthode onBackPressed() pour fermer l'activité et revenir à l'écran précédent
        }

        // Écouteur pour le bouton "Add to Cart"
        addToCartButton.setOnClickListener {
            val productName = productNameTextView.text.toString()
            val productPrice = productPriceTextView.text.toString()
            val productQuantity = productQuantityTextView.text.toString().toInt()

            // Ajouter l'article au panier
            val cartItem = CartItem(productName, productPrice, productQuantity)
            cartItems.add(cartItem)

            // Sauvegarder les articles dans SharedPreferences
            saveCartItems()

            // Afficher le ticket
            showReceipt(productName, productPrice, productQuantity.toString())
        }

        // Ajouter un écouteur pour l'icône du panier
        cartIcon.setOnClickListener {
            // Afficher les articles du panier
            showCartHistory()
        }

        // Logique pour la quantité avec les boutons + et -
        minusButton.setOnClickListener {
            var currentQuantity = productQuantityTextView.text.toString().toInt()
            if (currentQuantity > 1) {
                productQuantityTextView.text = (currentQuantity - 1).toString()
            }
        }

        plusButton.setOnClickListener {
            var currentQuantity = productQuantityTextView.text.toString().toInt()
            productQuantityTextView.text = (currentQuantity + 1).toString()
        }
    }

    // Méthode pour sauvegarder les articles dans SharedPreferences
    private fun saveCartItems() {
        val sharedPreferences = getSharedPreferences("cart_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(cartItems)  // Convertir la liste en JSON
        editor.putString("cart_items", json)
        editor.apply()  // Sauvegarder dans SharedPreferences
    }

    // Méthode pour charger les articles du panier depuis SharedPreferences
    private fun loadCartItems() {
        val sharedPreferences = getSharedPreferences("cart_data", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("cart_items", "[]")  // Récupérer la chaîne JSON
        val type = object : TypeToken<List<CartItem>>() {}.type
        val loadedItems: List<CartItem> = Gson().fromJson(json, type)
        cartItems.clear()
        cartItems.addAll(loadedItems)  // Ajouter les articles chargés dans la liste
    }

    // Méthode pour afficher l'historique du panier
    private fun showCartHistory() {
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Le panier est vide", Toast.LENGTH_SHORT).show()
            return
        }

        // Calculer le total du panier
        var totalAmount = 0.0
        val cartHistoryMessage = cartItems.joinToString("\n") {
            val price = it.productPrice.replace("$", "").toDouble()
            val itemTotal = it.quantity * price
            totalAmount += itemTotal  // Ajouter le total de cet article au total du panier
            "${it.productName} - ${it.quantity} x $${it.productPrice} = $${"%.2f".format(itemTotal)}"
        }

        // Afficher l'historique du panier et le total
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Historique du Panier")
        builder.setMessage("$cartHistoryMessage\n\nTotal: $${"%.2f".format(totalAmount)}")
        builder.setPositiveButton("Fermer") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    // Méthode pour afficher le ticket
    private fun showReceipt(productName: String, productPrice: String, productQuantity: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ticket de Magasin")

        // Calculer le total
        val price = productPrice.replace("$", "").toDouble()
        val quantity = productQuantity.toInt()
        val total = price * quantity

        // Créer un message de ticket
        val receiptMessage = """
            Nom: $productName
            Prix: $productPrice
            Quantité: $productQuantity
            Total: $${"%.2f".format(total)}
        """.trimIndent()

        builder.setMessage(receiptMessage)

        builder.setPositiveButton("Fermer") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}
