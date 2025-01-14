package com.example.echochicapplication.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.echochicapplication.R
import com.example.echochicapplication.databinding.ActivityQuizBinding

class Quiz : AppCompatActivity() {
    lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding.apply {
            singleBtn.setOnClickListener {

            }
           // bottomMenu.setItemSelected(R.id.item_home)
           // bottomMenu.setOnClickListener{
             //   if(it== R.id.item_board){

              //  }
//            }

        }

    }
}