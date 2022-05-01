package com.olamide.quizappdetails.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.olamide.quizappdetails.Constants
import com.olamide.quizappdetails.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnGetStarted : Button = findViewById(R.id.btnStart)
        var editTextName : EditText = findViewById(R.id.editTextName)

        btnGetStarted.setOnClickListener() {

            if (editTextName.text.isEmpty()) {
                Toast.makeText(this, "Invalid Username", Toast.LENGTH_LONG)
            } else {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, editTextName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}