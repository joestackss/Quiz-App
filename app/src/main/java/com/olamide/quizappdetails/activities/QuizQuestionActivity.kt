package com.olamide.quizappdetails.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.olamide.quizappdetails.Constants
import com.olamide.quizappdetails.R

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mQuestionsList :ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0


    private var mUsername :String? = null
    private var mCorrectAnswers: Int = 0

    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView? = null

    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null

    private var btnSubmit : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUsername = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.ivImage)

        tvOptionOne = findViewById(R.id.tvOptionOne)
        tvOptionTwo = findViewById(R.id.tvOptionTwo)
        tvOptionThree = findViewById(R.id.tvOptionThree)

        btnSubmit = findViewById(R.id.btnSubmit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)

        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()
        setQuestion()


    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        defaultOptionsView()
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "Question ${mCurrentPosition} of ${progressBar?.max}"
        tvQuestion?.text = question.questions
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree

        if (mCurrentPosition == mQuestionsList!!.size) {
            btnSubmit?.text = "Finish"
        } else {
            btnSubmit?.text = "Submit"
        }


    }


    // To set default colors of the Text and reset it
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add (0, it)
        }
        tvOptionTwo?.let {
            options.add (1, it)
        }
        tvOptionThree?.let {
            options.add (2, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#6c757d"))
            option.typeface = Typeface.DEFAULT_BOLD
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg

            )
        }
    }


    //Selected view should have a color, background and specific typeface

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }


    // Individual options selected will have this border color/stroke

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvOptionOne -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.tvOptionTwo -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.tvOptionThree -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }

            // When the submit button is clicked, the right and wrong answer color displays
            R.id.btnSubmit -> {


                if(mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                           val intent = Intent (this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUsername)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else {
                    val question = mQuestionsList?.get(mCurrentPosition -1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg,)

                    tvOptionOne?.setTextColor(Color.parseColor("#dee2e6"))
                    tvOptionTwo?.setTextColor(Color.parseColor("#dee2e6"))
                    tvOptionThree?.setTextColor(Color.parseColor("#dee2e6"))


                    if (mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text = "Finish"
                    }else {
                        btnSubmit?.text = "Proceed"
                    }

                    mSelectedOptionPosition = 0
                }
            }


        }
    }

    private fun answerView (answer: Int, drawableView : Int) {
    when(answer) {
        1 -> {
            tvOptionOne?.background = ContextCompat.getDrawable(
                this,
                drawableView
            )
        }

        2 -> {
            tvOptionTwo?.background = ContextCompat.getDrawable(
                this,
                drawableView
            )
        }

        3 -> {
            tvOptionThree?.background = ContextCompat.getDrawable(
                this,
                drawableView
            )
        }
    }
    }
}