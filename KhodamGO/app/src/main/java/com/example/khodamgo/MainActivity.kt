package com.example.khodamgo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.khodamgo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var questionModelList: MutableList<QuestionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromFirebase()

        binding.startBtn.setOnClickListener {
            if (questionModelList.isNotEmpty()) {
                val intent = Intent(this, QuizActivity::class.java)
                QuizActivity.questionModelList = questionModelList
                startActivity(intent)
            } else {
                Toast.makeText(this, "No questions available!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDataFromFirebase() {
        questionModelList = mutableListOf()
        // Menambahkan pertanyaan, opsi dan poin dalam format singkat
        questionModelList.add(
            QuestionModel("When faced with a problem, do you prefer to:",
                listOf("Analyze and think logically", "Consult with others", "Trust your intuition", "Wait and observe"),
                listOf(40, 20, 30, 10))
        )

        questionModelList.add(
            QuestionModel("How do you prefer to recharge after a long day?",
                listOf("Spending time alone", "Socializing with friends", "Engaging in a creative hobby", "Resting in quiet surroundings"),
                listOf(30, 20, 10, 40))
        )

        questionModelList.add(
            QuestionModel("Do you enjoy making decisions based on:",
                listOf("Logical reasoning", "Feelings and values", "A combination of both", "Practical experience"),
                listOf(40, 20, 30, 10))
        )

        questionModelList.add(
            QuestionModel("How do you prefer to plan your day?",
                listOf("I like to stick to a schedule", "I prefer flexibility and spontaneity", "I like a balance between planning and spontaneity", "I don't need a plan, I go with the flow"),
                listOf(40, 20, 30, 10))
        )

        questionModelList.add(
            QuestionModel("When you're working on a project, you usually:",
                listOf("Stick to the facts and focus on the task", "Seek feedback from others", "Go with your gut feeling", "Enjoy brainstorming and experimenting"),
                listOf(40, 20, 30, 10))
        )

        questionModelList.add(
            QuestionModel("How do you typically make decisions?",
                listOf("Using logical analysis and reasoning", "Relying on personal values and emotions", "Considering the pros and cons", "Trusting my instincts and intuition"),
                listOf(40, 20, 30, 10))
        )

        questionModelList.add(
            QuestionModel("In social situations, you tend to:",
                listOf("Take charge and lead", "Observe and listen", "Make sure everyone is included", "Engage in deep one-on-one conversations"),
                listOf(30, 20, 10, 40))
        )

        questionModelList.add(
            QuestionModel("When working on a task, do you prefer:",
                listOf("Having a clear structure and guidelines", "A flexible approach to problem-solving", "A mix of structure and freedom", "Exploring different options freely"),
                listOf(40, 10, 30, 20))
        )

        questionModelList.add(
            QuestionModel("How do you prefer to handle new information?",
                listOf("Focus on the details and analyze them thoroughly", "Look at the big picture first", "Take time to think it through before making a decision", "Trust your gut feeling and take action quickly"),
                listOf(40, 30, 20, 10))
        )

        questionModelList.add(
            QuestionModel("How do you feel about routine tasks?",
                listOf("I enjoy doing them as they help me stay organized", "I try to find ways to make them more interesting", "I dislike them but do them anyway", "I prefer variety and avoid routine if possible"),
                listOf(40, 20, 30, 10))
        )

    }
}
