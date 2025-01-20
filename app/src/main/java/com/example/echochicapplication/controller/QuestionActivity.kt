package com.example.echochicapplication.views

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.echochicapplication.adapter.QuestionAdapter
import com.example.echochicapplication.databinding.ActivityQuestionBinding
import com.example.echochicapplication.model.QuestionModel
import com.example.echochicapplication.views.ScoreActivity

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    var position:Int=0
    var receivedList:MutableList<QuestionModel> = mutableListOf()
    var allscore=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        receivedList=intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()
        binding.apply {
            backBtn.setOnClickListener{ finish()}
            progressBar.progress=1
            questionTxt.text=receivedList[position].question
            val drawableResourceId: Int = binding.root.resources.getIdentifier(
                receivedList[position].picPath,"drawable",binding.root.context.packageName
            )
            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply ( RequestOptions.bitmapTransform(RoundedCorners(60)))
                //.into(pic)
            loadAnswers()
            rightArrow.setOnClickListener{
                if(progressBar.progress==10){
                    val intent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                    intent.putExtra("score",allscore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener

                }
                position++
                progressBar.progress=progressBar.progress+1
                questionNumberTxt.text="Question"+progressBar.progress + "/10"
                questionTxt.text=receivedList[position].question

                val drawableResourceId:Int=binding.root.resources.getIdentifier(receivedList[position].picPath,
                    "drawable",binding.root.context.packageName)
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply ( RequestOptions.bitmapTransform(RoundedCorners(60)) )
                    //.into(pic)
                loadAnswers()
            }
            rightArrow.setOnClickListener{
                if(progressBar.progress==10){
                    val intent = Intent(this@QuestionActivity,ScoreActivity::class.java)
                    intent.putExtra("score",allscore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener

                }
                position++
                progressBar.progress=progressBar.progress+1
                questionNumberTxt.text="Question"+progressBar.progress + "/10"
                questionTxt.text=receivedList[position].question

                val drawableResourceId:Int=binding.root.resources.getIdentifier(receivedList[position].picPath,
                    "drawable",binding.root.context.packageName)
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply ( RequestOptions.bitmapTransform(RoundedCorners(60)) )
                   // .into(pic)
                loadAnswers()
            }


            leftArrow.setOnClickListener{
                if(progressBar.progress==1){

                    return@setOnClickListener

                }
                position--
                progressBar.progress=progressBar.progress - 1
                questionNumberTxt.text="Question"+progressBar.progress + "/10"
                questionTxt.text=receivedList[position].question

                val drawableResourceId:Int=binding.root.resources.getIdentifier(receivedList[position].picPath,
                    "drawable",binding.root.context.packageName)
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply ( RequestOptions.bitmapTransform(RoundedCorners(60)) )
                   // .into(pic)
                loadAnswers()
            } } }



    fun loadAnswers(){
        val users:MutableList<String> = mutableListOf()
        users.add(receivedList[position].answer_1.toString())
        users.add(receivedList[position].answer_2.toString())
        users.add(receivedList[position].answer_3.toString())
        users.add(receivedList[position].answer_4.toString())

        if (receivedList[position].clickedAnswer!=null) users.add(receivedList[position].clickedAnswer.toString())

        val questionAdapter by lazy {
            QuestionAdapter(receivedList[position].correct_answer.toString(),users,this)
        }
        questionAdapter.differ.submitList(users)
        binding.questionList.apply {
            layoutManager=LinearLayoutManager(this@QuestionActivity)
            adapter=questionAdapter
        }
    }
    fun amount(number: Int,clickedAnswer:String){
        allscore+=number
        receivedList[position].clickedAnswer=clickedAnswer
    }
    }
