package com.example.khodamgo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.khodamgo.databinding.ActivityQuizBinding
import com.example.khodamgo.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var questionModelList: List<QuestionModel> = listOf()
    }

    lateinit var binding: ActivityQuizBinding
    var currentQuestionIndex = 0
    var selectedAnswer = ""
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        loadQuestions()
    }

    private fun loadQuestions() {
        selectedAnswer = ""
        if (currentQuestionIndex == questionModelList.size) {
            return
        }

        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionIndex + 1}/ ${questionModelList.size}"
            questionProgressIndicator.progress =
                (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            btn3.text = questionModelList[currentQuestionIndex].options[3]
        }
    }

    override fun onClick(view: View?) {
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))
        }

        val clickedBtn = view as Button
        if (clickedBtn.id == R.id.nextBtn) {
            // Update score based on selected answer
            val selectedAnswerIndex = when (selectedAnswer) {
                questionModelList[currentQuestionIndex].options[0] -> 0
                questionModelList[currentQuestionIndex].options[1] -> 1
                questionModelList[currentQuestionIndex].options[2] -> 2
                questionModelList[currentQuestionIndex].options[3] -> 3
                else -> -1
            }

            if (selectedAnswerIndex != -1) {
                score += questionModelList[currentQuestionIndex].points[selectedAnswerIndex]
            }

            Log.i("Score of quiz", score.toString())

            // Check if it is the last question
            if (currentQuestionIndex == questionModelList.size - 1) {
                // If it is the last question, show result dialog
                showResultDialog()
            } else {
                // Otherwise, move to the next question
                currentQuestionIndex++
                loadQuestions()
            }
        } else {
            // Mark the selected answer
            selectedAnswer = clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.orange))
        }
    }

    private fun showResultDialog() {
        // Create a dialog view and bind it
        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            val maxScore = 400
            val percentage = ((score.toFloat() / maxScore.toFloat()) * 100).toInt()

            when {
                percentage == 100 -> {
                    scoreTitle.text = "Amazing! You have unlocked a rare AR character!"
                    scoreTitle.setTextColor(Color.BLUE)
                    characterImageView.setImageResource(R.drawable.khodam_) // Update with actual image
                }
                percentage >= 85 -> {
                    scoreTitle.text = "Amazing! You have unlocked a rare AR character!"
                    scoreTitle.setTextColor(Color.BLUE)
                    characterImageView.setImageResource(R.drawable.khodam_)
                }
                percentage >= 70 -> {
                    scoreTitle.text = "Great job! You've unlocked a strong AR character!"
                    scoreTitle.setTextColor(Color.GREEN)
                    characterImageView.setImageResource(R.drawable.khodam_)
                }
                percentage >= 55 -> {
                    scoreTitle.text = "Good job! You've unlocked a common AR character!"
                    scoreTitle.setTextColor(Color.BLACK)
                    characterImageView.setImageResource(R.drawable.khodam_)
                }
                percentage >= 40 -> {
                    scoreTitle.text = "Selamat! Kamu mendapatkan Sigit Rendang!"
                    scoreTitle.setTextColor(Color.DKGRAY)
                    characterImageView.setImageResource(R.drawable.sigit_rendang)
                }
                else -> {
                    scoreTitle.text = "Selamat! Kamu mendapatkan Skibidi Toilet Full Rizz!"
                    scoreTitle.setTextColor(Color.MAGENTA)
                    characterImageView.setImageResource(R.drawable.skibidi)
                }
            }

            finishBtn.setOnClickListener {
                val intent = Intent(this@QuizActivity, KhodamActivity::class.java)
                intent.putExtra("score", score) // Send score to KhodamActivity
                startActivity(intent)
                finish()
            }
        }

        // Show dialog as an AlertDialog
        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()
    }
}
