package com.example.khodamgo

data class QuestionModel(
    val question : String,
    val options : List<String>,
    val points : List<Int>,
){
    constructor() : this ("", emptyList(), emptyList())
}