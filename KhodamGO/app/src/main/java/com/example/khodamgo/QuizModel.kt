
package com.example.khodamgo

data class AnswerOption(
    val answer: String,
    val category: String
)

data class QuestionModel(
    val question: String,
    val options: List<AnswerOption>,
    val points: List<Int>
)

data class CategoryScore(
    val category: String,
    var points: Int = 0
)
