package com.example.khodamgo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.khodamgo.databinding.ActivityQuizBinding
import com.example.khodamgo.databinding.ScoreDialogBinding
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlin.math.pow
import kotlin.math.sqrt

class QuizActivity : AppCompatActivity() {
    private val questionModelList = mutableListOf<QuestionModel>()
    private val userAnswersPoint = mutableListOf<Int>()
    private var currentQuestionIndex = 0

    // Track answers in terms of categories
    private val categoryCounts = mutableMapOf(
        "Introvert" to 0,
        "Ekstrovert" to 0,
        "Logis" to 0,
        "Intuitif" to 0
    )

    private val userAnswers = mutableListOf<CategoryScore>(
        CategoryScore("Introvert"),
        CategoryScore("Ekstrovert"),
        CategoryScore("Logis"),
        CategoryScore("Intuitif")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupQuestions()
        displayQuestion(currentQuestionIndex)

        // Answer buttons click listeners
        findViewById<Button>(R.id.btn0).setOnClickListener { handleAnswerSelection(0) }
        findViewById<Button>(R.id.btn1).setOnClickListener { handleAnswerSelection(1) }
        findViewById<Button>(R.id.btn2).setOnClickListener { handleAnswerSelection(2) }
        findViewById<Button>(R.id.btn3).setOnClickListener { handleAnswerSelection(3) }

        // Next button click listener
        findViewById<Button>(R.id.nextBtn).setOnClickListener {
            handleAnswerSelection(-1) // Handle next question navigation without selection
        }
    }


    private fun setupQuestions() {
        questionModelList.add(
            QuestionModel(
                "Bagaimana Anda merespons situasi tak terduga?",
                listOf(
                    AnswerOption("Menyendiri untuk menenangkan diri dulu","Introvert"),
                    AnswerOption("Berbicara dengan orang lain untuk mencari solusi","Ekstrovert"),
                    AnswerOption("Menggunakan logika untuk menyelesaikan masalah","Logis"),
                    AnswerOption("Menyambut perubahan sebagai tantangan baru","Intuitif")
                ),
                listOf(40, 20, 30, 10)
            )
        )
        questionModelList.add(
            QuestionModel(
                "Apa yang paling menggambarkan kegiatan akhir pekan ideal Anda?",
                listOf(
                    AnswerOption("Membaca buku atau menulis jurnal","Introvert"),
                    AnswerOption("Menghadiri pesta atau berkumpul dengan teman","Ekstrovert"),
                    AnswerOption("Merencanakan sesuatu atau membuat daftar","Logis"),
                    AnswerOption("Berjalan-jalan tanpa rencana untuk menemukan hal baru","Intuitif")
                ),
                listOf(30, 20, 10, 40)
            )
        )
        questionModelList.add(
            QuestionModel(
                "Bagaimana cara Anda menghadapi masalah?",
                listOf(
                    AnswerOption("Memikirkan solusi dengan tenang sendirian","Introvert"),
                    AnswerOption("Mendiskusikan masalah dengan teman atau keluarga","Ekstrovert"),
                    AnswerOption("Mencari informasi dan data untuk mendukung keputusan","Logis"),
                    AnswerOption("Mengikuti intuisi dan mengambil keputusan cepat","Intuitif")
                ),
                listOf(40, 20, 30, 10)
            )
        )

        questionModelList.add(
            QuestionModel(
                "Apa yang Anda lakukan saat bertemu orang baru?",
                listOf(
                    AnswerOption("Berbicara hanya jika perlu, mengamati lebih dulu","Introvert"),
                    AnswerOption("Memulai percakapan dengan antusias","Ekstrovert"),
                    AnswerOption("Bertanya tentang fakta atau informasi spesifik","Logis"),
                    AnswerOption("Membahas ide-ide besar atau mimpi mereka","Intuitif")
                ),
                listOf(40, 20, 30, 10)
            )
        )

        questionModelList.add(
            QuestionModel(
                "Ketika bekerja dalam tim, apa peran favorit Anda?",
                listOf(
                    AnswerOption("Mengerjakan tugas sendiri dengan tenang","Introvert"),
                    AnswerOption("Menjadi penghubung atau fasilitator dalam diskusi","Ekstrovert"),
                    AnswerOption("Pembuat strategi dan menganalisis detail ","Logis"),
                    AnswerOption(" Memberikan ide-ide kreatif yang tidak biasa","Intuitif")
                ),
                listOf(40, 20, 10, 30)
            )
        )

        questionModelList.add(
            QuestionModel(
                "Bagaimana Anda menghabiskan waktu di perjalanan panjang?",
                listOf(
                    AnswerOption("Mendengarkan musik atau membaca","Introvert"),
                    AnswerOption("Berbicara dengan penumpang lain atau mendengarkan cerita","Ekstrovert"),
                    AnswerOption("Menonton dokumenter atau membaca artikel informatif","Logis"),
                    AnswerOption("Membayangkan skenario menarik atau cerita kreatif","Intuitif")
                ),
                listOf(40, 10, 20, 30)
            )
        )

        questionModelList.add(
            QuestionModel(
                "Apa yang paling membuat Anda merasa nyaman?",
                listOf(
                    AnswerOption("Waktu sendirian untuk merenung","Introvert"),
                    AnswerOption("Berada di sekitar orang yang menyenangkan","Ekstrovert"),
                    AnswerOption("Menyelesaikan tugas dengan rencana yang jelas","Logis"),
                    AnswerOption("Mencoba sesuatu yang baru dan menarik","Intuitif")
                ),
                listOf(30, 20, 10, 40)
            )
        )

        questionModelList.add(
            QuestionModel(
                "Ketika harus memilih aktivitas, mana yang lebih Anda sukai?",
                listOf(
                    AnswerOption("Aktivitas yang dapat dilakukan sendiri dengan fokus","Introvert"),
                    AnswerOption("Aktivitas yang melibatkan kerja sama dengan banyak orang","Ekstrovert"),
                    AnswerOption("Aktivitas yang terstruktur dengan tujuan yang jelas","Logis"),
                    AnswerOption("Aktivitas yang spontan dan penuh kejutan","Intuitif")
                ),
                listOf(20, 30, 40, 10)
            )
        )

        questionModelList.add(
            QuestionModel(
                "Bagaimana Anda membuat keputusan besar?",
                listOf(
                    AnswerOption("Merenung dengan tenang untuk mempertimbangkan pilihan ","Introvert"),
                    AnswerOption("Berkonsultasi dengan orang-orang yang Anda percayai","Ekstrovert"),
                    AnswerOption("Menganalisis opsi dengan logis sebelum memilih","Logis"),
                    AnswerOption("Mempercayai intuisi dan mengikuti perasaan Anda","Intuitif")
                ),
                listOf(40, 30, 10, 20)
            )
        )

        questionModelList.add(
            QuestionModel(
                "Apa yang paling Anda hargai dalam sebuah hubungan?",
                listOf(
                    AnswerOption("Privasi untuk tetap memiliki waktu sendiri","Introvert"),
                    AnswerOption("Kebersamaan dan komunikasi yang terbuka","Ekstrovert"),
                    AnswerOption("Kejujuran dan konsistensi dalam setiap aspek","Logis"),
                    AnswerOption("Kebebasan untuk berkembang dan saling mendukung ide-ide","Intuitif")
                ),
                listOf(10, 30, 40, 20)
            )
        )


    }
    private fun displayQuestion(index: Int) {
        val question = questionModelList[index]
        findViewById<TextView>(R.id.question_textview).text = question.question

        // Set button texts
        findViewById<Button>(R.id.btn0).text = question.options[0].answer
        findViewById<Button>(R.id.btn1).text = question.options[1].answer
        findViewById<Button>(R.id.btn2).text = question.options[2].answer
        findViewById<Button>(R.id.btn3).text = question.options[3].answer

        // Update question indicator text
        findViewById<TextView>(R.id.question_indicator_textview).text = "Question ${index + 1}/${questionModelList.size}"

        // Update the progress bar
        updateProgressBar()

        val nextBtn =  findViewById<Button>(R.id.nextBtn);
        if(index==questionModelList.size-1){
            nextBtn.visibility = View.VISIBLE
        }else{
            nextBtn.visibility  = View.GONE
        }
    }

    private fun updateProgressBar() {
        val progressBar = findViewById<LinearProgressIndicator>(R.id.question_progress_indicator)
        val progress = ((currentQuestionIndex + 1) * 100) / questionModelList.size
        progressBar.progress = progress
    }

    private fun handleAnswerSelection(answerIndex: Int) {
        if (answerIndex != -1) {
            val selectedOption = questionModelList[currentQuestionIndex].options[answerIndex]
            val selectedCategory = selectedOption.category

            // Increment the count for the selected category
            categoryCounts[selectedCategory] = categoryCounts.getOrDefault(selectedCategory, 0) + 1
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        // Move to the next question or navigate to results
        currentQuestionIndex++
        if (currentQuestionIndex < questionModelList.size) {
            displayQuestion(currentQuestionIndex)
        } else {
            showResults()
        }
    }


    private fun calculateEuclideanDistance(userAnswers: List<Int>, sampleAnswers: List<Int>): Double {
        var sum = 0.0
        for (i in userAnswers.indices) {
            sum += (userAnswers[i] - sampleAnswers[i]).toDouble().pow(2.0)
        }
        return sqrt(sum)
    }


    private fun showResults() {

        // Identify the top 2 categories
        val sortedCategories = categoryCounts.entries.sortedByDescending { it.value }
        val topCategories = sortedCategories.take(2)

        // Handle tie-breaking logic if necessary
        if (topCategories.size == 2 && topCategories[0].value == topCategories[1].value) {
            // Tie case: select the most frequent category
            val mostFrequentKey = topCategories.maxByOrNull { it.value }?.key ?: "Introvert"
            // Pass a default second category
            predictKhodam(mostFrequentKey, "Introvert")
        } else {
            // If no tie, use the two most frequent categories
            predictKhodam(topCategories[0].key, topCategories[1].key)
        }

    }

    private fun predictKhodam(category1: String, category2: String) {
        // Match the khodam based on the two categories
        val khodam = when {
            category1 == "Introvert" && category2 == "Logis" -> "Khodam IL"
            category1 == "Introvert" && category2 == "Logis" -> "Khodam IL2"
            category1 == "Introvert" && category2 == "Intuitif" -> "Khodam IN"
            category1 == "Introvert" && category2 == "Intuitif" -> "Khodam IN2"
            category1 == "Extrovert" && category2 == "Logis" -> "Khodam EL"
            category1 == "Extrovert" && category2 == "Logis" -> "Khodam EL2"
            category1 == "Extrovert" && category2 == "Intuitif" -> "Khodam EN"
            category1 == "Extrovert" && category2 == "Intuitif" -> "Khodam EN2"
            else -> "Unknown Khodam"
        }
    }


        private fun getExplanationForPersonality(personalityType: String): String {
        return when (personalityType) {
            "" -> """
            Berdasarkan jawaban yang kami dapatkan anda adalah Pribadi yang energik dan antusias. Anda merasa paling hidup saat berada di tengah keramaian atau berinteraksi dengan banyak orang.
            Anda senang berbagi ide, mencari pengalaman baru, dan menjadi pusat perhatian. Kemampuan Anda untuk berkomunikasi membuat Anda mudah diterima di berbagai lingkungan sosial.
        """.trimIndent()
            "Introvert" -> """
            Berdasarkan jawaban yang kami dapatkan anda adalah Pribadi yang tenang, reflektif, dan menikmati waktu untuk diri sendiri. Anda cenderung fokus pada hal-hal mendalam dan menyukai hubungan yang bermakna.
            Anda berkembang di lingkungan yang damai dan sering menggunakan waktu pribadi untuk memulihkan energi. Kemampuan Anda untuk mendengarkan dan memahami membuat Anda teman yang baik.
        """.trimIndent()
            "Logis" -> """
            Berdasarkan jawaban yang kami dapatkan anda adalah Pribadi yang analitis dan berpikir rasional. Anda menyukai data, fakta, dan logika untuk membuat keputusan.
            Dalam menghadapi masalah, Anda sering mengambil pendekatan sistematis dan mencari solusi yang paling efisien. Kemampuan ini menjadikan Anda pemecah masalah yang hebat.
        """.trimIndent()

            "Intuitif" -> """
            Berdasarkan jawaban yang kami dapatkan anda adalah Pribadi yang kreatif dan penuh ide. Anda cenderung mengandalkan perasaan dan intuisi daripada fakta mentah untuk membuat keputusan.
            Anda sering melihat pola besar di balik kejadian dan lebih fokus pada kemungkinan jangka panjang. Sifat Anda yang visioner membuat Anda inspiratif bagi orang lain.
        """.trimIndent()

            else -> """
            Kepribadian Anda unik dan memiliki kombinasi sifat yang menarik. Anda mungkin tidak masuk ke dalam kategori tertentu, tetapi hal itu membuat Anda istimewa.
            Teruslah eksplorasi diri untuk memahami potensi dan kelebihan Anda.
        """.trimIndent()
        }

    }

}
