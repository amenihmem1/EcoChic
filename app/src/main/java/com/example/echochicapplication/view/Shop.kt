package com.example.echochicapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.echochicapplication.R
import com.example.echochicapplication.adapter.ProductAdapter
import com.example.echochicapplication.adapter.ProductCategoryAdapter
import com.example.echochicapplication.model.ProductCategory
import com.example.echochicapplication.model.Products

class shop : AppCompatActivity() {

    private lateinit var productCategoryAdapter: ProductCategoryAdapter
    private lateinit var productCatRecycler: RecyclerView
    private lateinit var prodItemRecycler: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val productCategoryList = listOf(
            ProductCategory(1, "Trending"),
            ProductCategory(2, "Most Popular"),
            ProductCategory(3, "All Body Products"),
            ProductCategory(4, "Hoodies"),
            ProductCategory(5, "Jeans"),
            ProductCategory(6, "Dresses"),
            ProductCategory(7, "Blazers")
        )
        setProductRecycler(productCategoryList)

        val productsList = listOf(
            Products(1, "aaaaa", "XL ", "$ 17.00", R.drawable.prod2),
            Products(2, "bbbbb", "M", "$ 25.00", R.drawable.prod),
            Products(1, "ccccccc", "L", "$ 17.00", R.drawable.prod2),
            Products(2, "ddddddddd", "XXL", "$ 25.00", R.drawable.prod),
            Products(1, "fffffff", "S", "$ 17.00", R.drawable.prod2),
            Products(2, "hhhhhhhhhhhh", "XL", "$ 25.00", R.drawable.prod)
        )
        setProdItemRecycler(productsList)
    }

    private fun setProductRecycler(productCategoryList: List<ProductCategory>) {
        productCatRecycler = findViewById(R.id.cat_recycler)
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        productCatRecycler.layoutManager = layoutManager
        productCategoryAdapter = ProductCategoryAdapter(this, productCategoryList)
        productCatRecycler.adapter = productCategoryAdapter
    }

    private fun setProdItemRecycler(productsList: List<Products>) {
        prodItemRecycler = findViewById(R.id.product_recycler)
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        prodItemRecycler.layoutManager = layoutManager
        productAdapter = ProductAdapter(this, productsList)
        prodItemRecycler.adapter = productAdapter
    }
}
