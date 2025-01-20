package com.example.echochicapplication.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.echochicapplication.R
import com.example.echochicapplication.model.ProductCategory

class ProductCategoryAdapter(
    private val context: Context,
    private val productCategoryList: List<ProductCategory>
) : RecyclerView.Adapter<ProductCategoryAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.categoryName.text = productCategoryList[position].productName
    }

    override fun getItemCount(): Int = productCategoryList.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.cat_name)
    }
}
