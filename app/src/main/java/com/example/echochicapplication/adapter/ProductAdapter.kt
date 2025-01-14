package com.example.echochicapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.echochicapplication.Productdetails
import com.example.echochicapplication.model.Products
import com.example.echochicapplication.R

class ProductAdapter(
    private val context: Context,
    private val productsList: List<Products>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.products_row_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        holder.prodImage.setImageResource(product.imageUrl)
        holder.prodQty.text = product.productQty
        holder.prodPrice.text = product.productPrice

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Productdetails::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = productsList.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prodImage: ImageView = itemView.findViewById(R.id.prod_image)
        val prodQty: TextView = itemView.findViewById(R.id.prod_qty)
        val prodPrice: TextView = itemView.findViewById(R.id.prod_price)
    }
}

